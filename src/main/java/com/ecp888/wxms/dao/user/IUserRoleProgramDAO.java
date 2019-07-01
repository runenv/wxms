package com.ecp888.wxms.dao.user;

import java.util.List;

import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.user.UserRoleProgramVO;
import com.ecp888.wxms.vo.user.UserVO;

/**
 * 用户角色权限DAO
 * @author ecp875
 * @since 2014-07-07
 */
public interface IUserRoleProgramDAO {
	
	int countUserRole(UserVO userVO)throws AppException;

	List<UserRoleProgramVO> listUserRole(UserVO userVO, PageVO pageVO)throws AppException;

	UserRoleProgramVO getUserRoleById(UserRoleProgramVO uRVO)throws AppException;
	
	UserRoleProgramVO getUserRoleByRoleId(UserRoleProgramVO uRVO)throws AppException;
	
	void updateUserRole(UserRoleProgramVO uRVO)throws AppException;

	void addUserRole(UserRoleProgramVO uRVO)throws AppException;

	void deleteUserRole(UserRoleProgramVO uRVO)throws AppException;
}
