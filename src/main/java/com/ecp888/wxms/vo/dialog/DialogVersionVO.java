package com.ecp888.wxms.vo.dialog;

import com.ecp888.wxms.vo.BaseVO;
/**
 * APP弹窗版本信息VO
 * @author ecp875
 * @since 2014-08-03
 */
public class DialogVersionVO extends BaseVO{

	private static final long serialVersionUID = 2809803945409595278L;

	private Integer versionId;//序列
	private String itemCode;//数据字典item_code值
	private Integer dialogId;//t_wx_dialog.dialog_id
	public Integer getVersionId() {
		return versionId;
	}
	public void setVersionId(Integer versionId) {
		this.versionId = versionId;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public Integer getDialogId() {
		return dialogId;
	}
	public void setDialogId(Integer dialogId) {
		this.dialogId = dialogId;
	}

	
}
