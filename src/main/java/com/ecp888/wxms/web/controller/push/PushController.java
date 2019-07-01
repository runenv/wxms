package com.ecp888.wxms.web.controller.push;

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
import com.ecp888.wxms.service.push.IPushService;
import com.ecp888.wxms.utils.AppUtil;
import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.utils.exception.ExceptionContext;
import com.ecp888.wxms.utils.file.AttachmentUtil;
import com.ecp888.wxms.utils.file.ImportExcel;
import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.attachment.AttachmentVO;
import com.ecp888.wxms.vo.client.BaseResponse;
import com.ecp888.wxms.vo.push.PushStatisticVO;
import com.ecp888.wxms.vo.push.PushVO;
import com.ecp888.wxms.web.controller.common.BaseController;
/**
 * APP Push信息Controller
 * @author ecp875
 * @since 2014-08-24
 */
@Controller
@RequestMapping(value = "/push")
public class PushController extends BaseController{
	
	public static final String TARGETUSER_FILE_NAME = "TARGETUSER.xlsx";
	
	Log log = LogFactory.getLog(getClass());
	@Autowired
	IPushService pushService;

	@RequestMapping(value = "/listPush")
	public String listPush(ModelMap map,@ModelAttribute("pushForm") PushForm pushForm
			)throws Exception{
		PageVO pageVO = pushForm.getPageVO();
		PushVO pushVO = pushForm.getPushVO();
		List<PushVO> pushList = pushService.listPush(pushVO, pageVO);
		pushForm.setPushList(pushList);
		map.put("pushForm", pushForm);
		return "/push/listPush";
	}
	
	@RequestMapping(value = "/orderPush")
	public String orderPush(ModelMap map,@ModelAttribute("pushForm") PushForm pushForm
			)throws Exception{
		PushVO pushVO = pushForm.getPushVO();
		PageVO pageVO = pushForm.getPageVO();
		List<PushVO> pushList = pushService.orderPush(pushVO, pageVO);
		pushForm.setPushList(pushList);
		map.put("pushForm", pushForm);
		return "/push/listPush";
	}
	
	@RequestMapping(value = "/editPush")
	public String editPush(ModelMap map,@ModelAttribute("pushForm") PushForm pushForm
			)throws Exception{
		PushVO pushVO = pushForm.getPushVO();
		if(null!=pushVO){
			pushVO = pushService.getPush(pushVO);
			pushForm.setPushVO(pushVO);
			map.put("pushForm", pushForm);
		}
		return "/push/editPush";
	}
	
	@RequestMapping(value = "/viewPush")
	public String viewPush(ModelMap map,@ModelAttribute("pushForm") PushForm pushForm
			)throws Exception{
		PushVO pushVO = pushForm.getPushVO();
		if(null!=pushVO){
			pushVO = pushService.getPush(pushVO);
			pushForm.setPushVO(pushVO);
			map.put("pushForm", pushForm);
		}
		return "/push/viewPush";
	}
	
	@RequestMapping(value = "/savePush")
	public String savePush(ModelMap map,@ModelAttribute("pushForm") PushForm pushForm
			)throws Exception{
		PageVO pageVO = pushForm.getPageVO();
		PushVO pushVO = pushForm.getPushVO();
		
		pushService.savePush(pushVO);
		
		pushVO = new PushVO();
		List<PushVO> pushList = pushService.listPush(pushVO, pageVO);
		pushForm.setPushList(pushList);
		map.put("pushForm", pushForm);
		return "/push/listPush";
	}
	
	@RequestMapping(value = "/deletePush")
	public String deletePush(ModelMap map,@ModelAttribute("pushForm") PushForm pushForm
			)throws Exception{
		PageVO pageVO = pushForm.getPageVO();
		PushVO pushVO = pushForm.getPushVO();
		pushService.deletePush(pushVO);
		
		pushVO = new PushVO();
		List<PushVO> pushList = pushService.listPush(pushVO, pageVO);
		pushForm.setPushList(pushList);
		map.put("pushForm", pushForm);
		return "/push/listPush";
	}
	
	/**
	 * 发布APP Push信息
	 * @author ecp875
	 * @since 2014-08-24
	 * @param map
	 * @param pushForm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/releasePush")
	public String releasePush(ModelMap map,@ModelAttribute("pushForm") PushForm pushForm
			)throws Exception{
		PageVO pageVO = pushForm.getPageVO();
		PushVO pushVO = pushForm.getPushVO();
		pushService.releasePush(pushVO);
		
		pushVO = new PushVO();
		List<PushVO> pushList = pushService.listPush(pushVO, pageVO);
		pushForm.setPushList(pushList);
		map.put("pushForm", pushForm);
		return "/push/listPush";
	}
	
	/**
	 * 显示Push图片内容
	 * @param map
	 * @param pushForm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/showImage")
	public String showImage(@RequestParam("type") String type,ModelMap map,@ModelAttribute("pushForm") PushForm pushForm
			)throws Exception{
		PushVO pushVO = pushForm.getPushVO();
		pushVO = pushService.getPush(pushVO);
		pushForm.setPushVO(pushVO);
		if("1".equals(type)){
			pushVO.setHtmlUrl("");
		}else{
			pushVO.setImgUrl("");
		}
		return "/push/showImage";
	}
	
	@RequestMapping(value = "/checkTitle")
	public @ResponseBody String checkTitle(ModelMap map, @RequestBody PushVO pushVO
			) throws Exception{
		String message = "";
		BaseResponse baseResponse = new BaseResponse();
		try{
			PushVO currentPushVO=pushService.getPushByTitle(pushVO);
			if(null==currentPushVO){
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
	
	/**
	 * 发布APP Push信息
	 * @author ecp875
	 * @since 2014-09-10
	 * @param map
	 * @param pushForm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/statisticPush")
	public String statisticPush(ModelMap map,@ModelAttribute("pushForm") PushForm pushForm
			)throws Exception{
		PushStatisticVO pushStatisticVO = pushForm.getPushStatisticVO();
		List<PushStatisticVO> pushStatisticList = pushService.statisticPush(pushStatisticVO);
		
		pushForm.setPushStatisticList(pushStatisticList);
		map.put("pushForm", pushForm);
		return "/push/statisticPush";
	}
	
}
