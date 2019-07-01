package com.ecp888.wxms.vo.user;

import javax.ws.rs.PathParam;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.ibatis.annotations.Param;

import com.ecp888.wxms.vo.BaseVO;

/**
 * 部门VO
 * @author ecp875
 * @since 2014-07-07
 */
@XmlRootElement(name = "Rows")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeptVO extends BaseVO{

	private static final long serialVersionUID = -8317305354538498473L;
	private Integer deptId;
	private String deptName;
    private String description;
    private Integer parentId; //父ID
	private String parentName;//父节点名称
    private int deptStatus;
    private String deptStatusName;
	public String getDeptStatusName() {
		return deptStatusName;
	}
	public void setDeptStatusName(String deptStatusName) {
		this.deptStatusName = deptStatusName;
	}
	
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDeptStatus() {
		return deptStatus;
	}
	public void setDeptStatus(int deptStatus) {
		this.deptStatus = deptStatus;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
	public DeptVO() {
		super();
	}
	public DeptVO(String deptId) {
		this.deptId = Integer.valueOf(deptId);
	}
	/*public static DeptVO valueOf(String deptId) {
		DeptVO deptVO = new DeptVO();
		deptVO.setDeptId(Integer.valueOf(deptId));
		return deptVO;
	}*/
	
	
}
