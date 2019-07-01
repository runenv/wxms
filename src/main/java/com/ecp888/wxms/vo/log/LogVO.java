package com.ecp888.wxms.vo.log;

import com.ecp888.wxms.vo.BaseVO;
import com.ecp888.wxms.vo.user.UserVO;

/**
 * 系统操作日志VO
 * @author ecp875
 * @since 2014-07-07
 */
public class LogVO extends BaseVO {
	
	private static final long serialVersionUID = 8494167700373431136L;
	
	private Integer logId;
    private String operate;
    private String userIp;
    private UserVO userVO=new UserVO();
	public Integer getLogId() {
		return logId;
	}
	public void setLogId(Integer logId) {
		this.logId = logId;
	}
	public String getOperate() {
		return operate;
	}
	public void setOperate(String operate) {
		this.operate = operate;
	}
	public String getUserIp() {
		return userIp;
	}
	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
	public UserVO getUserVO() {
		return userVO;
	}
	public void setUserVO(UserVO userVO) {
		this.userVO = userVO;
	}
}
