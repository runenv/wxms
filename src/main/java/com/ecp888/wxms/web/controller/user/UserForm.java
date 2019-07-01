package com.ecp888.wxms.web.controller.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.user.UserVO;

public class UserForm implements Serializable{

	private static final long serialVersionUID = 3603468797255077494L;

	private UserVO userVO = new UserVO();
	private PageVO pageVO = new PageVO();
	private List<UserVO> userList = new ArrayList<UserVO>();
	
	
	public UserVO getUserVO() {
		return userVO;
	}
	public void setUserVO(UserVO userVO) {
		this.userVO = userVO;
	}
	public PageVO getPageVO() {
		return pageVO;
	}
	public void setPageVO(PageVO pageVO) {
		this.pageVO = pageVO;
	}
	public List<UserVO> getUserList() {
		return userList;
	}
	public void setUserList(List<UserVO> userList) {
		this.userList = userList;
	}
	
	
}
