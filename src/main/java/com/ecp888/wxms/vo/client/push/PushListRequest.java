package com.ecp888.wxms.vo.client.push;

public class PushListRequest {
	
	/**
     * 0表示web平台,1表示wap平台,2表示android平台,3表示ios平台(控制某些活动只针对某些平台)
     */
    String platform;
    /**
     * 用于描述渠道的特征的一个字符串(主要用于控制某些公告是针对某些渠道)
     */
    String channelNo;
    
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getChannelNo() {
		return channelNo;
	}
	public void setChannelNo(String channelNo) {
		this.channelNo = channelNo;
	}
    
}
