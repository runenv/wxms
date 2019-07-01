package com.ecp888.wxms.dao.user;

import java.util.List;
import java.util.Map;

import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.vo.user.RoleRightVO;

public interface IRoleRightDAO {

	List<RoleRightVO> listRoleRight(Integer roleId)throws AppException;

	List<RoleRightVO> listRoleRightByRoleId(Integer roleId)throws AppException;
	
	List<RoleRightVO> listAllRight()throws AppException;
	
	/**
	 * 添加角色权限
	 * @author ecp875
	 * @since 2014-07-07
	 * @param roleRightList
	 * @return
	 * @throws AppException
	 */
	void addRoleRight(RoleRightVO roleRightVO)throws AppException;
	
	/**
	 * 删除角色权限
	 * @author ecp875
	 * @since 2014-07-07
	 * @param roleRightList
	 * @return
	 * @throws AppException
	 */
	void deleteRoleRight(int roleId)throws AppException;

	RoleRightVO getRoleRight(Map<String, String> map)throws AppException;
	
	void addRight(RoleRightVO roleRightVO)throws AppException;
	
	void deleteRight()throws AppException;

}
