package com.ecp888.wxms.web.controller.dialog;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ecp888.wxms.service.dialog.IDialogService;
import com.ecp888.wxms.utils.AppUtil;
import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.utils.exception.ExceptionContext;
import com.ecp888.wxms.utils.file.AttachmentUtil;
import com.ecp888.wxms.utils.file.ImportExcel;
import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.attachment.AttachmentVO;
import com.ecp888.wxms.vo.client.BaseResponse;
import com.ecp888.wxms.vo.dialog.DialogVO;
import com.ecp888.wxms.web.controller.common.BaseController;
/**
 * APP弹窗信息Controller
 * @author ecp875
 * @since 2014-08-11
 */
@Controller
@RequestMapping(value = "/dialog")
public class DialogController extends BaseController{
	
	public static final String TARGETUSER_FILE_NAME = "TARGETUSER.xlsx";
	
	Log log = LogFactory.getLog(getClass());
	@Autowired
	IDialogService dialogService;

	@RequestMapping(value = "/listDialog")
	public String listDialog(ModelMap map,@ModelAttribute("dialogForm") DialogForm dialogForm
			)throws Exception{
		PageVO pageVO = dialogForm.getPageVO();
		DialogVO dialogVO = dialogForm.getDialogVO();
		List<DialogVO> dialogList = dialogService.listDialog(dialogVO, pageVO);
		dialogForm.setDialogList(dialogList);
		map.put("dialogForm", dialogForm);
		return "/dialog/listDialog";
	}
	
	@RequestMapping(value = "/orderDialog")
	public String orderDialog(ModelMap map,@ModelAttribute("dialogForm") DialogForm dialogForm
			)throws Exception{
		DialogVO dialogVO = dialogForm.getDialogVO();
		PageVO pageVO = dialogForm.getPageVO();
		List<DialogVO> dialogList = dialogService.orderDialog(dialogVO, pageVO);
		dialogForm.setDialogList(dialogList);
		map.put("dialogForm", dialogForm);
		return "/dialog/listDialog";
	}
	
	@RequestMapping(value = "/editDialog")
	public String editDialog(ModelMap map,@ModelAttribute("dialogForm") DialogForm dialogForm
			)throws Exception{
		DialogVO dialogVO = dialogForm.getDialogVO();
		if(null!=dialogVO){
			dialogVO = dialogService.getDialog(dialogVO);
			dialogForm.setDialogVO(dialogVO);
			map.put("dialogForm", dialogForm);
		}
		return "/dialog/editDialog";
	}
	
	@RequestMapping(value = "/viewDialog")
	public String viewDialog(ModelMap map,@ModelAttribute("dialogForm") DialogForm dialogForm
			)throws Exception{
		DialogVO dialogVO = dialogForm.getDialogVO();
		if(null!=dialogVO){
			dialogVO = dialogService.getDialog(dialogVO);
			dialogForm.setDialogVO(dialogVO);
			map.put("dialogForm", dialogForm);
		}
		return "/dialog/viewDialog";
	}
	
	@RequestMapping(value = "/saveDialog")
	public String saveDialog(ModelMap map,@ModelAttribute("dialogForm") DialogForm dialogForm
			)throws Exception{
		PageVO pageVO = dialogForm.getPageVO();
		DialogVO dialogVO = dialogForm.getDialogVO();
		
		dialogService.saveDialog(dialogVO);
		
		dialogVO = new DialogVO();
		List<DialogVO> dialogList = dialogService.listDialog(dialogVO, pageVO);
		dialogForm.setDialogList(dialogList);
		map.put("dialogForm", dialogForm);
		return "/dialog/listDialog";
	}
	
	@RequestMapping(value = "/deleteDialog")
	public String deleteDialog(ModelMap map,@ModelAttribute("dialogForm") DialogForm dialogForm
			)throws Exception{
		PageVO pageVO = dialogForm.getPageVO();
		DialogVO dialogVO = dialogForm.getDialogVO();
		dialogService.deleteDialog(dialogVO);
		
		dialogVO = new DialogVO();
		List<DialogVO> dialogList = dialogService.listDialog(dialogVO, pageVO);
		dialogForm.setDialogList(dialogList);
		map.put("dialogForm", dialogForm);
		return "/dialog/listDialog";
	}
	
	/**
	 * 发布APP弹窗信息
	 * @author ecp875
	 * @since 2014-08-24
	 * @param map
	 * @param dialogForm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/releaseDialog")
	public String releaseDialog(ModelMap map,@ModelAttribute("dialogForm") DialogForm dialogForm
			)throws Exception{
		PageVO pageVO = dialogForm.getPageVO();
		DialogVO dialogVO = dialogForm.getDialogVO();
		dialogService.releaseDialog(dialogVO);
		
		dialogVO = new DialogVO();
		List<DialogVO> dialogList = dialogService.listDialog(dialogVO, pageVO);
		dialogForm.setDialogList(dialogList);
		map.put("dialogForm", dialogForm);
		return "/dialog/listDialog";
	}
	
	/**
	 * 显示弹窗内容
	 * @param map
	 * @param dialogForm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/showImage")
	public String showImage(@RequestParam("type") String type,ModelMap map,@ModelAttribute("dialogForm") DialogForm dialogForm
			)throws Exception{
		DialogVO dialogVO = dialogForm.getDialogVO();
		dialogVO = dialogService.getDialog(dialogVO);
		dialogForm.setDialogVO(dialogVO);
		if("1".equals(type)){
			dialogVO.setHtmlUrl("");
		}else{
			dialogVO.setImgUrl("");
		}
		return "/dialog/showImage";
	}
	
	@RequestMapping(value = "/checkTitle")
	public @ResponseBody String checkTitle(ModelMap map, @RequestBody DialogVO dialogVO
			) throws Exception{
		String message = "";
		BaseResponse baseResponse = new BaseResponse();
		try{
			DialogVO currentDialogVO=dialogService.getDialogByTitle(dialogVO);
			if(null==currentDialogVO){
				message = AppUtil.object2Json(baseResponse);
			}else{
				baseResponse.setStatusCode(BaseResponse.REQUEST_DATA_EXCEPTION);
				baseResponse.setErrorMsg("标题已经存在");
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
	
}
