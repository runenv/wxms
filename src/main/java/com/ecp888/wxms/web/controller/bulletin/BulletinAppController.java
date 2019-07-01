package com.ecp888.wxms.web.controller.bulletin;

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

import com.ecp888.wxms.service.bulletin.IBulletinService;
import com.ecp888.wxms.service.partner.IPartnerService;
import com.ecp888.wxms.utils.AppUtil;
import com.ecp888.wxms.vo.client.BaseResponse;
import com.ecp888.wxms.vo.client.bulletin.Bulletin;
import com.ecp888.wxms.vo.client.bulletin.BulletinDetailRequest;
import com.ecp888.wxms.vo.client.bulletin.BulletinDetailResponse;
import com.ecp888.wxms.vo.client.bulletin.BulletinListRequest;
import com.ecp888.wxms.vo.client.bulletin.BulletinListResponse;
import com.ecp888.wxms.vo.bulletin.BulletinVO;
import com.ecp888.wxms.web.controller.common.BaseController;

/**
 * APP Bulletin信息Controller
 * @author ecp875
 * @since 2014-08-24
 */
@Controller
@RequestMapping(value = "/bulletin")
public class BulletinAppController extends BaseController{
	
	Log log = LogFactory.getLog(getClass());
	@Autowired
	IBulletinService bulletinService;
	@Autowired
	IPartnerService partnerService;
	/**
	 * App Bulletin列表
	 * http://localhost:8080/wxms/bulletin/app/bulletin_app!index.shtml?data={"channelNo":"1143","platform":"2"}
	 * @param map
	 * @param bulletinForm
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/bulletin_app!index.shtml")
	public String index(ModelMap map,@ModelAttribute("bulletinForm") BulletinForm bulletinForm,
			HttpServletResponse response)throws Exception{
		BulletinVO reqBulletinVO = bulletinForm.getBulletinVO();
		BulletinListResponse appResponse = new BulletinListResponse();
		BulletinListRequest bulletinListRequest = null;
		String data = bulletinForm.getData() ;
		
		bulletinListRequest = AppUtil.string2Object(data, BulletinListRequest.class);
		if (bulletinListRequest.getPlatform() == null || bulletinListRequest.getChannelNo() == null) {
            appResponse.setStatusCode(BaseResponse.REQUEST_DATA_EXCEPTION);
        } else {
        	//渠道号
            String channelNo = convertChannelNo(bulletinListRequest.getChannelNo());
            String parentChannelNo = partnerService.getParentChannelNo(channelNo);
            log.info("App传入的渠道号: " + bulletinListRequest.getChannelNo());
            log.info("App实际查询的渠道号: " + parentChannelNo);
            
            String ggPlatform = convertPlatform(bulletinListRequest.getPlatform());
            log.info("App传入的平台号: " + bulletinListRequest.getPlatform());
            log.info("App实际查询的平台号: " + ggPlatform);
            
            reqBulletinVO.setPlatform(ggPlatform);
            reqBulletinVO.setChannel(parentChannelNo);
            List<BulletinVO> bulletinList = bulletinService.listBulletinApp(reqBulletinVO);
            List<Bulletin> appBulletins = new ArrayList<Bulletin>();
            if(bulletinList != null && !bulletinList.isEmpty()) {
            	for (BulletinVO bulletinVO : bulletinList) {
            		Bulletin appBulletin = new Bulletin();
            		BeanUtils.copyProperties(appBulletin, bulletinVO);
                    appBulletin.setContent(request.getContextPath() + "/bulletin/showContent?bulletinVO.bulletinId=" + bulletinVO.getBulletinId());
                    
            		appBulletins.add(appBulletin);
            	}
            	appResponse.setBulletinArray(appBulletins);
                appResponse.setCounts(String.valueOf(appBulletins.size()));
            }
        }
		writeToClient(AppUtil.object2Json(appResponse),response);
		return null;
	}
	
	/**
	 * App Bulletin详情
	 * http://localhost:8080/wxms/bulletin/app/bulletin_app!show.shtml?data={"bulletinId":"178"}
	 * @param map
	 * @param bulletinForm
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/bulletin_app!show.shtml")
	public String show(ModelMap map,@ModelAttribute("bulletinForm") BulletinForm bulletinForm,
			HttpServletResponse response)throws Exception{
		String data = bulletinForm.getData() ;
		BulletinDetailRequest bulletinDetailRequest = AppUtil.string2Object(data, BulletinDetailRequest.class);
		BulletinDetailResponse appResponse = new BulletinDetailResponse();
		if (null == bulletinDetailRequest || null == bulletinDetailRequest.getBulletinId()) {
            appResponse.setStatusCode(BulletinDetailResponse.REQUEST_DATA_EXCEPTION);
            appResponse.setErrorMsg("请传入 BulletinID");
        } else {
        	BulletinVO paramVO = bulletinForm.getBulletinVO();
        	paramVO.setBulletinId(Integer.valueOf(bulletinDetailRequest.getBulletinId()));
        	BulletinVO bulletinVO = bulletinService.getBulletin(paramVO);
            if (bulletinVO == null) {
                appResponse.setErrorMsg("没有该记录");
            } else {
                BeanUtils.copyProperties(appResponse, bulletinVO);
                appResponse.setContent("/bulletin/showContent?id=" + bulletinVO.getBulletinId());
            }
        }
		writeToClient(AppUtil.object2Json(appResponse),response);
		return null;
	}
	
	/**
	 * 显示公告内容
	 * @param map
	 * @param bulletinForm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/showContent")
	public String showContent(ModelMap map,@ModelAttribute("bulletinForm") BulletinForm bulletinForm
			)throws Exception{
		BulletinVO bulletinVO = bulletinForm.getBulletinVO();
		bulletinVO = bulletinService.getBulletin(bulletinVO);
		bulletinForm.setBulletinVO(bulletinVO);
		return "/bulletin/showContent";
	}
}
