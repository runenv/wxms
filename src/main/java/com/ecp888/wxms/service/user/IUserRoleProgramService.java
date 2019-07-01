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
import com.ecp888.wxms.vo.user.UserRoleProgramVO;
import com.ecp888.wxms.vo.user.UserVO;

@Consumes("application/json")
@Produces({"application/json","application/xml"})
@Path("/userRole")
public interface IUserRoleProgramService {

	/**
	 * 用户角色列表
	 * @author ecp875
	 * @since 2014-07-07
	 * @param userVO
	 * @param pageVO
	 * @return
	 * @throws AppException
	 */
	List<UserRoleProgramVO> listUserRole(UserVO userVO, PageVO pageVO)throws AppException;

	UserRoleProgramVO getUserRoleById(UserVO userVO)throws AppException;
	
	@GET
	@Path("/getUserRole/{userRoleId}")
	UserRoleProgramVO getUserRoleById(@PathParam("userRoleId")Integer userRoleId)throws AppException;
	
	void saveUserRole(UserVO userVO)throws AppException;

	void deleteUserRole(UserVO userVO)throws AppException;
}
