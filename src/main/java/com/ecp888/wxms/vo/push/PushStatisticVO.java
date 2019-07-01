package com.ecp888.wxms.vo.push;

import com.ecp888.wxms.vo.BaseVO;

/**
 * push数据统计VO
 * @author ecp875
 * @since 2014-09-10
 */
public class PushStatisticVO extends BaseVO{

	private static final long serialVersionUID = -4751571478263037333L;
	
	private Integer statisticId;
	
	private Integer pushId;
	
	private long msgId; //关联JPush的msg_id
	
	private String platform;
	
	private String version;
	
	private int pushCount; //Push次数：统计当前push一共push了多少个手机客户端
	
	private int userCount; //Push用户数: 统计登录的用户数
	
	private int hitCount; //点击次数: push被点击的次数
	
	private int updateFlag; //更新标识，1：已更新，0：待更新

	public Integer getStatisticId() {
		return statisticId;
	}

	public void setStatisticId(Integer statisticId) {
		this.statisticId = statisticId;
	}

	public Integer getPushId() {
		return pushId;
	}

	public void setPushId(Integer pushId) {
		this.pushId = pushId;
	}

	public long getMsgId() {
		return msgId;
	}

	public void setMsgId(long msgId) {
		this.msgId = msgId;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getPushCount() {
		return pushCount;
	}

	public void setPushCount(int pushCount) {
		this.pushCount = pushCount;
	}

	public int getUserCount() {
		return userCount;
	}

	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}

	public int getHitCount() {
		return hitCount;
	}

	public void setHitCount(int hitCount) {
		this.hitCount = hitCount;
	}

	public int getUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(int updateFlag) {
		this.updateFlag = updateFlag;
	}
	
}
