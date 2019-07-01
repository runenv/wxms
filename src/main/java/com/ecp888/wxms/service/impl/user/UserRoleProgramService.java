package com.ecp888.wxms.service.impl.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecp888.wxms.utils.AppUtil;
import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.utils.exception.ExConstants;
import com.ecp888.wxms.utils.exception.FunctionException;
import com.ecp888.wxms.utils.interfaces.AppOperate;
import com.ecp888.wxms.utils.interfaces.AppResource;
import com.ecp888.wxms.dao.user.IUserRoleProgramDAO;
import com.ecp888.wxms.service.user.IUserRoleProgramService;
import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.user.UserRoleProgramVO;
import com.ecp888.wxms.vo.user.UserVO;

@Service("userRoleProgramService")
@AppResource(code="UserRoleProgramService",desc="用户角色管理")
public class UserRoleProgramService implements IUserRoleProgramService {

	@Autowired
	IUserRoleProgramDAO userRoleProgramDAO;

	public List<UserRoleProgramVO> listUserRole(UserVO userVO, PageVO pageVO)
			throws AppException {
		if(null==userVO.getUserId()){
			userVO.setUserId(userVO.getUserRoleProgramVO().getUserId());
		}
		int total = userRoleProgramDAO.countUserRole(userVO);
		pageVO.setRecordTotal(total);
		return userRoleProgramDAO.listUserRole(userVO,pageVO);
	}

	@AppOperate(code="update",desc="更新用户角色")
	public void saveUserRole(UserVO userVO) throws AppException {
		UserVO curUserVO=AppUtil.getCurrentUser();
		UserRoleProgramVO uRVO=userVO.getUserRoleProgramVO();
		uRVO.setCreateUser(curUserVO.getUserId());
		uRVO.setUpdateUser(curUserVO.getUserId());
		if(null!=uRVO && null!=uRVO.getUserRoleProgramId()){
			userRoleProgramDAO.updateUserRole(uRVO);
		}else{
			if(null != userRoleProgramDAO.getUserRoleByRoleId(uRVO)){
				throw new FunctionException(ExConstants.F_USER_1007);
			}
			userRoleProgramDAO.addUserRole(uRVO);
		}
	}

	@AppOperate(code="delete",desc="删除用户角色")
	public void deleteUserRole(UserVO userVO) throws AppException {
		UserRoleProgramVO uRVO=userVO.getUserRoleProgramVO();
		userRoleProgramDAO.deleteUserRole(uRVO);
	}

	public UserRoleProgramVO getUserRoleById(UserVO userVO) throws AppException {
		UserRoleProgramVO uRVO=userVO.getUserRoleProgramVO();
		return userRoleProgramDAO.getUserRoleById(uRVO);
	}

	public UserRoleProgramVO getUserRoleById(Integer userRoleId) throws AppException {
		UserVO curUserVO=AppUtil.getCurrentUser();
		UserRoleProgramVO uRVO=new UserRoleProgramVO();
		uRVO.setUserRoleProgramId(userRoleId);
		uRVO.setUserId(curUserVO.getUserId());
		return userRoleProgramDAO.getUserRoleById(uRVO);
	}
}
