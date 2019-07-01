package com.ecp888.wxms.web.controller.dialog;

import java.util.ArrayList;
import java.util.List;

import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.dialog.DialogVO;
/**
 * APP弹窗信息Form
 * @author ecp875
 * @since 2014-08-11
 */
public class DialogForm {

	private PageVO pageVO=new PageVO();
	
	private DialogVO dialogVO = new DialogVO();
	
	private List<DialogVO> dialogList = new ArrayList<DialogVO>();
	
	private String data;
	
	public PageVO getPageVO() {
		return pageVO;
	}

	public void setPageVO(PageVO pageVO) {
		this.pageVO = pageVO;
	}

	public DialogVO getDialogVO() {
		return dialogVO;
	}

	public void setDialogVO(DialogVO dialogVO) {
		this.dialogVO = dialogVO;
	}

	public List<DialogVO> getDialogList() {
		return dialogList;
	}

	public void setDialogList(List<DialogVO> dialogList) {
		this.dialogList = dialogList;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
}
