package com.ecp888.wxms.vo.client.notice;

import java.util.List;

import com.ecp888.wxms.vo.client.BaseResponse;

/**
 * 广告位App List响应封装
 * @author ecp875
 * @since 2014-08-24
 */
public class NoticeListResponse extends BaseResponse {

	private String counts = "0";
	private List<Notice> noticeArray;
	public String getCounts() {
		return counts;
	}
	public void setCounts(String counts) {
		this.counts = counts;
	}
	public List<Notice> getNoticeArray() {
		return noticeArray;
	}
	public void setNoticeArray(List<Notice> noticeArray) {
		this.noticeArray = noticeArray;
	}
	
	
}
