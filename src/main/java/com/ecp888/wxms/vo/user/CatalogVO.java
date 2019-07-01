package com.ecp888.wxms.vo.user;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.ecp888.wxms.vo.BaseVO;
/**
 * 栏目VO
 * @author ecp875
 * @since 2014-07-17
 */
@XmlRootElement(name = "Rows")
@XmlAccessorType(XmlAccessType.FIELD)
public class CatalogVO extends BaseVO {

	private static final long serialVersionUID = 188978449699835095L;

	private Integer catalogId; //序号
	private String catalogName; //栏目名称
	private String catalogValue; //栏目值
	private String catalogUrl; //栏目URL
	private Integer parentId; //父ID
	private String parentName;//父节点名称
	private String catalogFlag; //栏目标识 1：有效，0：无效 
	private String catalogDescription; //栏目描述
	private Integer orderNum; //栏目排序号
	
	private RoleVO roleVO = new RoleVO();
	
	public RoleVO getRoleVO() {
		return roleVO;
	}
	public void setRoleVO(RoleVO roleVO) {
		this.roleVO = roleVO;
	}
	public Integer getCatalogId() {
		return catalogId;
	}
	public void setCatalogId(Integer catalogId) {
		this.catalogId = catalogId;
	}
	public String getCatalogName() {
		return catalogName;
	}
	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}
	public String getCatalogValue() {
		return catalogValue;
	}
	public void setCatalogValue(String catalogValue) {
		this.catalogValue = catalogValue;
	}
	public String getCatalogUrl() {
		return catalogUrl;
	}
	public void setCatalogUrl(String catalogUrl) {
		this.catalogUrl = catalogUrl;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getCatalogFlag() {
		return catalogFlag;
	}
	public void setCatalogFlag(String catalogFlag) {
		this.catalogFlag = catalogFlag;
	}
	public String getCatalogDescription() {
		return catalogDescription;
	}
	public void setCatalogDescription(String catalogDescription) {
		this.catalogDescription = catalogDescription;
	}
	
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
	
	
}
