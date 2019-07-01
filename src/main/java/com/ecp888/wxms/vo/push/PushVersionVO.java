package com.ecp888.wxms.vo.push;

import com.ecp888.wxms.vo.BaseVO;
/**
 * APP Push版本信息VO
 * @author ecp875
 * @since 2014-08-03
 */
public class PushVersionVO extends BaseVO{

	private static final long serialVersionUID = -8790428714440840216L;

	private Integer versionId;//序列
	private String itemCode;//数据字典item_code值
	private Integer pushId;//t_wx_push.push_id
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
	public Integer getPushId() {
		return pushId;
	}
	public void setPushId(Integer pushId) {
		this.pushId = pushId;
	}

	
}
