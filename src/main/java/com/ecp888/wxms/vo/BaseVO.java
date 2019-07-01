package com.ecp888.wxms.vo;

import java.io.Serializable;

import com.ecp888.wxms.utils.AppUtil;

public class BaseVO implements Serializable {

	private static final long serialVersionUID = 4532303823470234019L;
	public static final String PARTNER_URL = "http://passport.ecp888.com/hessian/gjMemService";
	
	private String processInstanceId;//流程实例ID
	private String approveResult;//审批结果
	private String taskId;//流程任务ID
	
	private String searchValue;
	private Integer createUser;	
	private String createUserName;
	private Integer updateUser; 
	private String updateUserName;
	private String createDate; 		
	private String updateDate;
	private String[] arrId; //platform
	private String[] arrId1; //channelNo
	private String[] arrId2; //version
	private String direction; //排序方向
	private String targetUserPath; //目标用户路径
	private String imgUrlPath;//图片路径
	private String htmlUrlPath;//h5图片路径
	
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public String getUpdateUserName() {
		return updateUserName;
	}
	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}
	public Integer getCreateUser() {
		if(null == createUser){
			createUser = AppUtil.getCurrentUser().getUserId();
		}
		return createUser;
	}
	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}
	public Integer getUpdateUser() {
		if(null == updateUser){
			updateUser = AppUtil.getCurrentUser().getUserId();
		}
		return updateUser;
	}
	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public String getApproveResult() {
		return approveResult;
	}
	public void setApproveResult(String approveResult) {
		this.approveResult = approveResult;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	/**
	 * platform
	 * @return
	 */
	public String[] getArrId() {
		return arrId;
	}
	public void setArrId(String[] arrId) {
		this.arrId = arrId;
	}
	
	/**
	 * channelNo
	 * @return
	 */
	public String[] getArrId1() {
		return arrId1;
	}
	public void setArrId1(String[] arrId1) {
		this.arrId1 = arrId1;
	}
	
	/**
	 * version
	 * @return
	 */
	public String[] getArrId2() {
		return arrId2;
	}
	public void setArrId2(String[] arrId2) {
		this.arrId2 = arrId2;
	}
	
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	public String getTargetUserPath() {
		return targetUserPath;
	}
	public void setTargetUserPath(String targetUserPath) {
		this.targetUserPath = targetUserPath;
	}
	public String getImgUrlPath() {
		return imgUrlPath;
	}
	public void setImgUrlPath(String imgUrlPath) {
		this.imgUrlPath = imgUrlPath;
	}
	public String getHtmlUrlPath() {
		return htmlUrlPath;
	}
	public void setHtmlUrlPath(String htmlUrlPath) {
		this.htmlUrlPath = htmlUrlPath;
	}
	
}
