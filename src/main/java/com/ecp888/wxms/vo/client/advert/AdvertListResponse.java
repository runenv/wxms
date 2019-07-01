package com.ecp888.wxms.vo.client.advert;

import java.util.List;

import com.ecp888.wxms.vo.client.BaseResponse;

/**
 * 资讯App List响应封装
 * @author ecp875
 * @since 2014-08-24
 */
public class AdvertListResponse extends BaseResponse {

	private String counts = "0";
	private List<Advert> advertArray;
	public String getCounts() {
		return counts;
	}
	public void setCounts(String counts) {
		this.counts = counts;
	}
	public List<Advert> getAdvertArray() {
		return advertArray;
	}
	public void setAdvertArray(List<Advert> advertArray) {
		this.advertArray = advertArray;
	}
	
	
}
