package com.ecp888.wxms.dao.user;

import java.util.List;

import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.user.DeptVO;
/**
 * 部门信息DAO
 * @author ecp875
 * @since 2014-07-07
 */
public interface IDeptDAO {
	/**
	 * 查询部门信息总记录
	 * @author ecp875
	 * @since 2014-07-07
	 * @param deptVO
	 * @return
	 * @throws AppException
	 */
	int countDept(DeptVO DeptVO)throws AppException;
	/**
	 * 查询部门信息列表
	 * @author ecp875
	 * @since 2014-07-07
	 * @param deptVO
	 * @param pageVO
	 * @return
	 * @throws AppException
	 */
	List<DeptVO> listDept(DeptVO deptVO, PageVO pageVO)throws AppException;
	/**
	 * 根据DeptId查询单个部门信息
	 * @author ecp875
	 * @since 2014-07-07
	 * @param deptVO
	 * @return
	 * @throws AppException
	 */
	DeptVO getDeptById(DeptVO deptVO)throws AppException;
	/**
	 * 根据部门名称查询单个部门信息
	 * @author ecp875
	 * @since 2014-07-07
	 * @param deptVO
	 * @return
	 * @throws AppException
	 */
	DeptVO getDeptByName(DeptVO deptVO)throws AppException;
	/**
	 * 新增部门信息
	 * @author ecp875
	 * @since 2014-07-07
	 * @param deptVO
	 * @throws AppException
	 */
	void addDept(DeptVO deptVO)throws AppException;
	/**
	 * 修改部门信息
	 * @author ecp875
	 * @since 2014-07-07
	 * @param deptVO
	 * @throws AppException
	 */
	void updateDept(DeptVO deptVO)throws AppException;
	
	/**
	 * 删除部门信息
	 * @author ecp875
	 * @since 2014-07-07
	 * @param deptVO
	 * @throws AppException
	 */
	void deleteDept(DeptVO deptVO)throws AppException;
	
	/**
	 * 查找部门树
	 * @author ecp875
	 * @since 2014-07-25
	 * @param deptId
	 * @return
	 * @throws AppException
	 */
	List<DeptVO> findDeptTreeById(String deptId)throws AppException;

}
