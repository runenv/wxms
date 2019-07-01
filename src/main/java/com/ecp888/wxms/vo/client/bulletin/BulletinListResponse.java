package com.ecp888.wxms.vo.client.bulletin;

import java.util.List;

import com.ecp888.wxms.vo.client.BaseResponse;

/**
 * 公告App List响应封装
 * @author ecp875
 * @since 2014-08-24
 */
public class BulletinListResponse extends BaseResponse {

	private String counts = "0";
	private List<Bulletin> bulletinArray;
	public String getCounts() {
		return counts;
	}
	public void setCounts(String counts) {
		this.counts = counts;
	}
	public List<Bulletin> getBulletinArray() {
		return bulletinArray;
	}
	public void setBulletinArray(List<Bulletin> bulletinArray) {
		this.bulletinArray = bulletinArray;
	}
	
}
