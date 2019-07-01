package com.ecp888.wxms.web.controller.push;

import java.util.ArrayList;
import java.util.List;

import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.push.PushStatisticVO;
import com.ecp888.wxms.vo.push.PushVO;
/**
 * APP Push信息Form
 * @author ecp875
 * @since 2014-08-24
 */
public class PushForm {

	private PageVO pageVO=new PageVO();
	
	private PushVO pushVO = new PushVO();
	
	private PushStatisticVO pushStatisticVO = new PushStatisticVO();
	
	private List<PushVO> pushList = new ArrayList<PushVO>();
	
	private List<PushStatisticVO> pushStatisticList = new ArrayList<PushStatisticVO>();
	
	private String data;
	
	public PageVO getPageVO() {
		return pageVO;
	}

	public void setPageVO(PageVO pageVO) {
		this.pageVO = pageVO;
	}

	public PushVO getPushVO() {
		return pushVO;
	}

	public void setPushVO(PushVO pushVO) {
		this.pushVO = pushVO;
	}

	public List<PushVO> getPushList() {
		return pushList;
	}

	public void setPushList(List<PushVO> pushList) {
		this.pushList = pushList;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public PushStatisticVO getPushStatisticVO() {
		return pushStatisticVO;
	}

	public void setPushStatisticVO(PushStatisticVO pushStatisticVO) {
		this.pushStatisticVO = pushStatisticVO;
	}

	public List<PushStatisticVO> getPushStatisticList() {
		return pushStatisticList;
	}

	public void setPushStatisticList(List<PushStatisticVO> pushStatisticList) {
		this.pushStatisticList = pushStatisticList;
	}
	
}
