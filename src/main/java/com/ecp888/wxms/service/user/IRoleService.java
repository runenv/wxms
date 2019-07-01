package com.ecp888.wxms.service.user;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.user.RoleVO;
/**
 * 角色信息服务类
 * @author ecp875
 * @since 2014-07-07
 */
@Consumes("application/json")
@Produces({"application/json","application/xml"})
@Path("/role")
public interface IRoleService {
	/**
	 * 查询列表角色信息
	 * @author ecp875
	 * @since 2014-07-07
	 * @param roleVO
	 * @param pageVO
	 * @return
	 * @throws AppException
	 */
	List<RoleVO> listRole(RoleVO roleVO,PageVO pageVO)throws AppException;
	/**
	 * 查询单个角色信息
	 * @author ecp875
	 * @since 2014-07-07
	 * @param roleVO
	 * @return
	 * @throws AppException
	 */
	RoleVO getRole(RoleVO roleVO)throws AppException;
	
	@GET
	@Path("/getRole/{roleId}")
	RoleVO getRole(@PathParam("roleId")Integer roleId)throws AppException;
	
	/**
	 * 保存角色信息
	 * @author ecp875
	 * @since 2014-07-07
	 * @param roleVO
	 * @throws AppException
	 */
	void saveRole(RoleVO roleVO)throws AppException;
	/**
	 * 删除角色信息
	 * @author ecp875
	 * @since 2014-07-07
	 * @param roleVO
	 * @throws AppException
	 */
	void deleteRole(RoleVO roleVO)throws AppException;
	
	@GET
	@Path("/lookupRole")
	public List<RoleVO> lookupRole()throws AppException;
	
	@GET
	@Path("/currentRole")
	public List<RoleVO> currentRole()throws AppException;
	
	void saveRoleRight(RoleVO roleVO)throws AppException;

}
