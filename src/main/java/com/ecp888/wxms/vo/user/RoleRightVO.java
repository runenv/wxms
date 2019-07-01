package com.ecp888.wxms.vo.user;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.ecp888.wxms.vo.BaseVO;

@XmlRootElement(name = "Rows")
@XmlAccessorType(XmlAccessType.FIELD)
public class RoleRightVO extends BaseVO{

	private static final long serialVersionUID = -4073890381752966927L;

	private Integer roleRightId;
	private int roleId;
	private String roleName;
	private int rightId;
	private String rightName;
	private String rightValue;
	private String rightItemName;
	private String rightItemValue;
	private String resourceType;
	private int parentId;
	private String rightIds;
	
	public Integer getRoleRightId() {
		return roleRightId;
	}
	public void setRoleRightId(Integer roleRightId) {
		this.roleRightId = roleRightId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getRightId() {
		return rightId;
	}
	public void setRightId(int rightId) {
		this.rightId = rightId;
	}
	public String getRightName() {
		return rightName;
	}
	public void setRightName(String rightName) {
		this.rightName = rightName;
	}
	public String getRightValue() {
		return rightValue;
	}
	public void setRightValue(String rightValue) {
		this.rightValue = rightValue;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRightIds() {
		return rightIds;
	}
	public void setRightIds(String rightIds) {
		this.rightIds = rightIds;
	}
	public String getRightItemName() {
		return rightItemName;
	}
	public void setRightItemName(String rightItemName) {
		this.rightItemName = rightItemName;
	}
	public String getRightItemValue() {
		return rightItemValue;
	}
	public void setRightItemValue(String rightItemValue) {
		this.rightItemValue = rightItemValue;
	}
	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	
}
