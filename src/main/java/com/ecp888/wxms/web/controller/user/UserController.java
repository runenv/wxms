package com.ecp888.wxms.web.controller.user;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecp888.wxms.service.user.IUserRoleProgramService;
import com.ecp888.wxms.service.user.IUserService;
import com.ecp888.wxms.utils.AppUtil;
import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.utils.exception.ExceptionContext;
import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.client.BaseResponse;
import com.ecp888.wxms.vo.user.UserRoleProgramVO;
import com.ecp888.wxms.vo.user.UserVO;
import com.ecp888.wxms.web.controller.common.BaseController;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController{

	Log log = LogFactory.getLog(UserController.class);
	
	@Autowired
	IUserService userService;
	@Autowired
	private IUserRoleProgramService userRoleProgramService;
	
	@RequestMapping(value = "/toLogin")
	public String toLogin(ModelMap map,@ModelAttribute("userForm") UserForm userForm
			 ) throws Exception{
		
		return "user/loginUser";
	}
	
	@RequestMapping(value = "/login")
	public String login(ModelMap map, @ModelAttribute("userVO") UserVO userVO,
			HttpServletResponse response) throws Exception{
		log.info("应用上下文路径：" + request.getContextPath());
		UserVO currentUserVO=userService.login(userVO);
		if(null!=currentUserVO){
			session.setAttribute("userSession", currentUserVO);
			AppUtil.setCurrentUser(currentUserVO);
			response.sendRedirect(AppUtil.isNotEmpty(request.getContextPath()) ?request.getContextPath():"../");
			return null;
		}
		return "user/loginUser";
	}
	
	@RequestMapping(value = "/loginByAjax")
	public @ResponseBody UserVO loginByAjax(ModelMap map, @RequestBody UserVO userVO
			) throws Exception{
		UserVO currentUserVO=userService.login(userVO);
		if(null!=currentUserVO){
			session.setAttribute("userSession", currentUserVO);
			AppUtil.setCurrentUser(currentUserVO);
		}
		return currentUserVO;
	}
	
	@RequestMapping(value = "/validateLogin")
	public @ResponseBody String validateLogin(ModelMap map, @RequestBody UserVO userVO
			) throws Exception{
		String message = "";
		BaseResponse baseResponse = new BaseResponse();
		try{
			UserVO currentUserVO=userService.login(userVO);
			if(null!=currentUserVO){
				message = AppUtil.object2Json(baseResponse);
			}else{
				baseResponse.setStatusCode(BaseResponse.REQUEST_DATA_EXCEPTION);
				baseResponse.setErrorMsg("<span style=\"color:red;\">用户名不存在或密码错误!</span>");
				message = AppUtil.object2Json(baseResponse);
			}
		}catch(AppException e){
			ExceptionContext exContext = ((AppException) e).getExceptionContext();
			baseResponse.setStatusCode(BaseResponse.REQUEST_DATA_EXCEPTION);
			baseResponse.setErrorMsg("<span style=\"color:red;\">"+exContext.getDescription()+"</span>");
			message = AppUtil.object2Json(baseResponse);
		}
		return message;
	}
	
	@RequestMapping(value = "/checkUserName")
	public @ResponseBody String checkUserName(ModelMap map, @RequestBody UserVO userVO
			) throws Exception{
		String message = "";
		BaseResponse baseResponse = new BaseResponse();
		try{
			UserVO currentUserVO=userService.getUserByName(userVO);
			if(null==currentUserVO){
				message = AppUtil.object2Json(baseResponse);
			}else{
				baseResponse.setStatusCode(BaseResponse.REQUEST_DATA_EXCEPTION);
				baseResponse.setErrorMsg("用户名已经存在");
				message = AppUtil.object2Json(baseResponse);
			}
		}catch(AppException e){
			ExceptionContext exContext = ((AppException) e).getExceptionContext();
			baseResponse.setStatusCode(BaseResponse.REQUEST_DATA_EXCEPTION);
			baseResponse.setErrorMsg("<span style=\"color:red;\">"+exContext.getDescription()+"</span>");
			message = AppUtil.object2Json(baseResponse);
		}
		return message;
	}
	
	@RequestMapping(value = "/logout")
	public String logout(ModelMap map,@ModelAttribute("userForm") UserForm userForm
			) throws Exception{
		session.removeAttribute("userSession");
		AppUtil.setCurrentUser(new UserVO());
		return "/user/loginUser";
	}
	
	
	@RequestMapping(value = "/listUser"/*,method = RequestMethod.GET*/)
	public String listUser(ModelMap map,@ModelAttribute("userForm") UserForm userForm
			) throws Exception{
		PageVO pageVO = userForm.getPageVO();
		UserVO userVO = userForm.getUserVO();
		List<UserVO> userList = userService.listUser(userVO, pageVO);
		userForm.setUserList(userList);
		map.put("userForm", userForm);
		return "/user/listUser";
	}
	
	@RequestMapping(value = "/switchRole")
	public String switchRole(ModelMap map,@ModelAttribute("userForm") UserForm userForm,
			HttpServletResponse response)throws Exception{
		String roleId = request.getParameter("roleId");
		UserVO currentUserVO= getUserInfo();
		currentUserVO.setCurrentRoleId(Integer.valueOf(roleId));
		userService.synchCurrentRole(currentUserVO);
		AppUtil.setCurrentUser(currentUserVO);
		
		PageVO pageVO = userForm.getPageVO();
		UserVO userVO = userForm.getUserVO();
		List<UserVO> userList = userService.listUser(userVO, pageVO);
		userForm.setUserList(userList);
		map.put("userForm", userForm);
		
		response.sendRedirect(AppUtil.isNotEmpty(request.getContextPath()) ?request.getContextPath():"../");
		return null;
	}
	
	@RequestMapping(value = "/add")
	public String editUser(ModelMap map,@ModelAttribute("userForm") UserForm userForm
			)throws Exception{
		UserVO userVO = userForm.getUserVO();
		if(null!=userVO 
				&& null!=userVO.getUserId()
				&& !"".equals(userVO.getUserId())){
			userVO= userService.getUser(userVO);
			userForm.setUserVO(userVO);
			map.put("userForm", userForm);
		}
		return "/user/editUser";
	}
	
	@RequestMapping(value = "/save")
	public String saveUser(ModelMap map,@ModelAttribute("userForm") UserForm userForm
			)throws Exception{
		UserVO userVO = userForm.getUserVO();
		PageVO pageVO = userForm.getPageVO();
		userService.saveUser(userVO);
		userVO=new UserVO();
		List<UserVO> userList = userService.listUser(userVO, pageVO);
		userForm.setUserList(userList);
		return "/user/listUser";
	}
	
	@RequestMapping(value = "/getUser")
	public String getUser(ModelMap map,@ModelAttribute("userForm") UserForm userForm
			)throws Exception{
		UserVO userVO = userForm.getUserVO();
		userService.getUser(userVO);
		return "/user/switchRoleUser";
	}
	
	@RequestMapping(value = "/delete")
	public String deleteUser(ModelMap map,@ModelAttribute("userForm") UserForm userForm
			)throws Exception{
		UserVO userVO = userForm.getUserVO();
		PageVO pageVO = userForm.getPageVO();
		userService.deleteUser(userVO);
		userVO=new UserVO();
		List<UserVO> userList=userService.listUser(userVO, pageVO);
		userForm.setUserList(userList);
		return "/user/listUser";
	}
	
	@RequestMapping(value = "/listUserRole")
	public String listUserRole(ModelMap map,@ModelAttribute("userForm") UserForm userForm
			)throws Exception{
		UserVO userVO = userForm.getUserVO();
		PageVO pageVO = userForm.getPageVO();
		List<UserRoleProgramVO> userRoleList
			= userRoleProgramService.listUserRole(userVO, pageVO);
	    userVO.setUserRoleProgramList(userRoleList);
	    map.put("userForm", userForm);
		return "/user/listUserRole";
	}
	
	@RequestMapping(value = "/editUserRole")
	public String editUserRole(ModelMap map,@ModelAttribute("userForm") UserForm userForm
			)throws Exception{
		UserVO userVO = userForm.getUserVO();
		UserRoleProgramVO urVO =userVO.getUserRoleProgramVO();
		if(null!=userVO && null!=urVO
				&& null!=urVO.getUserRoleProgramId()){
			 urVO =userRoleProgramService.getUserRoleById(userVO);
		}
		urVO.setUserId(userVO.getUserId());
		userVO.setUserRoleProgramVO(urVO);
		map.put("userForm", userForm);
		return "/user/editUserRole";
	}
	
	@RequestMapping(value = "/saveUserRole")
	public String saveUserRole(ModelMap map,@ModelAttribute("userForm") UserForm userForm,
			HttpServletResponse response)throws Exception{
		UserVO userVO = userForm.getUserVO();
		PageVO pageVO = userForm.getPageVO();
		userRoleProgramService.saveUserRole(userVO);
		List<UserRoleProgramVO> userRoleList
			=userRoleProgramService.listUserRole(userVO, pageVO);
		userVO.setUserRoleProgramList(userRoleList);
		if(null!=userRoleList){
			userVO.setUserId(userRoleList.get(0).getUserId());
		}
//		return "/user/listUser";
		response.sendRedirect(request.getContextPath() + "/user/listUser");
		return null;
	}
	
	@RequestMapping(value = "/deleteUserRole")
	public String deleteUserRole(ModelMap map,@ModelAttribute("userForm") UserForm userForm,
			HttpServletResponse response)throws Exception{
		UserVO userVO = userForm.getUserVO();
		PageVO pageVO = userForm.getPageVO();
		userRoleProgramService.deleteUserRole(userVO);
		List<UserRoleProgramVO> userRoleList
			=userRoleProgramService.listUserRole(userVO, pageVO);
		userVO.setUserRoleProgramList(userRoleList);
//		return "/user/listUser";
		response.sendRedirect(request.getContextPath() + "/user/listUser");
		return null;
	}
	
	public String selectUserList(ModelMap map,@ModelAttribute("userForm") UserForm userForm
			)throws Exception{
		UserVO userVO = userForm.getUserVO();
		PageVO pageVO = userForm.getPageVO();
		List<UserVO> userList=userService.listUser(userVO, pageVO);
		userForm.setUserList(userList);
		return "/user/switchRoleUser";
	}
	
}
