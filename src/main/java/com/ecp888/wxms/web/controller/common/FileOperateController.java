package com.ecp888.wxms.web.controller.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ecp888.wxms.service.attach.IAttachmentService;
import com.ecp888.wxms.utils.AppUtil;
import com.ecp888.wxms.vo.attachment.AttachmentVO;
import com.ecp888.wxms.vo.client.BaseResponse;

/**
 * 文件上传
 * @author ecp875
 * @since 2014-08-15
 */
@Controller
@RequestMapping("/file")
public class FileOperateController {
	
	Log log = LogFactory.getLog(getClass());
	@Autowired
	IAttachmentService attachmentService;
	
	/**
	 * 上传文件完毕后返回给前台[0`filepath],0表示上传成功(后跟上传后的文件路径),1表示失败(后跟失败描述)
	 * @param uname
	 * @param myfiles
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
    @RequestMapping(value="/fileUpload")
    public String uploadFile(@RequestParam("fileName") String fileName, @RequestParam MultipartFile[] myfiles, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception{
    	
    	response.setContentType("text/plain; charset=UTF-8");
        //可以在上传文件的同时接收其它参数
        log.info("收到用户[" + fileName + "]的文件上传请求");
        //自动判断/upload是否存在,不存在会自动创建
        String realPath = AttachmentVO.REAL_PATH + AttachmentVO.ATTACH_PATH_TEMP;
        PrintWriter out = response.getWriter();
        String originalFilename = null;  //上传文件的原名
        for(MultipartFile myfile : myfiles){
            if(myfile.isEmpty()){
                out.print("1`请选择文件后上传");
                out.flush();
                return null;
            }else{
            	if(AppUtil.isNotEmpty(fileName)){
            		originalFilename = fileName;
            	}else{
            		originalFilename = myfile.getOriginalFilename();
            	}
                log.info("文件名称: " + myfile.getName()+","+"文件长度: " + myfile.getSize()+","+"文件类型: " + myfile.getContentType());
                try {
                	/**
                	 * FileUtils.copyInputStreamToFile() or MultipartFile.transferTo(File dest)
                	 */
                    FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, originalFilename));
                } catch (IOException e) {
                    log.error("文件[" + originalFilename + "]上传失败,", e);
                    out.print("1`文件上传失败，请重试！！");
                    out.flush();
                    return null;
                }
            }
        }
        out.print("0`" + request.getContextPath() + "/"+AttachmentVO.ATTACH_PATH_TEMP + originalFilename);
        out.flush();
        return null;
    }
    
    @RequestMapping(value="/fileDownload/{fileId}")
    public String downloadFile(@PathVariable("fileId") String fileId, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception{
    
    	response.setContentType("text/html;charset=utf-8");  
        request.setCharacterEncoding("UTF-8");  
        if(!AppUtil.isNotEmpty(fileId)) return null;
        BufferedInputStream bis = null;  
        BufferedOutputStream bos = null;  
        /*String ctxPath = request.getSession().getServletContext().getRealPath("/") + "\\" + "upload\\";  
        ctxPath = ctxPath.replaceAll("\\\\", "/");*/
        AttachmentVO attachmentVO = attachmentService.getAttachment(Integer.valueOf(fileId));
        if(null == attachmentVO) return null;
        log.info("下载文件路径：" + attachmentVO.getAttachPath());  
        try {  
            long fileLength = new File(attachmentVO.getAttachPath()).length();  
            response.setContentType("application/x-msdownload;");  
            response.setHeader("Content-disposition", "attachment; filename="  
            		+ new String(attachmentVO.getAttachName().getBytes("GB2312"), "ISO8859-1"));  
            response.setHeader("Content-Length", String.valueOf(fileLength));  
            bis = new BufferedInputStream(new FileInputStream(attachmentVO.getAttachPath()));  
            bos = new BufferedOutputStream(response.getOutputStream());  
            byte[] buff = new byte[2048];  
            int bytesRead;  
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {  
                bos.write(buff, 0, bytesRead);  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (bis != null)  
                bis.close();  
            if (bos != null)  
                bos.close();  
        }  
    	return null;
    }
    
    @RequestMapping(value="/fileDelete/{fileId}")
    public @ResponseBody String deleteFile(@PathVariable("fileId") String fileId, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception{
    	response.setContentType("text/html;charset=utf-8");  
        request.setCharacterEncoding("UTF-8");  
        BaseResponse baseResponse = new BaseResponse();
        String message = "";
        if(!AppUtil.isNotEmpty(fileId)){
        	baseResponse.setStatusCode(BaseResponse.REQUEST_DATA_EXCEPTION);
			baseResponse.setErrorMsg("fileId is null !");
			message = AppUtil.object2Json(baseResponse);
        	return message;
        }
        AttachmentVO attachmentVO = attachmentService.getAttachment(Integer.valueOf(fileId));
        if(null == attachmentVO){
        	baseResponse.setStatusCode(BaseResponse.REQUEST_DATA_EXCEPTION);
			baseResponse.setErrorMsg("该附件不存在!");
			message = AppUtil.object2Json(baseResponse);
        	return message;
        }
        log.info("删除的文件路径：" + attachmentVO.getAttachPath());  
        attachmentService.deleteAttachmentById(attachmentVO);
        message = AppUtil.object2Json(baseResponse);
    	return message;
    }
}
