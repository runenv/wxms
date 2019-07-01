package com.ecp888.wxms.service.impl.user;

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
import com.ecp888.wxms.service.user.IRoleService;
import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.user.RoleRightVO;
import com.ecp888.wxms.vo.user.RoleVO;
import com.ecp888.wxms.vo.user.UserVO;
/**
 * 角色信息服务类
 * @author ecp875
 * @since 2014-07-07
 */
@Service("roleService")
@AppResource(code="RoleService",desc="角色管理")
public class RoleService implements IRoleService {
	@Autowired
	IRoleDAO roleDAO;
	@Autowired
	IRoleRightDAO roleRightDAO;
	
	public List<RoleVO> listRole(RoleVO roleVO, PageVO pageVO)
			throws AppException{
		int total = roleDAO.countRole(roleVO);
		pageVO.setRecordTotal(total);
		return roleDAO.listRole(roleVO,pageVO);
	}

	public RoleVO getRole(RoleVO roleVO) throws AppException {
		return roleDAO.getRoleById(roleVO);
	}

	public void saveRole(RoleVO roleVO) throws AppException {
		RoleVO curRole = roleDAO.getRoleByName(roleVO);		
		if("".equals(roleVO.getRoleId()) || null==roleVO.getRoleId()){
			if (curRole!=null){
				throw new FunctionException(ExConstants.F_USER_1004);
			}
			addRole(roleVO);
		}else{
			updateRole(roleVO);
		}
	}
	
	@AppOperate(code="add",desc="添加角色")
	public void addRole(RoleVO roleVO) throws AppException {
		roleDAO.addRole(roleVO);
	}
	
	@AppOperate(code="update",desc="更新角色")
	public void updateRole(RoleVO roleVO) throws AppException {
		roleDAO.updateRole(roleVO);
	}

	@AppOperate(code="delete",desc="删除角色")
	public void deleteRole(RoleVO roleVO) throws AppException {
		if(WxConstants.APP_RESOURCE_POINT > roleVO.getRoleId()){
			throw new FunctionException(ExConstants.SYS_COMMON_10008);
		}
		roleDAO.deleteRole(roleVO);
	}

	public List<RoleVO> lookupRole() throws AppException {
		return roleDAO.lookupRole() ;
	}
	
	public List<RoleVO> currentRole() throws AppException {
		UserVO curUserVO=AppUtil.getCurrentUser();
		return roleDAO.currentRole(curUserVO.getUserId());
	}

	public RoleVO getRole(Integer roleId) throws AppException {
		RoleVO roleVO=new RoleVO();
		roleVO.setRoleId(roleId);
		return roleDAO.getRoleById(roleVO);
	}

	@AppOperate(code="updateRight",desc="角色授权")
	public void saveRoleRight(RoleVO roleVO) throws AppException {
		UserVO curUserVO=AppUtil.getCurrentUser();
		RoleRightVO roleRightVO =roleVO.getRoleRightVO();
		if(null!=roleRightVO){
			roleRightDAO.deleteRoleRight(roleRightVO.getRoleId());
			String rights=roleRightVO.getRightIds();
			if(AppUtil.isNotEmpty(rights)){
				String[] rightArr=rights.split(",");
				for(int i=0;i<rightArr.length;i++){
					RoleRightVO rightVO=new RoleRightVO();
					rightVO.setRoleId(roleRightVO.getRoleId());
					rightVO.setRightId(Integer.valueOf(rightArr[i]));
					rightVO.setCreateUser(curUserVO.getUserId());
					rightVO.setUpdateUser(curUserVO.getUserId());
					roleRightDAO.addRoleRight(rightVO);
				}
			}
		}
		
	}

}
