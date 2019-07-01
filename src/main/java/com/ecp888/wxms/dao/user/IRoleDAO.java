package com.ecp888.wxms.dao.user;

import java.util.List;

import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.user.RoleRightVO;
import com.ecp888.wxms.vo.user.RoleVO;
import com.ecp888.wxms.vo.user.UserVO;
/**
 * 角色信息DAO
 * @author ecp875
 * @since 2014-07-07
 */
public interface IRoleDAO {
	/**
	 * 查询角色信息总记录
	 * @author ecp875
	 * @since 2014-07-07
	 * @param roleVO
	 * @return
	 * @throws AppException
	 */
	int countRole(RoleVO roleVO)throws AppException;
	/**
	 * 查询角色信息列表
	 * @author ecp875
	 * @since 2014-07-07
	 * @param roleVO
	 * @param pageVO
	 * @return
	 * @throws AppException
	 */
	List<RoleVO> listRole(RoleVO roleVO, PageVO pageVO)throws AppException;
	/**
	 * 根据roleId查询单个角色信息
	 * @author ecp875
	 * @since 2014-07-07
	 * @param roleVO
	 * @return
	 * @throws AppException
	 */
	RoleVO getRoleById(RoleVO roleVO)throws AppException;
	/**
	 * 根据角色名称查询单个角色信息
	 * @author ecp875
	 * @since 2014-07-07
	 * @param roleVO
	 * @return
	 * @throws AppException
	 */
	RoleVO getRoleByName(RoleVO roleVO)throws AppException;
	/**
	 * 新增角色信息
	 * @author ecp875
	 * @since 2014-07-07
	 * @param roleVO
	 * @throws AppException
	 */
	void addRole(RoleVO roleVO)throws AppException;
	/**
	 * 修改角色信息
	 * @author ecp875
	 * @since 2014-07-07
	 * @param roleVO
	 * @throws AppException
	 */
	void updateRole(RoleVO roleVO)throws AppException;
	
	/**
	 * 删除角色信息
	 * @author ecp875
	 * @since 2014-07-07
	 * @param roleVO
	 * @throws AppException
	 */
	void deleteRole(RoleVO roleVO)throws AppException;
	
	/**
	 * 获取角色列表
	 * @author ecp875
	 * @since 2014-07-07
	 * @return
	 * @throws AppException
	 */
	List<RoleVO> lookupRole()throws AppException;
	/**
	 * 获取用户当前的角色列表
	 * @author ecp875
	 * @since 2014-07-07
	 * @param userId
	 * @return
	 * @throws AppException
	 */
	List<RoleVO> currentRole(Integer userId)throws AppException;
	
}
