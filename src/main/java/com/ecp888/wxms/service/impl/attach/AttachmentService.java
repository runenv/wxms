package com.ecp888.wxms.service.impl.attach;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecp888.wxms.dao.attach.IAttachmentDAO;
import com.ecp888.wxms.service.attach.IAttachmentService;
import com.ecp888.wxms.utils.AppUtil;
import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.utils.exception.ExConstants;
import com.ecp888.wxms.utils.exception.FunctionException;
import com.ecp888.wxms.utils.file.AttachmentUtil;
import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.attachment.AttachmentVO;
/**
 * 角色信息服务类
 * @author ecp875
 * @since 2014-07-07
 */
@Service("attachmentService")
public class AttachmentService implements IAttachmentService {
	@Autowired
	IAttachmentDAO attachmentDAO;
	
	public List<AttachmentVO> listAttachment(AttachmentVO attachmentVO, PageVO pageVO)
			throws AppException{
		int total = attachmentDAO.countAttachment(attachmentVO);
		pageVO.setRecordTotal(total);
		return attachmentDAO.listAttachment(attachmentVO,pageVO);
	}

	public AttachmentVO getAttachment(AttachmentVO attachmentVO) throws AppException {
		return attachmentDAO.getAttachmentById(attachmentVO);
	}

	/**
	 * 保存附件信息
	 * atachTempPath:上传的临时文件物理路径
	 * @author ecp875
	 * @since 2014-07-07
	 * @param attachmentVO
	 * @throws AppException
	 */
	public AttachmentVO saveAttachment(AttachmentVO attachmentVO) throws AppException {
		
		/*AttachmentVO curAttachment = attachmentDAO.getAttachmentByName(attachmentVO);		
		if (curAttachment!=null){
			throw new FunctionException(ExConstants.F_ATTACH_1001);
		}*/
		String attachName = generateAttachNumber(attachmentVO.getAttachTempPath());
		attachmentVO.setAttachPath(attachmentVO.getUploadPath() + attachName);
		attachmentVO.setAttachName(AttachmentUtil.getFileName(attachmentVO.getAttachTempPath()));
		
		File destFile = new File(attachmentVO.getUploadPath(),attachName);
		boolean bool = AttachmentUtil.moveFile(attachmentVO.getAttachTempPath(), destFile);
		if(bool){
			attachmentVO.setAttachSize(destFile.length());
			addAttachment(attachmentVO);
		}
		return attachmentVO;
	}
	
	public AttachmentVO addAttachment(AttachmentVO attachmentVO) throws AppException {
		attachmentDAO.addAttachment(attachmentVO);
		return attachmentVO;
	}
	
	/**
	 * 根据附件ID删除附件信息
	 * @author ecp875
	 * @since 2014-07-07
	 * @param attachmentVO
	 * @throws AppException
	 */
	public void deleteAttachmentById(AttachmentVO attachmentVO) throws AppException {
		
		attachmentVO = getAttachment(attachmentVO);
		if(null != attachmentVO){
			AttachmentUtil.deleteFile(attachmentVO.getAttachPath());
			attachmentDAO.deleteAttachmentById(attachmentVO);
		}
	}
	
	public void deleteAttachment(AttachmentVO attachmentVO) throws AppException {
		attachmentDAO.deleteAttachment(attachmentVO);
	}

	public AttachmentVO getAttachment(Integer attachmentId) throws AppException {
		AttachmentVO attachmentVO=new AttachmentVO();
		attachmentVO.setAttachId(attachmentId);
		return attachmentDAO.getAttachmentById(attachmentVO);
	}
	
	public String generateAttachNumber(String attachTempPath) throws AppException {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		String data = fmt.format(new Date());
		int attachNumber = attachmentDAO.generateAttachNumber();
		return data + attachNumber +"."+AttachmentUtil.getFileSuffix(attachTempPath);
	}

	

}
