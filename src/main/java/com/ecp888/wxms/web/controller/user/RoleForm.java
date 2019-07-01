package com.ecp888.wxms.web.controller.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.user.RoleVO;

public class RoleForm implements Serializable{

	private static final long serialVersionUID = 3603468797255077494L;

	private PageVO pageVO=new PageVO();
	private RoleVO roleVO=new RoleVO();
	private List<RoleVO> roleList=new ArrayList<RoleVO>();
	public PageVO getPageVO() {
		return pageVO;
	}
	public void setPageVO(PageVO pageVO) {
		this.pageVO = pageVO;
	}
	public RoleVO getRoleVO() {
		return roleVO;
	}
	public void setRoleVO(RoleVO roleVO) {
		this.roleVO = roleVO;
	}
	public List<RoleVO> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<RoleVO> roleList) {
		this.roleList = roleList;
	}
	
	
}
