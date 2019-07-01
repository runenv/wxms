package com.ecp888.wxms.vo;

import java.io.Serializable;

/**
 * 分页VO
 * @author ecp875
 * @since 2014-07-07
 */
public class PageVO implements Serializable {

	private static final long serialVersionUID = 129100128566274871L;
	private int pageNo = 1; //当前页号
	private int pageSize=15; //每页记录数
	private int recordTotal = 0; //总记录数
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return (0==pageSize)?10:pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getRecordTotal() {
		return recordTotal;
	}
	public void setRecordTotal(int recordTotal) {
		this.recordTotal = recordTotal;
	}
	public int getPageTotal() {//统计共多少页
		int ret = (this.getRecordTotal() - 1) / this.getPageSize() + 1;
		ret = (ret<1)?1:ret;
		return ret;
	}
	public int getFirstRecord(){
		int ret = (this.getPageNo()-1) * this.getPageSize();// + 1;
		ret = (ret < 1)?0:ret;
		return ret;
	}
	
	public int getEndRecord() {
		return pageNo*pageSize;
	}
}
