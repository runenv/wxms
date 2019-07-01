package com.ecp888.wxms.web.controller.bulletin;

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
import com.ecp888.wxms.service.bulletin.IBulletinService;
import com.ecp888.wxms.utils.AppUtil;
import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.utils.exception.ExceptionContext;
import com.ecp888.wxms.utils.file.AttachmentUtil;
import com.ecp888.wxms.utils.file.ImportExcel;
import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.attachment.AttachmentVO;
import com.ecp888.wxms.vo.bulletin.BulletinVO;
import com.ecp888.wxms.vo.client.BaseResponse;
import com.ecp888.wxms.web.controller.common.BaseController;
/**
 * APP公告信息Controller
 * @author ecp875
 * @since 2014-08-11
 */
@Controller
@RequestMapping(value = "/bulletin")
public class BulletinController extends BaseController{
	
	public static final String TARGETUSER_FILE_NAME = "TARGETUSER.xlsx";
	
	Log log = LogFactory.getLog(getClass());
	@Autowired
	IBulletinService bulletinService;

	@RequestMapping(value = "/listBulletin")
	public String listBulletin(ModelMap map,@ModelAttribute("bulletinForm") BulletinForm bulletinForm
			)throws Exception{
		PageVO pageVO = bulletinForm.getPageVO();
		BulletinVO bulletinVO = bulletinForm.getBulletinVO();
		List<BulletinVO> bulletinList = bulletinService.listBulletin(bulletinVO, pageVO);
		bulletinForm.setBulletinList(bulletinList);
		map.put("bulletinForm", bulletinForm);
		return "/bulletin/listBulletin";
	}
	
	@RequestMapping(value = "/orderBulletin")
	public String orderBulletin(ModelMap map,@ModelAttribute("bulletinForm") BulletinForm bulletinForm
			)throws Exception{
		BulletinVO bulletinVO = bulletinForm.getBulletinVO();
		PageVO pageVO = bulletinForm.getPageVO();
		List<BulletinVO> bulletinList = bulletinService.orderBulletin(bulletinVO, pageVO);
		bulletinForm.setBulletinList(bulletinList);
		map.put("bulletinForm", bulletinForm);
		return "/bulletin/listBulletin";
	}
	
	@RequestMapping(value = "/editBulletin")
	public String editBulletin(ModelMap map,@ModelAttribute("bulletinForm") BulletinForm bulletinForm
			)throws Exception{
		BulletinVO bulletinVO = bulletinForm.getBulletinVO();
		if(null!=bulletinVO){
			bulletinVO = bulletinService.getBulletin(bulletinVO);
			bulletinForm.setBulletinVO(bulletinVO);
			map.put("bulletinForm", bulletinForm);
		}
		return "/bulletin/editBulletin";
	}
	
	@RequestMapping(value = "/viewBulletin")
	public String viewBulletin(ModelMap map,@ModelAttribute("bulletinForm") BulletinForm bulletinForm
			)throws Exception{
		BulletinVO bulletinVO = bulletinForm.getBulletinVO();
		if(null!=bulletinVO){
			bulletinVO = bulletinService.getBulletin(bulletinVO);
			bulletinForm.setBulletinVO(bulletinVO);
			map.put("bulletinForm", bulletinForm);
		}
		return "/bulletin/viewBulletin";
	}
	
	@RequestMapping(value = "/saveBulletin")
	public String saveBulletin(ModelMap map,@ModelAttribute("bulletinForm") BulletinForm bulletinForm
			)throws Exception{
		PageVO pageVO = bulletinForm.getPageVO();
		BulletinVO bulletinVO = bulletinForm.getBulletinVO();
		
		bulletinService.saveBulletin(bulletinVO);
		
		bulletinVO = new BulletinVO();
		List<BulletinVO> bulletinList = bulletinService.listBulletin(bulletinVO, pageVO);
		bulletinForm.setBulletinList(bulletinList);
		map.put("bulletinForm", bulletinForm);
		return "/bulletin/listBulletin";
	}
	
	@RequestMapping(value = "/deleteBulletin")
	public String deleteBulletin(ModelMap map,@ModelAttribute("bulletinForm") BulletinForm bulletinForm
			)throws Exception{
		PageVO pageVO = bulletinForm.getPageVO();
		BulletinVO bulletinVO = bulletinForm.getBulletinVO();
		bulletinService.deleteBulletin(bulletinVO);
		
		bulletinVO = new BulletinVO();
		List<BulletinVO> bulletinList = bulletinService.listBulletin(bulletinVO, pageVO);
		bulletinForm.setBulletinList(bulletinList);
		map.put("bulletinForm", bulletinForm);
		return "/bulletin/listBulletin";
	}
	
	/**
	 * 发布APP公告信息
	 * @author ecp875
	 * @since 2014-08-24
	 * @param map
	 * @param bulletinForm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/releaseBulletin")
	public String releaseBulletin(ModelMap map,@ModelAttribute("bulletinForm") BulletinForm bulletinForm
			)throws Exception{
		PageVO pageVO = bulletinForm.getPageVO();
		BulletinVO bulletinVO = bulletinForm.getBulletinVO();
		bulletinService.releaseBulletin(bulletinVO);
		
		bulletinVO = new BulletinVO();
		List<BulletinVO> bulletinList = bulletinService.listBulletin(bulletinVO, pageVO);
		bulletinForm.setBulletinList(bulletinList);
		map.put("bulletinForm", bulletinForm);
		return "/bulletin/listBulletin";
	}
	
	/**
	 * 显示图片内容
	 * @param map
	 * @param bulletinForm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/showImage")
	public String showImage(@RequestParam("type") String type,ModelMap map,@ModelAttribute("bulletinForm") BulletinForm bulletinForm
			)throws Exception{
		BulletinVO bulletinVO = bulletinForm.getBulletinVO();
		bulletinVO = bulletinService.getBulletin(bulletinVO);
		bulletinForm.setBulletinVO(bulletinVO);
		if("1".equals(type)){
			bulletinVO.setHtmlUrl("");
		}else{
			bulletinVO.setImgUrl("");
		}
		return "/bulletin/showImage";
	}
	
	@RequestMapping(value = "/checkTitle")
	public @ResponseBody String checkTitle(ModelMap map, @RequestBody BulletinVO bulletinVO
			) throws Exception{
		String message = "";
		BaseResponse baseResponse = new BaseResponse();
		try{
			BulletinVO currentBulletinVO=bulletinService.getBulletinByTitle(bulletinVO);
			if(null==currentBulletinVO){
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
