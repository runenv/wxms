package com.ecp888.wxms.web.controller.user;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ecp888.wxms.service.user.IRoleService;
import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.user.RoleRightVO;
import com.ecp888.wxms.vo.user.RoleVO;
import com.ecp888.wxms.web.controller.common.BaseController;

@Controller
@RequestMapping(value = "/role")
public class RoleController  extends BaseController{

	Log log = LogFactory.getLog(RoleController.class);
	
	@Autowired
	private IRoleService roleService;
	
	@RequestMapping(value = "/listRole")
	public String listRole(ModelMap map,@ModelAttribute("roleForm") RoleForm roleForm
			)throws Exception{
		PageVO pageVO = roleForm.getPageVO();
		RoleVO roleVO = roleForm.getRoleVO();
		List<RoleVO> roleList = roleService.listRole(roleVO, pageVO);
		roleForm.setRoleList(roleList);
		map.put("roleForm", roleForm);
		return "user/listRole";
	}
	
	@RequestMapping(value = "/getRole")
	public String getRole(ModelMap map,@ModelAttribute("roleForm") RoleForm roleForm
			)throws Exception{
		RoleVO roleVO = roleForm.getRoleVO();
		roleForm.setRoleVO(roleService.getRole(roleVO));
		map.put("roleForm", roleForm);
		return "user/viewRole";
	}
	
	@RequestMapping(value = "/saveRole")
	public String saveRole(ModelMap map,@ModelAttribute("roleForm") RoleForm roleForm
			)throws Exception{
		PageVO pageVO = roleForm.getPageVO();
		RoleVO roleVO = roleForm.getRoleVO();
		roleService.saveRole(roleVO);
		roleVO = new RoleVO();
		List<RoleVO> roleList = roleService.listRole(roleVO, pageVO);
		roleForm.setRoleList(roleList);
		map.put("roleForm", roleForm);
		return "user/listRole";
	}
	
	@RequestMapping(value = "/editRole")
	public String editRole(ModelMap map,@ModelAttribute("roleForm") RoleForm roleForm
			)throws Exception{
		RoleVO roleVO = roleForm.getRoleVO();
		if(null != roleVO && null != roleVO.getRoleId()){
			roleVO= roleService.getRole(roleVO);
			roleForm.setRoleVO(roleVO);
		}
		map.put("roleForm", roleForm);
		return "user/editRole";
	}
	
	@RequestMapping(value = "/deleteRole")
	public String deleteRole(ModelMap map,@ModelAttribute("roleForm") RoleForm roleForm
			)throws Exception{
		PageVO pageVO = roleForm.getPageVO();
		RoleVO roleVO = roleForm.getRoleVO();
		roleService.deleteRole(roleVO);
		roleVO=new RoleVO();
		List<RoleVO> roleList = roleService.listRole(roleVO, pageVO);
		roleForm.setRoleList(roleList);
		map.put("roleForm", roleForm);
		return "user/listRole";
	}
	
	@RequestMapping(value = "/listRoleRight")
	public String listRoleRight(ModelMap map,@ModelAttribute("roleForm") RoleForm roleForm
			)throws Exception{
		RoleVO roleVO = roleForm.getRoleVO();
		RoleRightVO rrVO = roleVO.getRoleRightVO();
		if(null!= rrVO && null != roleVO.getRoleId()){
			rrVO.setRoleId(roleVO.getRoleId());
		}
		map.put("roleForm", roleForm);
		return "user/editRoleRight";
	}
	
	@RequestMapping(value = "/saveRoleRight")
	public String saveRoleRight(ModelMap map,@ModelAttribute("roleForm") RoleForm roleForm
			)throws Exception{
		RoleVO roleVO = roleForm.getRoleVO();
		roleService.saveRoleRight(roleVO);
		RoleRightVO rrVO = roleVO.getRoleRightVO();
		if(null!=rrVO && "".equals(rrVO.getRoleId())){
			rrVO.setRoleId(roleVO.getRoleId());
		}
		return "user/editRoleRight"; 
	}
	
	
	
}
