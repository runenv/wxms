package com.ecp888.wxms.vo.push;

import com.ecp888.wxms.vo.BaseVO;
/**
 * APP push 目标用户VO
 * @author ecp875
 * @since 2014-08-03
 */
public class PushTargetVO extends BaseVO{

	private static final long serialVersionUID = -8321726879511951726L;

	private Integer targetId;//序列
	private String targetUser;//目标用户
	private Integer pushId;//t_wx_push.push_id
	public Integer getTargetId() {
		return targetId;
	}
	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
	}
	public String getTargetUser() {
		return targetUser;
	}
	public void setTargetUser(String targetUser) {
		this.targetUser = targetUser;
	}
	public Integer getPushId() {
		return pushId;
	}
	public void setPushId(Integer pushId) {
		this.pushId = pushId;
	}
	
	
	
}
