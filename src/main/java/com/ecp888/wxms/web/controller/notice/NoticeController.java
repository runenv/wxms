package com.ecp888.wxms.web.controller.notice;

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
import com.ecp888.wxms.service.notice.INoticeService;
import com.ecp888.wxms.utils.AppUtil;
import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.utils.exception.ExceptionContext;
import com.ecp888.wxms.utils.file.AttachmentUtil;
import com.ecp888.wxms.utils.file.ImportExcel;
import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.attachment.AttachmentVO;
import com.ecp888.wxms.vo.client.BaseResponse;
import com.ecp888.wxms.vo.notice.NoticeVO;
import com.ecp888.wxms.web.controller.common.BaseController;
/**
 * APP广告位信息Controller
 * @author ecp875
 * @since 2014-08-11
 */
@Controller
@RequestMapping(value = "/notice")
public class NoticeController extends BaseController{
	
	public static final String TARGETUSER_FILE_NAME = "TARGETUSER.xlsx";
	
	Log log = LogFactory.getLog(getClass());
	@Autowired
	INoticeService noticeService;

	@RequestMapping(value = "/listNotice")
	public String listNotice(ModelMap map,@ModelAttribute("noticeForm") NoticeForm noticeForm
			)throws Exception{
		PageVO pageVO = noticeForm.getPageVO();
		NoticeVO noticeVO = noticeForm.getNoticeVO();
		List<NoticeVO> noticeList = noticeService.listNotice(noticeVO, pageVO);
		noticeForm.setNoticeList(noticeList);
		map.put("userForm", noticeForm);
		return "/notice/listNotice";
	}
	
	@RequestMapping(value = "/orderNotice")
	public String orderNotice(ModelMap map,@ModelAttribute("noticeForm") NoticeForm noticeForm
			)throws Exception{
		NoticeVO noticeVO = noticeForm.getNoticeVO();
		PageVO pageVO = noticeForm.getPageVO();
		List<NoticeVO> noticeList = noticeService.orderNotice(noticeVO, pageVO);
		noticeForm.setNoticeList(noticeList);
		map.put("userForm", noticeForm);
		return "/notice/listNotice";
	}
	
	@RequestMapping(value = "/editNotice")
	public String editNotice(ModelMap map,@ModelAttribute("noticeForm") NoticeForm noticeForm
			)throws Exception{
		NoticeVO noticeVO = noticeForm.getNoticeVO();
		if(null!=noticeVO){
			noticeVO = noticeService.getNotice(noticeVO);
			noticeForm.setNoticeVO(noticeVO);
			map.put("userForm", noticeForm);
		}
		return "/notice/editNotice";
	}
	
	@RequestMapping(value = "/viewNotice")
	public String viewNotice(ModelMap map,@ModelAttribute("noticeForm") NoticeForm noticeForm
			)throws Exception{
		NoticeVO noticeVO = noticeForm.getNoticeVO();
		if(null!=noticeVO){
			noticeVO = noticeService.getNotice(noticeVO);
			noticeForm.setNoticeVO(noticeVO);
			map.put("userForm", noticeForm);
		}
		return "/notice/viewNotice";
	}
	
	@RequestMapping(value = "/saveNotice")
	public String saveNotice(ModelMap map,@ModelAttribute("noticeForm") NoticeForm noticeForm
			)throws Exception{
		PageVO pageVO = noticeForm.getPageVO();
		NoticeVO noticeVO = noticeForm.getNoticeVO();
		
		noticeService.saveNotice(noticeVO);
		
		noticeVO = new NoticeVO();
		List<NoticeVO> noticeList = noticeService.listNotice(noticeVO, pageVO);
		noticeForm.setNoticeList(noticeList);
		map.put("userForm", noticeForm);
		return "/notice/listNotice";
	}
	
	@RequestMapping(value = "/deleteNotice")
	public String deleteNotice(ModelMap map,@ModelAttribute("noticeForm") NoticeForm noticeForm
			)throws Exception{
		PageVO pageVO = noticeForm.getPageVO();
		NoticeVO noticeVO = noticeForm.getNoticeVO();
		noticeService.deleteNotice(noticeVO);
		
		noticeVO = new NoticeVO();
		List<NoticeVO> noticeList = noticeService.listNotice(noticeVO, pageVO);
		noticeForm.setNoticeList(noticeList);
		map.put("userForm", noticeForm);
		return "/notice/listNotice";
	}
	
	/**
	 * 发布APP广告位信息
	 * @author ecp875
	 * @since 2014-08-24
	 * @param map
	 * @param noticeForm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/releaseNotice")
	public String releaseNotice(ModelMap map,@ModelAttribute("noticeForm") NoticeForm noticeForm
			)throws Exception{
		PageVO pageVO = noticeForm.getPageVO();
		NoticeVO noticeVO = noticeForm.getNoticeVO();
		noticeService.releaseNotice(noticeVO);
		
		noticeVO = new NoticeVO();
		List<NoticeVO> noticeList = noticeService.listNotice(noticeVO, pageVO);
		noticeForm.setNoticeList(noticeList);
		map.put("userForm", noticeForm);
		return "/notice/listNotice";
	}
	
	/**
	 * 显示广告位内容
	 * @param map
	 * @param noticeForm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/showImage")
	public String showImage(@RequestParam("type") String type,ModelMap map,@ModelAttribute("noticeForm") NoticeForm noticeForm
			)throws Exception{
		NoticeVO noticeVO = noticeForm.getNoticeVO();
		noticeVO = noticeService.getNotice(noticeVO);
		noticeForm.setNoticeVO(noticeVO);
		if("1".equals(type)){
			noticeVO.setHtmlUrl("");
		}else{
			noticeVO.setImgUrl("");
		}
		return "/notice/showImage";
	}
	
	@RequestMapping(value = "/checkTitle")
	public @ResponseBody String checkTitle(ModelMap map, @RequestBody NoticeVO noticeVO
			) throws Exception{
		String message = "";
		BaseResponse baseResponse = new BaseResponse();
		try{
			NoticeVO currentNoticeVO=noticeService.getNoticeByTitle(noticeVO);
			if(null==currentNoticeVO){
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
