package com.ecp888.wxms.dao.user;

import java.util.List;

import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.user.UserVO;
/**
 * 用户信息DAO
 * @author ecp875
 * @since 2014-07-07
 */
public interface IUserDAO {

	UserVO findUserByName(UserVO userVO)throws AppException ;
	
	UserVO getUserById(UserVO userVO)throws AppException;
	
	int countUser(UserVO userVO)throws AppException;

	List<UserVO> listUser(UserVO userVO,PageVO pageVO)throws AppException ;

	void addUser(UserVO userVO)throws AppException;

	void updateUser(UserVO userVO)throws AppException;

	void deleteUser(UserVO userVO)throws AppException;
}
