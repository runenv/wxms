package com.ecp888.wxms.web.controller.notice;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecp888.wxms.service.notice.INoticeService;
import com.ecp888.wxms.service.partner.IPartnerService;
import com.ecp888.wxms.utils.AppUtil;
import com.ecp888.wxms.vo.client.BaseResponse;
import com.ecp888.wxms.vo.client.notice.Notice;
import com.ecp888.wxms.vo.client.notice.NoticeDetailRequest;
import com.ecp888.wxms.vo.client.notice.NoticeDetailResponse;
import com.ecp888.wxms.vo.client.notice.NoticeListRequest;
import com.ecp888.wxms.vo.client.notice.NoticeListResponse;
import com.ecp888.wxms.vo.dict.DictionaryVO;
import com.ecp888.wxms.vo.notice.NoticeVO;
import com.ecp888.wxms.web.controller.common.BaseController;

/**
 * APP广告位信息Controller
 * @author ecp875
 * @since 2014-08-24
 */
@Controller
@RequestMapping(value = "/gjgonggao")
public class NoticeAppController extends BaseController{
	
	Log log = LogFactory.getLog(getClass());
	@Autowired
	INoticeService noticeService;
	@Autowired
	IPartnerService partnerService;
	/**
	 * App广告位列表
	 * http://localhost:8080/wxms/gjgonggao/app/notic_app!index.shtml?data={"channelNo":"1143","platform":"2"}
	 * @param map
	 * @param noticeForm
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/notic_app!index.shtml")
	public String index(ModelMap map,@ModelAttribute("noticeForm") NoticeForm noticeForm,
			HttpServletResponse response)throws Exception{
		NoticeVO reqNoticeVO = noticeForm.getNoticeVO();
		NoticeListResponse appResponse = new NoticeListResponse();
		NoticeListRequest noticeListRequest = null;
		String data = noticeForm.getData() ;
		
		noticeListRequest = AppUtil.string2Object(data, NoticeListRequest.class);
		if (noticeListRequest.getPlatform() == null || noticeListRequest.getChannelNo() == null) {
            appResponse.setStatusCode(BaseResponse.REQUEST_DATA_EXCEPTION);
        } else {
        	//渠道号
            String channelNo = convertChannelNo(noticeListRequest.getChannelNo());
            String parentChannelNo = partnerService.getParentChannelNo(channelNo);
            log.info("App传入的渠道号: " + noticeListRequest.getChannelNo());
            log.info("App实际查询的渠道号: " + parentChannelNo);
            
            String ggPlatform = convertPlatform(noticeListRequest.getPlatform());
            log.info("App传入的平台号: " + noticeListRequest.getPlatform());
            log.info("App实际查询的平台号: " + ggPlatform);
            
            reqNoticeVO.setPlatform(ggPlatform);
            reqNoticeVO.setChannel(parentChannelNo);
            reqNoticeVO.setUserName(noticeListRequest.getUserName());
            List<NoticeVO> noticeList = noticeService.listNoticeApp(reqNoticeVO);
            List<Notice> appNotices = new ArrayList<Notice>();
            if(noticeList != null && !noticeList.isEmpty()) {
            	for (NoticeVO noticeVO : noticeList) {
            		Notice appNotice = new Notice();
            		BeanUtils.copyProperties(appNotice, noticeVO);
            		appNotice.setNoticeId(String.valueOf(noticeVO.getNoticeId()));
                    appNotice.setTime(noticeVO.getCreateDate());
                    appNotice.setContentUrl("/gjgonggao/app/notic!showContent.shtml?noticeVO.noticeId=" + noticeVO.getNoticeId());
                    appNotice.setType(null == noticeVO.getClassId()?"":String.valueOf(noticeVO.getClassId()));
                    appNotice.setImgUrl("/file/fileDownload/" + noticeVO.getImgUrl());
                    appNotice.setHtmlUrl("/file/fileDownload/" + noticeVO.getHtmlUrl());
                    appNotice.setLotteryId(null == noticeVO.getLotteryId()?"":String.valueOf(noticeVO.getLotteryId()));
                    appNotice.setStartDate(noticeVO.getStartDate());
                    appNotice.setEndDate(noticeVO.getEndDate());
                    
            		appNotices.add(appNotice);
            	}
            	appResponse.setNoticeArray(appNotices);
                appResponse.setCounts(String.valueOf(appNotices.size()));
            }
        }
		writeToClient(AppUtil.object2Json(appResponse),response);
		return null;
	}
	
	/**
	 * App广告位详情
	 * http://localhost:8080/wxms/gjgonggao/app/notic_app!show.shtml?data={"noticeId":"178"}
	 * @param map
	 * @param noticeForm
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/notic_app!show.shtml")
	public String show(ModelMap map,@ModelAttribute("noticeForm") NoticeForm noticeForm,
			HttpServletResponse response)throws Exception{
		String data = noticeForm.getData() ;
		NoticeDetailRequest noticeDetailRequest = AppUtil.string2Object(data, NoticeDetailRequest.class);
		NoticeDetailResponse appResponse = new NoticeDetailResponse();
		if (null == noticeDetailRequest || null == noticeDetailRequest.getNoticeId()) {
            appResponse.setStatusCode(NoticeDetailResponse.REQUEST_DATA_EXCEPTION);
            appResponse.setErrorMsg("请传入广告位ID");
        } else {
        	NoticeVO paramVO = noticeForm.getNoticeVO();
        	paramVO.setNoticeId(Integer.valueOf(noticeDetailRequest.getNoticeId()));
        	NoticeVO noticeVO = noticeService.getNotice(paramVO);
            if (noticeVO == null) {
                appResponse.setErrorMsg("没有该记录");
            } else {
                BeanUtils.copyProperties(appResponse, noticeVO);
                Integer classId = noticeVO.getClassId();
                if(DictionaryVO.PUSH_DIRECT_URL.equals(noticeVO.getDirectFlag())){
					classId = DictionaryVO.PUSH_CLASSID_H5;
				}
                appResponse.setType(String.valueOf(null == classId ? "":classId));
                appResponse.setLotteryId(String.valueOf(null == noticeVO.getLotteryId()?"":noticeVO.getLotteryId()));
                appResponse.setContentUrl("/gjgonggao/app/notic!showContent.shtml?noticeVO.noticeId=" + noticeVO.getNoticeId());
                appResponse.setImgUrl("/file/fileDownload/" + noticeVO.getImgUrl());
            }
        }
		writeToClient(AppUtil.object2Json(appResponse),response);
		return null;
	}
	
	/**
	 * 显示广告位内容
	 * @param map
	 * @param noticeForm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/notic!showContent.shtml")
	public String showContent(ModelMap map,@ModelAttribute("noticeForm") NoticeForm noticeForm
			)throws Exception{
		NoticeVO noticeVO = noticeForm.getNoticeVO();
		noticeVO = noticeService.getNotice(noticeVO);
		noticeForm.setNoticeVO(noticeVO);
		return "/notice/showContent";
	}
}
