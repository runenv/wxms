/**
 * 数据字典类别子项
 * 对应数据库表：t_app_dictionary_item
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
public class DictionaryItemVO extends BaseVO {
	private static final long serialVersionUID = 3791846175414930751L;
	public static final String DICT_ITEM_STATUS="WXMS_STATUS";
	private Integer itemId;          //数据字典类别子项Id
	private String  itemName;        //数据字典类别子项名称
	private String  itemCode;        //数据字典类别子项编码
	private Integer  dictionaryId;        //数据字典类别Id
	private String  dictionaryName;        //数据字典类别Id
	private String  description;     //描述
	private String  itemStatus;      //状态
	private String  itemStatusName;      //状态
	private Integer parentId;//父Id
	private String parentName;
	
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getItemStatus() {
		return itemStatus;
	}
	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}

	public Integer getDictionaryId() {
		return dictionaryId;
	}
	public void setDictionaryId(Integer dictionaryId) {
		this.dictionaryId = dictionaryId;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getDictionaryName() {
		return dictionaryName;
	}
	public void setDictionaryName(String dictionaryName) {
		this.dictionaryName = dictionaryName;
	}
	public String getItemStatusName() {
		return itemStatusName;
	}
	public void setItemStatusName(String itemStatusName) {
		this.itemStatusName = itemStatusName;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
}
