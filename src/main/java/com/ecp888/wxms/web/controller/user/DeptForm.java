package com.ecp888.wxms.web.controller.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.user.DeptVO;

public class DeptForm implements Serializable{

	private static final long serialVersionUID = 3603468797255077494L;

	private PageVO pageVO=new PageVO();
	private DeptVO deptVO=new DeptVO();
	private List<DeptVO> deptList=new ArrayList<DeptVO>();
	public PageVO getPageVO() {
		return pageVO;
	}
	public void setPageVO(PageVO pageVO) {
		this.pageVO = pageVO;
	}
	public DeptVO getDeptVO() {
		return deptVO;
	}
	public void setDeptVO(DeptVO deptVO) {
		this.deptVO = deptVO;
	}
	public List<DeptVO> getDeptList() {
		return deptList;
	}
	public void setDeptList(List<DeptVO> deptList) {
		this.deptList = deptList;
	}
	
	
}
