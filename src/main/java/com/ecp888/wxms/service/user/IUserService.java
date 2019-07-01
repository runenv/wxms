package com.ecp888.wxms.service.user;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.user.UserVO;

/**
 * 用户信息服务接口
 * @author ecp875
 * @since 2014-07-07
 *
 */
@Consumes("application/json")
@Produces({"application/json","application/xml"})
@Path("/user")
public interface IUserService {
	/**
	 * @author ecp875
	 * notes 用户登录验证
	 * @param userVO
	 * @return
	 */
	UserVO login(UserVO userVO)throws AppException;
	/**
	 * 查询用户列表
	 * @return
	 */
	List<UserVO> listUser(UserVO userVO,PageVO pageVO)throws AppException;
	/**
	 * 查询单个用户信息
	 * @param userVO
	 * @return
	 */
	UserVO getUser(UserVO userVO)throws AppException;
	/**
	 * 根据用户名查询单个用户信息
	 * @param userVO
	 * @return
	 */
	public UserVO getUserByName(UserVO userVO) throws AppException;
	/**
	 * 保存用户信息
	 * @param userVO
	 * @return
	 */
	void saveUser(UserVO userVO)throws AppException;
	/**
	 * 删除用户信息
	 * @param userVO
	 * @return
	 */
	void deleteUser(UserVO userVO)throws AppException;
	/**
	 * 设置登录用户的默认角色及角色对应的权限列表
	 * @author ecp875
	 * @since 2014-07-07
	 * @param userVO
	 * @throws AppException
	 */
	void synchCurrentRole(UserVO userVO) throws AppException;
	
	/**
	 * 检查用户session是否失效
	 * @return
	 * @throws AppException
	 */
	@GET
	@Path("/checkUserSession")
	boolean checkUserSession() throws AppException;
	
}
