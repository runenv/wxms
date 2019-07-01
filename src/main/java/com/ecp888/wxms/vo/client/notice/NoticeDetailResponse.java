package com.ecp888.wxms.vo.client.notice;

import java.util.Date;

import com.ecp888.wxms.utils.json.JsonDateSerializer;
import com.ecp888.wxms.vo.client.BaseResponse;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 广告位App 详细响应封装类
 * @author ecp875
 * @since 2014-08-24
 */
public class NoticeDetailResponse extends BaseResponse{

	private String title;
	private String imgUrl;
	private String openUrl;
	private String contentUrl;
	private String lotteryId;
	private String time;
	private String startDate;
	private String endDate;
	private String type;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getOpenUrl() {
		return openUrl;
	}
	public void setOpenUrl(String openUrl) {
		this.openUrl = openUrl;
	}
	public String getContentUrl() {
		return contentUrl;
	}
	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}
	public String getLotteryId() {
		return lotteryId;
	}
	public void setLotteryId(String lotteryId) {
		this.lotteryId = lotteryId;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
