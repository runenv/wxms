/**
 * 数据字典类别
 * 对应数据库表：t_app_dictionary
 */
package com.ecp888.wxms.vo.dict;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.ecp888.wxms.vo.BaseVO;


/**
 * @author ecp875
 * @since 2014-07-07
 */
@XmlRootElement(name = "Rows")
@XmlAccessorType(XmlAccessType.FIELD)
public class DictionaryVO extends BaseVO {

	private static final long serialVersionUID = 757155746873809184L;
	
	//值为对应数据字典里的类别名称
	//common
	public static final String DICT_STATUS="WXMS_STATUS";
	public static final String DICT_STATUS_TRUE="1";
	public static final String DICT_STATUS_FALSE="0";
	public static final String RELEASE_STATUS="RELEASE_STATUS";
	public static final String RELEASE_STATUS_TRUE="1";
	public static final String RELEASE_STATUS_FALSE="0";
	
	
	//NOTICE Module
	public static final String NOTICE_PLATFORM = "NOTICE_PLATFORM";
	public static final String NOTICE_CLASS="NOTICE_CLASS";
	public static final String NOTICE_ACTION="NOTICE_ACTION";
	
	public static final String NOTICE_DIRECT="NOTICE_DIRECT";
	public static final String NOTICE_DIRECT_PAGE="1";
	public static final String NOTICE_DIRECT_URL="0";
	public static final Integer NOTICE_CLASSID_H5=3;
	
	
	//PUSH Module
	public static final String PUSH_PLATFORM = "PUSH_PLATFORM";
	public static final String PUSH_VERSION = "PUSH_VERSION";
	
	public static final String PUSH_TYPE="PUSH_TYPE";
	public static final String PUSH_TYPE_LAZY="0";
	public static final String PUSH_TYPE_LOAD="1";
	
	public static final String PUSH_DIRECT="PUSH_DIRECT";
	public static final String PUSH_DIRECT_PAGE="1";
	public static final String PUSH_DIRECT_URL="0";
	public static final Integer PUSH_CLASSID_H5=3;
	
	//ADVERT Module
	public static final String ADVERT_DIRECT="ADVERT_DIRECT";
	public static final String ADVERT_DIRECT_PAGE="1";
	public static final String ADVERT_DIRECT_URL="0";
	
	//DIALOG Module
	public static final String DIALOG_DIRECT="DIALOG_DIRECT";
	public static final String DIALOG_DIRECT_PAGE="1";
	public static final String DIALOG_DIRECT_URL="0";
	
	
	//BULLETIN Module  
	public static final String BULLETIN_PLATFORM = "BULLETIN_PLATFORM";
	public static final String BULLETIN_DIRECT="BULLETIN_DIRECT";
	public static final String BULLETIN_DIRECT_PAGE="1";
	public static final String BULLETIN_DIRECT_URL="0";
	
	private Integer dictionaryId;    //数据字典类别Id
	private String  dictionaryName;  //数据字典类别名称
	private String  description;     //描述
	private String  dictionaryStatus;//状态
	private String  dictionaryStatusName;//状态
	
	private Integer parentId;//父Id
	private String parentName;//父节点名称
	
	private DictionaryItemVO dictItemVO =new DictionaryItemVO();//数据字典Item
	
	public DictionaryItemVO getDictItemVO() {
		return dictItemVO;
	}
	public void setDictItemVO(DictionaryItemVO dictItemVO) {
		this.dictItemVO = dictItemVO;
	}
	public Integer getDictionaryId() {
		return dictionaryId;
	}
	public void setDictionaryId(Integer dictionaryId) {
		this.dictionaryId = dictionaryId;
	}
	public String getDictionaryName() {
		return dictionaryName;
	}
	public void setDictionaryName(String dictionaryName) {
		this.dictionaryName = dictionaryName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDictionaryStatus() {
		return dictionaryStatus;
	}
	public void setDictionaryStatus(String dictionaryStatus) {
		this.dictionaryStatus = dictionaryStatus;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getDictionaryStatusName() {
		return dictionaryStatusName;
	}
	public void setDictionaryStatusName(String dictionaryStatusName) {
		this.dictionaryStatusName = dictionaryStatusName;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
}
