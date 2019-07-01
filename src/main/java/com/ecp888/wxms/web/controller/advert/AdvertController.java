package com.ecp888.wxms.web.controller.advert;

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

import com.ecp888.wxms.service.advert.IAdvertService;
import com.ecp888.wxms.utils.AppUtil;
import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.utils.exception.ExceptionContext;
import com.ecp888.wxms.utils.file.AttachmentUtil;
import com.ecp888.wxms.utils.file.ImportExcel;
import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.attachment.AttachmentVO;
import com.ecp888.wxms.vo.advert.AdvertVO;
import com.ecp888.wxms.vo.client.BaseResponse;
import com.ecp888.wxms.web.controller.common.BaseController;
/**
 * APP资讯信息Controller
 * @author ecp875
 * @since 2014-08-11
 */
@Controller
@RequestMapping(value = "/advert")
public class AdvertController extends BaseController{
	
	public static final String TARGETUSER_FILE_NAME = "TARGETUSER.xlsx";
	
	Log log = LogFactory.getLog(getClass());
	@Autowired
	IAdvertService advertService;

	@RequestMapping(value = "/listAdvert")
	public String listAdvert(ModelMap map,@ModelAttribute("advertForm") AdvertForm advertForm
			)throws Exception{
		PageVO pageVO = advertForm.getPageVO();
		AdvertVO advertVO = advertForm.getAdvertVO();
		List<AdvertVO> advertList = advertService.listAdvert(advertVO, pageVO);
		advertForm.setAdvertList(advertList);
		map.put("advertForm", advertForm);
		return "/advert/listAdvert";
	}
	
	@RequestMapping(value = "/orderAdvert")
	public String orderAdvert(ModelMap map,@ModelAttribute("advertForm") AdvertForm advertForm
			)throws Exception{
		AdvertVO advertVO = advertForm.getAdvertVO();
		PageVO pageVO = advertForm.getPageVO();
		List<AdvertVO> advertList = advertService.orderAdvert(advertVO, pageVO);
		advertForm.setAdvertList(advertList);
		map.put("advertForm", advertForm);
		return "/advert/listAdvert";
	}
	
	@RequestMapping(value = "/editAdvert")
	public String editAdvert(ModelMap map,@ModelAttribute("advertForm") AdvertForm advertForm
			)throws Exception{
		AdvertVO advertVO = advertForm.getAdvertVO();
		if(null!=advertVO){
			advertVO = advertService.getAdvert(advertVO);
			advertForm.setAdvertVO(advertVO);
			map.put("advertForm", advertForm);
		}
		return "/advert/editAdvert";
	}
	
	@RequestMapping(value = "/viewAdvert")
	public String viewAdvert(ModelMap map,@ModelAttribute("advertForm") AdvertForm advertForm
			)throws Exception{
		AdvertVO advertVO = advertForm.getAdvertVO();
		if(null!=advertVO){
			advertVO = advertService.getAdvert(advertVO);
			advertForm.setAdvertVO(advertVO);
			map.put("advertForm", advertForm);
		}
		return "/advert/viewAdvert";
	}
	
	@RequestMapping(value = "/saveAdvert")
	public String saveAdvert(ModelMap map,@ModelAttribute("advertForm") AdvertForm advertForm
			)throws Exception{
		PageVO pageVO = advertForm.getPageVO();
		AdvertVO advertVO = advertForm.getAdvertVO();
		
		advertService.saveAdvert(advertVO);
		
		advertVO = new AdvertVO();
		List<AdvertVO> advertList = advertService.listAdvert(advertVO, pageVO);
		advertForm.setAdvertList(advertList);
		map.put("advertForm", advertForm);
		return "/advert/listAdvert";
	}
	
	@RequestMapping(value = "/deleteAdvert")
	public String deleteAdvert(ModelMap map,@ModelAttribute("advertForm") AdvertForm advertForm
			)throws Exception{
		PageVO pageVO = advertForm.getPageVO();
		AdvertVO advertVO = advertForm.getAdvertVO();
		advertService.deleteAdvert(advertVO);
		
		advertVO = new AdvertVO();
		List<AdvertVO> advertList = advertService.listAdvert(advertVO, pageVO);
		advertForm.setAdvertList(advertList);
		map.put("advertForm", advertForm);
		return "/advert/listAdvert";
	}
	
	/**
	 * 发布APP资讯信息
	 * @author ecp875
	 * @since 2014-08-24
	 * @param map
	 * @param advertForm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/releaseAdvert")
	public String releaseAdvert(ModelMap map,@ModelAttribute("advertForm") AdvertForm advertForm
			)throws Exception{
		PageVO pageVO = advertForm.getPageVO();
		AdvertVO advertVO = advertForm.getAdvertVO();
		advertService.releaseAdvert(advertVO);
		
		advertVO = new AdvertVO();
		List<AdvertVO> advertList = advertService.listAdvert(advertVO, pageVO);
		advertForm.setAdvertList(advertList);
		map.put("advertForm", advertForm);
		return "/advert/listAdvert";
	}
	
	/**
	 * 显示资讯内容
	 * @param map
	 * @param advertForm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/showImage")
	public String showImage(@RequestParam("type") String type,ModelMap map,@ModelAttribute("advertForm") AdvertForm advertForm
			)throws Exception{
		AdvertVO advertVO = advertForm.getAdvertVO();
		advertVO = advertService.getAdvert(advertVO);
		advertForm.setAdvertVO(advertVO);
		if("1".equals(type)){
			advertVO.setHtmlUrl("");
		}else{
			advertVO.setImgUrl("");
		}
		return "/advert/showImage";
	}
	
	@RequestMapping(value = "/checkTitle")
	public @ResponseBody String checkTitle(ModelMap map, @RequestBody AdvertVO advertVO
			) throws Exception{
		String message = "";
		BaseResponse baseResponse = new BaseResponse();
		try{
			AdvertVO currentAdvertVO=advertService.getAdvertByTitle(advertVO);
			if(null==currentAdvertVO){
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
