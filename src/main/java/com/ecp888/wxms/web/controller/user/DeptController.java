package com.ecp888.wxms.web.controller.user;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecp888.wxms.service.user.IDeptService;
import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.user.DeptVO;
import com.ecp888.wxms.web.controller.common.BaseController;

@Controller
@RequestMapping(value = "/dept")
public class DeptController  extends BaseController{

	Log log = LogFactory.getLog(DeptController.class);
	
	@Autowired
	private IDeptService deptService;
	
	@RequestMapping(value = "/listDept")
	public String listDept(ModelMap map,@ModelAttribute("deptForm") DeptForm deptForm
			)throws Exception{
		PageVO pageVO = deptForm.getPageVO();
		DeptVO deptVO = deptForm.getDeptVO();
		List<DeptVO> deptList = deptService.listDept(deptVO, pageVO);
		deptForm.setDeptList(deptList);
		map.put("deptForm", deptForm);
		return "user/listDept";
	}
	
	@RequestMapping(value = "/getDept")
	public String getDept(ModelMap map,@ModelAttribute("deptForm") DeptForm deptForm
			)throws Exception{
		DeptVO deptVO = deptForm.getDeptVO();
		DeptVO dept = deptService.getDept(deptVO);
		deptForm.setDeptVO(dept);
		map.put("deptForm", deptForm);
		return "user/viewDept";
	}
	
	@RequestMapping(value = "/saveDept")
	public String saveDept(ModelMap map,@ModelAttribute("deptForm") DeptForm deptForm
			)throws Exception{
		PageVO pageVO = deptForm.getPageVO();
		DeptVO deptVO = deptForm.getDeptVO();
		deptService.saveDept(deptVO);
		deptVO=new DeptVO();
		List<DeptVO> deptList = deptService.listDept(deptVO, pageVO);
		deptForm.setDeptList(deptList);
		deptForm.setDeptVO(new DeptVO());
		map.put("deptForm", deptForm);
		return "user/listDept";
	}
	
	@RequestMapping(value = "/editDept")
	public String editDept(ModelMap map,@ModelAttribute("deptForm") DeptForm deptForm
			)throws Exception{
		DeptVO deptVO = deptForm.getDeptVO();
		if(null != deptVO && null != deptVO.getDeptId()){
			deptVO= deptService.getDept(deptVO);
			deptForm.setDeptVO(deptVO);
		}
		map.put("deptForm", deptForm);
		return "user/editDept";
	}
	
	@RequestMapping(value = "/deleteDept")
	public String deleteDept(ModelMap map,@ModelAttribute("deptForm") DeptForm deptForm
			)throws Exception{
		PageVO pageVO = deptForm.getPageVO();
		DeptVO deptVO = deptForm.getDeptVO();
		deptService.deleteDept(deptVO);
		deptVO = new DeptVO();
		List<DeptVO> deptList = deptService.listDept(deptVO, pageVO);
		deptForm.setDeptList(deptList);
		map.put("deptForm", deptForm);
		return "user/listDept";
	}
	
}
