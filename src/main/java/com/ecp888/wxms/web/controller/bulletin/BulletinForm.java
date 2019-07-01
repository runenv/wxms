package com.ecp888.wxms.web.controller.bulletin;

import java.util.ArrayList;
import java.util.List;

import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.bulletin.BulletinVO;
/**
 * APP公告信息Form
 * @author ecp875
 * @since 2014-08-11
 */
public class BulletinForm {

	private PageVO pageVO=new PageVO();
	
	private BulletinVO bulletinVO = new BulletinVO();
	
	private List<BulletinVO> bulletinList = new ArrayList<BulletinVO>();
	
	private String data;
	
	public PageVO getPageVO() {
		return pageVO;
	}

	public void setPageVO(PageVO pageVO) {
		this.pageVO = pageVO;
	}

	public BulletinVO getBulletinVO() {
		return bulletinVO;
	}

	public void setBulletinVO(BulletinVO bulletinVO) {
		this.bulletinVO = bulletinVO;
	}

	public List<BulletinVO> getBulletinList() {
		return bulletinList;
	}

	public void setBulletinList(List<BulletinVO> bulletinList) {
		this.bulletinList = bulletinList;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
