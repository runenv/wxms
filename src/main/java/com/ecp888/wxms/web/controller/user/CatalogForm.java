package com.ecp888.wxms.web.controller.user;

import java.util.ArrayList;
import java.util.List;

import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.user.CatalogVO;

/**
 * 栏目Form
 * @author ecp875
 * @since 2014-07-18
 */
public class CatalogForm {

	private PageVO pageVO=new PageVO();

	private CatalogVO catalogVO=new CatalogVO();
	private List<CatalogVO> catalogList=new ArrayList<CatalogVO>();

	public PageVO getPageVO() {
		return pageVO;
	}

	public void setPageVO(PageVO pageVO) {
		this.pageVO = pageVO;
	}

	public CatalogVO getCatalogVO() {
		return catalogVO;
	}

	public void setCatalogVO(CatalogVO catalogVO) {
		this.catalogVO = catalogVO;
	}

	public List<CatalogVO> getCatalogList() {
		return catalogList;
	}

	public void setCatalogList(List<CatalogVO> catalogList) {
		this.catalogList = catalogList;
	}
	
}
