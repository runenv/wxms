package com.ecp888.wxms.web.controller.advert;

import java.util.ArrayList;
import java.util.List;

import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.advert.AdvertVO;
/**
 * APP资讯信息Form
 * @author ecp875
 * @since 2014-08-11
 */
public class AdvertForm {

	private PageVO pageVO=new PageVO();
	
	private AdvertVO advertVO = new AdvertVO();
	
	private List<AdvertVO> advertList = new ArrayList<AdvertVO>();
	
	private String data;
	
	public PageVO getPageVO() {
		return pageVO;
	}

	public void setPageVO(PageVO pageVO) {
		this.pageVO = pageVO;
	}

	public AdvertVO getAdvertVO() {
		return advertVO;
	}

	public void setAdvertVO(AdvertVO advertVO) {
		this.advertVO = advertVO;
	}

	public List<AdvertVO> getAdvertList() {
		return advertList;
	}

	public void setAdvertList(List<AdvertVO> advertList) {
		this.advertList = advertList;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
