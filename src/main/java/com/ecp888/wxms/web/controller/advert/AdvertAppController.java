package com.ecp888.wxms.web.controller.advert;

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

import com.ecp888.wxms.service.advert.IAdvertService;
import com.ecp888.wxms.service.partner.IPartnerService;
import com.ecp888.wxms.utils.AppUtil;
import com.ecp888.wxms.vo.client.BaseResponse;
import com.ecp888.wxms.vo.client.advert.Advert;
import com.ecp888.wxms.vo.client.advert.AdvertDetailRequest;
import com.ecp888.wxms.vo.client.advert.AdvertDetailResponse;
import com.ecp888.wxms.vo.client.advert.AdvertListRequest;
import com.ecp888.wxms.vo.client.advert.AdvertListResponse;
import com.ecp888.wxms.vo.dict.DictionaryVO;
import com.ecp888.wxms.vo.advert.AdvertVO;
import com.ecp888.wxms.web.controller.common.BaseController;

/**
 * APP资讯信息Controller
 * @author ecp875
 * @since 2014-08-24
 */
@Controller
@RequestMapping(value = "/app/advert")
public class AdvertAppController extends BaseController{
	
	Log log = LogFactory.getLog(getClass());
	@Autowired
	IAdvertService advertService;
	@Autowired
	IPartnerService partnerService;
	/**
	 * App资讯列表
	 * http://localhost:8080/wxms/gjgonggao/app/notic_app!index.shtml?data={"channelNo":"1143","platform":"2"}
	 * @param map
	 * @param advertForm
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/notic_app!index.shtml")
	public String index(ModelMap map,@ModelAttribute("advertForm") AdvertForm advertForm,
			HttpServletResponse response)throws Exception{
		AdvertVO reqAdvertVO = advertForm.getAdvertVO();
		AdvertListResponse appResponse = new AdvertListResponse();
		AdvertListRequest advertListRequest = null;
		String data = advertForm.getData() ;
		
		advertListRequest = AppUtil.string2Object(data, AdvertListRequest.class);
		if (advertListRequest.getPlatform() == null || advertListRequest.getChannelNo() == null) {
            appResponse.setStatusCode(BaseResponse.REQUEST_DATA_EXCEPTION);
        } else {
        	//渠道号
            String channelNo = convertChannelNo(advertListRequest.getChannelNo());
            String parentChannelNo = partnerService.getParentChannelNo(channelNo);
            log.info("App传入的渠道号: " + advertListRequest.getChannelNo());
            log.info("App实际查询的渠道号: " + parentChannelNo);
            
            String ggPlatform = convertPlatform(advertListRequest.getPlatform());
            log.info("App传入的平台号: " + advertListRequest.getPlatform());
            log.info("App实际查询的平台号: " + ggPlatform);
            
            reqAdvertVO.setPlatform(ggPlatform);
            reqAdvertVO.setChannel(parentChannelNo);
            List<AdvertVO> advertList = advertService.listAdvertApp(reqAdvertVO);
            List<Advert> appAdverts = new ArrayList<Advert>();
            if(advertList != null && !advertList.isEmpty()) {
            	for (AdvertVO advertVO : advertList) {
            		Advert appAdvert = new Advert();
            		BeanUtils.copyProperties(appAdvert, advertVO);
            		appAdvert.setAdvertId(String.valueOf(advertVO.getAdvertId()));
                    appAdvert.setTime(advertVO.getCreateDate());
                    appAdvert.setContentUrl("/gjgonggao/app/notic!showContent.shtml?advertVO.advertId=" + advertVO.getAdvertId());
                    appAdvert.setType(null == advertVO.getClassId()?"":String.valueOf(advertVO.getClassId()));
                    appAdvert.setImgUrl(advertVO.getImgUrl());
                    appAdvert.setHtmlUrl(advertVO.getHtmlUrl());
                    appAdvert.setLotteryId(null == advertVO.getLotteryId()?"":String.valueOf(advertVO.getLotteryId()));
                    appAdvert.setStartDate(advertVO.getStartDate());
                    appAdvert.setEndDate(advertVO.getEndDate());
                    
            		appAdverts.add(appAdvert);
            	}
            	appResponse.setAdvertArray(appAdverts);
                appResponse.setCounts(String.valueOf(appAdverts.size()));
            }
        }
		writeToClient(AppUtil.object2Json(appResponse),response);
		return null;
	}
	
	/**
	 * App资讯详情
	 * http://localhost:8080/wxms/gjgonggao/app/notic_app!show.shtml?data={"advertId":"178"}
	 * @param map
	 * @param advertForm
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/notic_app!show.shtml")
	public String show(ModelMap map,@ModelAttribute("advertForm") AdvertForm advertForm,
			HttpServletResponse response)throws Exception{
		String data = advertForm.getData() ;
		AdvertDetailRequest advertDetailRequest = AppUtil.string2Object(data, AdvertDetailRequest.class);
		AdvertDetailResponse appResponse = new AdvertDetailResponse();
		if (null == advertDetailRequest || null == advertDetailRequest.getAdvertId()) {
            appResponse.setStatusCode(AdvertDetailResponse.REQUEST_DATA_EXCEPTION);
            appResponse.setErrorMsg("请传入资讯ID");
        } else {
        	AdvertVO paramVO = advertForm.getAdvertVO();
        	paramVO.setAdvertId(Integer.valueOf(advertDetailRequest.getAdvertId()));
        	AdvertVO advertVO = advertService.getAdvert(paramVO);
            if (advertVO == null) {
                appResponse.setErrorMsg("没有该记录");
            } else {
                BeanUtils.copyProperties(appResponse, advertVO);
                Integer classId = advertVO.getClassId();
                if(DictionaryVO.PUSH_DIRECT_URL.equals(advertVO.getDirectFlag())){
					classId = DictionaryVO.PUSH_CLASSID_H5;
				}
                appResponse.setType(String.valueOf(null == classId ? "":classId));
                appResponse.setLotteryId(String.valueOf(null == advertVO.getLotteryId()?"":advertVO.getLotteryId()));
                appResponse.setContentUrl("/gjgonggao/app/notic!showContent.shtml?advertVO.advertId=" + advertVO.getAdvertId());
                
            }
        }
		writeToClient(AppUtil.object2Json(appResponse),response);
		return null;
	}
	
	/**
	 * 显示资讯内容
	 * @param map
	 * @param advertForm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/notic!showContent.shtml")
	public String showContent(ModelMap map,@ModelAttribute("advertForm") AdvertForm advertForm
			)throws Exception{
		AdvertVO advertVO = advertForm.getAdvertVO();
		advertVO = advertService.getAdvert(advertVO);
		advertForm.setAdvertVO(advertVO);
		return "/advert/showContent";
	}
}
