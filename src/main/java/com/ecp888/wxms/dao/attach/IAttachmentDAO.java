package com.ecp888.wxms.dao.attach;

import java.util.List;

import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.attachment.AttachmentVO;

public interface IAttachmentDAO {
	/**
	 * 查询附件信息总记录
	 * @author ecp875
	 * @since 2014-07-07
	 * @param attachmentVO
	 * @return
	 * @throws AppException
	 */
	int countAttachment(AttachmentVO attachmentVO)throws AppException;
	/**
	 * 查询附件信息列表
	 * @author ecp875
	 * @since 2014-07-07
	 * @param attachmentVO
	 * @param pageVO
	 * @return
	 * @throws AppException
	 */
	List<AttachmentVO> listAttachment(AttachmentVO attachmentVO, PageVO pageVO)throws AppException;
	/**
	 * 根据attachId查询单个附件信息
	 * @author ecp875
	 * @since 2014-07-07
	 * @param attachmentVO
	 * @return
	 * @throws AppException
	 */
	AttachmentVO getAttachmentById(AttachmentVO attachmentVO)throws AppException;
	/**
	 * 根据附件名称查询单个附件信息
	 * @author ecp875
	 * @since 2014-07-07
	 * @param attachmentVO
	 * @return
	 * @throws AppException
	 */
	AttachmentVO getAttachmentByName(AttachmentVO attachmentVO)throws AppException;
	/**
	 * 新增附件信息
	 * @author ecp875
	 * @since 2014-07-07
	 * @param attachmentVO
	 * @throws AppException
	 */
	void addAttachment(AttachmentVO attachmentVO)throws AppException;
	
	/**
	 * 根据附件ID删除附件信息
	 * @author ecp875
	 * @since 2014-07-07
	 * @param attachmentVO
	 * @throws AppException
	 */
	void deleteAttachmentById(AttachmentVO attachmentVO)throws AppException;
	
	/**
	 * 删除附件信息
	 * @author ecp875
	 * @since 2014-07-07
	 * @param attachmentVO
	 * @throws AppException
	 */
	void deleteAttachment(AttachmentVO attachmentVO)throws AppException;
	
	/**
	 * 获取附件序列
	 * @author ecp875
	 * @since 2014-07-07
	 * @throws AppException
	 */
	int generateAttachNumber()throws AppException;
	
	
}
