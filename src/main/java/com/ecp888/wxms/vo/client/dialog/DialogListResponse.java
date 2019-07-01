package com.ecp888.wxms.vo.client.dialog;

import java.util.List;

import com.ecp888.wxms.vo.client.BaseResponse;

/**
 * 弹窗App List响应封装
 * @author ecp875
 * @since 2014-08-24
 */
public class DialogListResponse extends BaseResponse {

	private String counts = "0";
	private List<Dialog> dialogArray;
	public String getCounts() {
		return counts;
	}
	public void setCounts(String counts) {
		this.counts = counts;
	}
	public List<Dialog> getDialogArray() {
		return dialogArray;
	}
	public void setDialogArray(List<Dialog> dialogArray) {
		this.dialogArray = dialogArray;
	}
	
}
