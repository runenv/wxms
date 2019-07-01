package com.ecp888.wxms.vo.user;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.ecp888.wxms.vo.BaseVO;

/**
 * 角色VO类
 * @author ecp875
 * @since 2014-07-07
 */
@XmlRootElement(name = "Rows")
@XmlAccessorType(XmlAccessType.FIELD)
public class RoleVO extends BaseVO{

	private static final long serialVersionUID = 1175341327677401751L;

	private String roleDescription;
	private Integer roleId;
	private String roleName;
	private int roleStatus;
	private String roleStatusName;
	private RoleRightVO roleRightVO=new RoleRightVO();
	private List<RoleRightVO> roleRightList=new ArrayList<RoleRightVO>();
	private List<RoleRightVO> allRightList=new ArrayList<RoleRightVO>();
	
	public RoleRightVO getRoleRightVO() {
		return roleRightVO;
	}
	public void setRoleRightVO(RoleRightVO roleRightVO) {
		this.roleRightVO = roleRightVO;
	}
	public String getRoleStatusName() {
		return roleStatusName;
	}
	public void setRoleStatusName(String roleStatusName) {
		this.roleStatusName = roleStatusName;
	}
	public String getRoleDescription() {
		return roleDescription;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public int getRoleStatus() {
		return roleStatus;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public void setRoleStatus(int roleStatus) {
		this.roleStatus = roleStatus;
	}
	public List<RoleRightVO> getRoleRightList() {
		return roleRightList;
	}
	public void setRoleRightList(List<RoleRightVO> roleRightList) {
		this.roleRightList = roleRightList;
	}
	public List<RoleRightVO> getAllRightList() {
		return allRightList;
	}
	public void setAllRightList(List<RoleRightVO> allRightList) {
		this.allRightList = allRightList;
	}
	
	
}
