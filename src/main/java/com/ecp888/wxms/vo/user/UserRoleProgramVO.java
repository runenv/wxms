package com.ecp888.wxms.vo.user;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.ecp888.wxms.vo.BaseVO;

@XmlRootElement(name = "Rows")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserRoleProgramVO extends BaseVO {

	private static final long serialVersionUID = 5507285098939897201L;
	
	private Integer userRoleProgramId;
	private Integer userId;
	private String userName;
	private RoleVO roleVO=new RoleVO();
	
	public Integer getUserRoleProgramId() {
		return userRoleProgramId;
	}
	public void setUserRoleProgramId(Integer userRoleProgramId) {
		this.userRoleProgramId = userRoleProgramId;
	}
	public RoleVO getRoleVO() {
		return roleVO;
	}
	public void setRoleVO(RoleVO roleVO) {
		this.roleVO = roleVO;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
