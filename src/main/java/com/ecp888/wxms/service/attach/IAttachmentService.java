package com.ecp888.wxms.service.attach;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.attachment.AttachmentVO;
/**
 * 角色信息服务类
 * @author ecp875
 * @since 2014-07-07
 */
@Consumes("application/json")
@Produces({"application/json","application/xml"})
@Path("/attachment")
public interface IAttachmentService {
	/**
	 * 查询列表角色信息
	 * @author ecp875
	 * @since 2014-07-07
	 * @param attachmentVO
	 * @param pageVO
	 * @return
	 * @throws AppException
	 */
	List<AttachmentVO> listAttachment(AttachmentVO attachmentVO,PageVO pageVO)throws AppException;
	/**
	 * 查询单个角色信息
	 * @author ecp875
	 * @since 2014-07-07
	 * @param attachmentVO
	 * @return
	 * @throws AppException
	 */
	AttachmentVO getAttachment(AttachmentVO attachmentVO)throws AppException;
	
	@GET
	@Path("/getAttachment/{attachmentId}")
	AttachmentVO getAttachment(@PathParam("attachmentId")Integer attachmentId)throws AppException;
	
	/**
	 * 保存附件信息
	 * atachTempPath:上传的临时文件物理路径
	 * @author ecp875
	 * @since 2014-07-07
	 * @param attachmentVO
	 * @throws AppException
	 */
	AttachmentVO saveAttachment(AttachmentVO attachmentVO)throws AppException;
	/**
	 * 删除角色信息
	 * @author ecp875
	 * @since 2014-07-07
	 * @param attachmentVO
	 * @throws AppException
	 */
	void deleteAttachment(AttachmentVO attachmentVO)throws AppException;
	
	void deleteAttachmentById(AttachmentVO attachmentVO)throws AppException;
	
	public String generateAttachNumber(String attachTempPath) throws AppException;
	
}
