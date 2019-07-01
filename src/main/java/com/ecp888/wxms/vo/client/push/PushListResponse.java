package com.ecp888.wxms.vo.client.push;

import java.util.List;

import com.ecp888.wxms.vo.client.BaseResponse;

/**
 * Push App List响应封装
 * @author ecp875
 * @since 2014-08-24
 */
public class PushListResponse extends BaseResponse {

	private String counts = "0";
	private List<Push> pushArray;
	public String getCounts() {
		return counts;
	}
	public void setCounts(String counts) {
		this.counts = counts;
	}
	public List<Push> getPushArray() {
		return pushArray;
	}
	public void setPushArray(List<Push> pushArray) {
		this.pushArray = pushArray;
	}
	
}
