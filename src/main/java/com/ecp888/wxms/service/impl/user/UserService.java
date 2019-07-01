package com.ecp888.wxms.service.impl.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecp888.wxms.utils.AppUtil;
import com.ecp888.wxms.utils.WxConstants;
import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.utils.exception.ExConstants;
import com.ecp888.wxms.utils.exception.FunctionException;
import com.ecp888.wxms.utils.interfaces.AppOperate;
import com.ecp888.wxms.utils.interfaces.AppResource;
import com.ecp888.wxms.dao.user.IRoleDAO;
import com.ecp888.wxms.dao.user.IRoleRightDAO;
import com.ecp888.wxms.dao.user.IUserDAO;
import com.ecp888.wxms.service.user.IUserService;
import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.user.RoleRightVO;
import com.ecp888.wxms.vo.user.RoleVO;
import com.ecp888.wxms.vo.user.UserRoleProgramVO;
import com.ecp888.wxms.vo.user.UserVO;
/**
 * 用户信息服务类
 * @author ecp875
 * @since 2014-07-07
 */
@Service("userService")
@AppResource(code="UserService",desc="用户管理")
public class UserService implements IUserService {
	@Autowired
	IUserDAO userDAO;
	@Autowired
	IRoleDAO roleDAO;
	@Autowired
	IRoleRightDAO roleRightDAO;
	
	public UserVO login(UserVO userVO) throws AppException{
		UserVO userVO1 = userDAO.findUserByName(userVO);
		if(null==userVO1 || userVO1.getUserPassword().equals("")
				|| !userVO1.getUserPassword().equals(userVO.getUserPassword())){
			return null;
		}
		//设置登录用户的角色列表
		List<RoleVO> currentRole=roleDAO.currentRole(userVO1.getUserId());
		if(null!=currentRole && !currentRole.isEmpty()){
			userVO1.setCurrentRoleId(currentRole.get(0).getRoleId());
			synchCurrentRole(userVO1);
		}else{
			throw new FunctionException(ExConstants.F_USER_1006);
		}
		return userVO1;
	}
	
	/**
	 * 设置登录用户的默认角色及角色对应的权限列表
	 * @author ecp875
	 * @since 2014-07-07
	 * @param userVO
	 * @throws AppException
	 */
	public void synchCurrentRole(UserVO userVO) throws AppException{
		List<RoleVO> currentRole=roleDAO.currentRole(userVO.getUserId());
		if(null!=currentRole && !currentRole.isEmpty()){
			List<UserRoleProgramVO> userRoleProgramList=new ArrayList<UserRoleProgramVO>();
			for(RoleVO roleVO :currentRole){
				UserRoleProgramVO uRoleProgramVO=new UserRoleProgramVO();
				uRoleProgramVO.setUserId(userVO.getUserId());
				//角色拥有的权限列表
				List<RoleRightVO> roleRightList= roleRightDAO.listRoleRightByRoleId(roleVO.getRoleId());
				roleVO.setRoleRightList(roleRightList);
				//所有的权限列表
				roleVO.setAllRightList(roleRightDAO.listAllRight());
				uRoleProgramVO.setRoleVO(roleVO);
				if(userVO.getCurrentRoleId()==roleVO.getRoleId()){
					userVO.setUserRoleProgramVO(uRoleProgramVO);
					userRoleProgramList.add(uRoleProgramVO);
				}
			}
			userVO.setUserRoleProgramList(userRoleProgramList);
		}
	}
	
	public List<UserVO> listUser(UserVO userVO,PageVO pageVO) throws AppException {
		int total = userDAO.countUser(userVO);
		pageVO.setRecordTotal(total);
		return userDAO.listUser(userVO,pageVO);
	}
	
	public UserVO getUser(UserVO userVO) throws AppException {
		return userDAO.getUserById(userVO);
	}
	
	public UserVO getUserByName(UserVO userVO) throws AppException {
		return userDAO.findUserByName(userVO);
	}
	
	@AppOperate(code="save",desc="保存用户")
	public void saveUser(UserVO userVO) throws AppException {
		UserVO curUser = userDAO.findUserByName(userVO);		
		if("".equals(userVO.getUserId())
				|| null==userVO.getUserId()){//用户ID不存在，添加用户	
			if (curUser!=null){
				throw new FunctionException(ExConstants.F_USER_1001,"saveUser");
			}
			addUser(userVO);
		}else{ // 用户ID已存在，更新用户信息
			updateUser(userVO);
		}
	}
	
	public void addUser(UserVO userVO) throws AppException {
		userDAO.addUser(userVO);
	}
	
	public void updateUser(UserVO userVO) throws AppException {
		userDAO.updateUser(userVO);
	}
	
	@AppOperate(code="delete",desc="删除用户")
	public void deleteUser(UserVO userVO) throws AppException {
		if(WxConstants.APP_RESOURCE_POINT > userVO.getUserId()){
			throw new FunctionException(ExConstants.SYS_COMMON_10008);
		}
		userDAO.deleteUser(userVO);
	}

	public boolean checkUserSession() throws AppException {
		boolean result = false;
		UserVO userVO = AppUtil.getCurrentUser();
		if(null!=userVO && AppUtil.isNotEmpty(userVO.getUserName())){
			result=true;
		}
		return result;
	}
	
	

}
