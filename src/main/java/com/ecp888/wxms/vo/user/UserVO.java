package com.ecp888.wxms.vo.user;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.ecp888.wxms.vo.BaseVO;
/**
 * @author ecp875
 * created 2014-07-07
 * notes 用户信息VO类
 */
@XmlRootElement(name = "Rows")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserVO extends BaseVO {

	private static final long serialVersionUID = -7285458722744175796L;
	
	private int deptId;
	private String deptName;
	private String userAddress;
	private int userAge;
	private String userBirthDay;
	private String userEmail;
	private String userFax;
	private Integer userId;
	private String userMobile;
	private String userName;
	private String userPassword;
	private String userPhone;
	private String userSex="1";
	private String userSexName;
	private String userStatus;
	private String userZip;
	private int currentRoleId;
	
	private DeptVO deptVO=new DeptVO();
	private List<UserRoleProgramVO> userRoleProgramList=new ArrayList<UserRoleProgramVO>();
	private UserRoleProgramVO userRoleProgramVO=new UserRoleProgramVO();
	
	public int getDeptId() {
		return deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public int getUserAge() {
		return userAge;
	}
	public String getUserBirthDay() {
		return userBirthDay;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public String getUserFax() {
		return userFax;
	}
	public Integer getUserId() {
		return userId;
	}
	public String getUserMobile() {
		return userMobile;
	}
	public String getUserName() {
		return userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public String getUserSex() {
		return userSex;
	}
	public String getUserSexName() {
		return userSexName;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public String getUserZip() {
		return userZip;
	}
	
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}
	public void setUserBirthDay(String userBirthDay) {
		this.userBirthDay = userBirthDay;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public void setUserFax(String userFax) {
		this.userFax = userFax;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public void setUserSexName(String userSexName) {
		this.userSexName = userSexName;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public void setUserZip(String userZip) {
		this.userZip = userZip;
	}
	
	public DeptVO getDeptVO() {
		return deptVO;
	}
	public void setDeptVO(DeptVO deptVO) {
		this.deptVO = deptVO;
	}
	public List<UserRoleProgramVO> getUserRoleProgramList() {
		return userRoleProgramList;
	}
	public void setUserRoleProgramList(List<UserRoleProgramVO> userRoleProgramList) {
		this.userRoleProgramList = userRoleProgramList;
	}
	public UserRoleProgramVO getUserRoleProgramVO() {
		return userRoleProgramVO;
	}
	public void setUserRoleProgramVO(UserRoleProgramVO userRoleProgramVO) {
		this.userRoleProgramVO = userRoleProgramVO;
	}
	public int getCurrentRoleId() {
		return currentRoleId;
	}
	public void setCurrentRoleId(int currentRoleId) {
		this.currentRoleId = currentRoleId;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof UserVO) {
			return ((UserVO) obj).getUserId().equals(this.getUserId());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.getUserId().hashCode();
	}
	
}
