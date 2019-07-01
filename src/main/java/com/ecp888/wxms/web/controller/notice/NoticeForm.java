package com.ecp888.wxms.web.controller.notice;

import java.util.ArrayList;
import java.util.List;

import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.notice.NoticeVO;
/**
 * APP广告位信息Form
 * @author ecp875
 * @since 2014-08-11
 */
public class NoticeForm {

	private PageVO pageVO=new PageVO();
	
	private NoticeVO noticeVO = new NoticeVO();
	
	private List<NoticeVO> noticeList = new ArrayList<NoticeVO>();
	
	private String data;
	
	public PageVO getPageVO() {
		return pageVO;
	}

	public void setPageVO(PageVO pageVO) {
		this.pageVO = pageVO;
	}

	public NoticeVO getNoticeVO() {
		return noticeVO;
	}

	public void setNoticeVO(NoticeVO noticeVO) {
		this.noticeVO = noticeVO;
	}

	public List<NoticeVO> getNoticeList() {
		return noticeList;
	}

	public void setNoticeList(List<NoticeVO> noticeList) {
		this.noticeList = noticeList;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
