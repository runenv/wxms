package com.ecp888.wxms.service.user;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.user.DeptVO;
/**
 * 部门信息服务类
 * @author ecp875
 * @since 2014-07-07
 */
@Consumes("application/json")
@Produces({"application/json","application/xml"})
@Path("/dept")
public interface IDeptService {
	/**
	 * 查询列表部门信息
	 * @author ecp875
	 * @since 2014-07-07
	 * @param deptVO
	 * @param pageVO
	 * @return
	 * @throws AppException
	 */
	List<DeptVO> listDept(DeptVO deptVO,PageVO pageVO)throws AppException;
	/**
	 * 查询单个部门信息
	 * @author ecp875
	 * @since 2014-07-07
	 * @param deptVO
	 * @return
	 * @throws AppException
	 */
	@GET
	@Path("/dept/{deptId}")
	DeptVO getDept(@PathParam("deptId")DeptVO deptVO)throws AppException;
	/**
	 * 保存部门信息
	 * @author ecp875
	 * @since 2014-07-07
	 * @param deptVO
	 * @throws AppException
	 */
	void saveDept(DeptVO deptVO)throws AppException;
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
	@GET
	@Path("/deptTree/{deptId}")
	List<DeptVO> findDeptTreeById(@PathParam("deptId")String deptId)throws AppException;

}
