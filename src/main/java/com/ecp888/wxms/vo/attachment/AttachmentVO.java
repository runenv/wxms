package com.ecp888.wxms.vo.attachment;

import com.ecp888.wxms.utils.file.AttachmentUtil;
import com.ecp888.wxms.vo.BaseVO;
/**
 * 附件VO
 * @author ecp875
 * @since 2014-08-18
 */
public class AttachmentVO extends BaseVO{

	private static final long serialVersionUID = 3778226673567019340L;
	public static final String ATTACH_VERSION = "1.0";
	
	public static String CONTEXT_PATH = "";
	public static String REAL_PATH = "";
	public static final String ATTACH_PATH = "gjgonggao/images/upload/";
	public static final String ATTACH_PATH_TEMP = "gjgonggao/images/temp/";

	private Integer attachId; //序列
	private String attachName; //附件名称
	private Long attachSize; //附件大小
	private String attachPath; //附件路径
	private String attachTempPath; //附件临时路径
	private String attachVersion; //附件版本
	private String attachMoudle; //附件所属模块
	private Integer attachKey; //附件外键关联
	
	public String getUploadPath() {
		return REAL_PATH + ATTACH_PATH;
	}
	
	public String getUploadTempPath() {
		return REAL_PATH + ATTACH_PATH_TEMP;
	}
	
	/**
	 * 获取上传的临时文件物理路径
	 * @return
	 */
	public String getAttachTempPath() {
		return attachTempPath;
	}
	
	public Integer getAttachId() {
		return attachId;
	}
	public void setAttachId(Integer attachId) {
		this.attachId = attachId;
	}
	public String getAttachName() {
		return attachName;
	}
	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}
	public Long getAttachSize() {
		return attachSize;
	}
	public void setAttachSize(Long attachSize) {
		this.attachSize = attachSize;
	}
	public String getAttachPath() {
		return attachPath;
	}
	public void setAttachPath(String attachPath) {
		this.attachPath = attachPath;
	}
	public String getAttachVersion() {
		if(null==attachVersion || "".equals(attachVersion)){
			attachVersion = ATTACH_VERSION;
		}
		return attachVersion;
	}
	public void setAttachVersion(String attachVersion) {
		this.attachVersion = attachVersion;
	}
	public String getAttachMoudle() {
		return attachMoudle;
	}
	public void setAttachMoudle(String attachMoudle) {
		this.attachMoudle = attachMoudle;
	}
	public Integer getAttachKey() {
		return attachKey;
	}
	public void setAttachKey(Integer attachKey) {
		this.attachKey = attachKey;
	}

	public void setAttachTempPath(String attachTempPath) {
		this.attachTempPath = attachTempPath;
	}
	
	
}
