package com.ecp888.wxms.web.controller.dict;

import java.util.ArrayList;
import java.util.List;

import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.dict.DictionaryItemVO;
import com.ecp888.wxms.vo.dict.DictionaryVO;


public class DictForm {

	private PageVO pageVO=new PageVO();

	private DictionaryVO dictionaryVO=new DictionaryVO();//数据字典类别对象
	private List<DictionaryVO> dictionaryList=new ArrayList<DictionaryVO>();

	private DictionaryItemVO dictItemVO=new DictionaryItemVO();//数据字典类别子项对象

	private List<DictionaryItemVO> dictItemList=new ArrayList<DictionaryItemVO>();

	private String messages;//提示信息

	public PageVO getPageVO() {
		return pageVO;
	}

	public void setPageVO(PageVO pageVO) {
		this.pageVO = pageVO;
	}

	public DictionaryVO getDictionaryVO() {
		return dictionaryVO;
	}

	public void setDictionaryVO(DictionaryVO dictionaryVO) {
		this.dictionaryVO = dictionaryVO;
	}

	public List<DictionaryVO> getDictionaryList() {
		return dictionaryList;
	}

	public void setDictionaryList(List<DictionaryVO> dictionaryList) {
		this.dictionaryList = dictionaryList;
	}

	public DictionaryItemVO getDictItemVO() {
		return dictItemVO;
	}

	public void setDictItemVO(DictionaryItemVO dictItemVO) {
		this.dictItemVO = dictItemVO;
	}

	public List<DictionaryItemVO> getDictItemList() {
		return dictItemList;
	}

	public void setDictItemList(List<DictionaryItemVO> dictItemList) {
		this.dictItemList = dictItemList;
	}

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}
	
	
	
}
