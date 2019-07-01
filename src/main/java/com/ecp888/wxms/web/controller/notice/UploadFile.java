package com.ecp888.wxms.web.controller.notice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.ecp888.wxms.utils.file.ImportExcel;

/**
 * Servlet implementation class UploadFile
 */
//@WebServlet("/UploadFile")
public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CLASS_PATH = "WEB-INF/classes/excel";
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadFile() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings({"rawtypes" })
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		ServletContext context=this.getServletContext();
        
        //构造一个文件上传处理对象
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List items = null;
        try{
            //解析表单中提交的所有文件内容
        	items = upload.parseRequest(request);
        	// 对所有请求信息进行判断
        	Iterator iter = items.iterator();
            while(iter.hasNext()){
            	String name ="";
                FileItem item = (FileItem) iter.next();
                if(!item.isFormField()){
                    //取出上传文件的文件名称
                    name = item.getName();
                }else{
                	name = item.getFieldName();
                }
              //取得上传文件以后的存储路径
                //上传文件以后的存储路径
                //String uploadPath= request.getRealPath("/UploadFile");
                String uploadPath= context.getRealPath("/").replaceAll("\\\\", "/")+ CLASS_PATH;
        		File uploadFile = new File(uploadPath);
                if (!uploadFile.exists()) {
                	uploadFile.mkdirs();
        		}
                PrintWriter out = null;
                FileOutputStream fos = null;
                FileInputStream fin = null;
                try{
                	
	                File tempFile = new File(name);
	                item.write(tempFile);
	                fin = new FileInputStream(tempFile);
	    			// 向服务器写入文件
	                String fileName=name.substring(name.lastIndexOf('/')+1,name.length());
	                if(fileName.indexOf(".xlsx") != -1){
	                	ImportExcel.TEMPLATE_FILE_SUFFIX = ImportExcel.TEMPLATE_FILE_SUFFIX_2007;
	                	fos = new FileOutputStream(uploadPath + File.separatorChar+ImportExcel.USER_FILE_2007);
	                }else{
	                	ImportExcel.TEMPLATE_FILE_SUFFIX = ImportExcel.TEMPLATE_FILE_SUFFIX_2003;
	                	fos = new FileOutputStream(uploadPath + File.separatorChar+ImportExcel.USER_FILE_2003);
	                }
	    			byte[] buffer = new byte[1024];
	    			int i = -1;
	    			while ((i = fin.read(buffer)) != -1) {
	    				// fos.write((char)c);
	    				fos.write(buffer, 0, i);
	    			}
	    			fos.flush();
	    			fos.close();
	    			System.out.println("导入成功!");
	    			//打印上传成功信息
	                out = response.getWriter();
	                //out.print("<font size='2'>上传文件为: "+name+"<br>保存的地址为: "+uploadPath+ "</font>");
	                out.print("<font size='2' style='color:red;'>导入成功！</font>");
                }catch(Exception e){
                	e.printStackTrace();
                	out.print("<font size='2' style='color:red;'>导入失败！</font>");
                }finally{
                	if(null != fin) fin.close();
                	if(null != fos) fos.close();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
	}

}
