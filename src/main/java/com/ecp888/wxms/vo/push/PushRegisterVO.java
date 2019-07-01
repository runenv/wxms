package com.ecp888.wxms.vo.push;

import com.ecp888.wxms.vo.BaseVO;
/**
 * Push目标用户关联设备VO
 * @author ecp875
 * @since 2014-09-18
 *
 */
public class PushRegisterVO extends BaseVO{

	private static final long serialVersionUID = -4166712137671487269L;
	
	public static String REGISTER_TYPE_LOGIN = "1";
	public static String REGISTER_TYPE_LOGOUT = "0";

	private Integer registerId; //序列
	private String targetUser; //目标用户
	private String registrationId; //jPush RegistrationID
	private String registerType; //注册类型,1:登陆，0:登出
	
	public Integer getRegisterId() {
		return registerId;
	}
	public void setRegisterId(Integer registerId) {
		this.registerId = registerId;
	}
	public String getTargetUser() {
		return targetUser;
	}
	public void setTargetUser(String targetUser) {
		this.targetUser = targetUser;
	}
	public String getRegistrationId() {
		return registrationId;
	}
	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}
	public String getRegisterType() {
		return registerType;
	}
	public void setRegisterType(String registerType) {
		this.registerType = registerType;
	}
	
}
