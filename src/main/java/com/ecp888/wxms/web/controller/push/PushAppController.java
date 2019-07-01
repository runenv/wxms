package com.ecp888.wxms.web.controller.push;

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

import com.ecp888.wxms.service.push.IPushService;
import com.ecp888.wxms.service.partner.IPartnerService;
import com.ecp888.wxms.utils.AppUtil;
import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.vo.client.BaseResponse;
import com.ecp888.wxms.vo.client.push.Push;
import com.ecp888.wxms.vo.client.push.PushDetailRequest;
import com.ecp888.wxms.vo.client.push.PushDetailResponse;
import com.ecp888.wxms.vo.client.push.PushListRequest;
import com.ecp888.wxms.vo.client.push.PushListResponse;
import com.ecp888.wxms.vo.dict.DictionaryVO;
import com.ecp888.wxms.vo.push.PushRegisterVO;
import com.ecp888.wxms.vo.push.PushStatisticVO;
import com.ecp888.wxms.vo.push.PushVO;
import com.ecp888.wxms.web.controller.common.BaseController;

/**
 * APP Push信息Controller
 * @author ecp875
 * @since 2014-08-24
 */
@Controller
@RequestMapping(value = "/app/push")
public class PushAppController extends BaseController{
	
	Log log = LogFactory.getLog(getClass());
	@Autowired
	IPushService pushService;
	@Autowired
	IPartnerService partnerService;
	/**
	 * App Push列表
	 * http://localhost:8080/wxms/push/app/push_app!index.shtml?data={"channelNo":"1143","platform":"2"}
	 * @param map
	 * @param pushForm
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/push_app!index.shtml")
	public String index(ModelMap map,@ModelAttribute("pushForm") PushForm pushForm,
			HttpServletResponse response)throws Exception{
		PushVO reqPushVO = pushForm.getPushVO();
		PushListResponse appResponse = new PushListResponse();
		PushListRequest pushListRequest = null;
		String data = pushForm.getData() ;
		
		pushListRequest = AppUtil.string2Object(data, PushListRequest.class);
		if (pushListRequest.getPlatform() == null || pushListRequest.getChannelNo() == null) {
            appResponse.setStatusCode(BaseResponse.REQUEST_DATA_EXCEPTION);
        } else {
        	//渠道号
            String channelNo = convertChannelNo(pushListRequest.getChannelNo());
            String parentChannelNo = partnerService.getParentChannelNo(channelNo);
            log.info("App传入的渠道号: " + pushListRequest.getChannelNo());
            log.info("App实际查询的渠道号: " + parentChannelNo);
            
            String ggPlatform = convertPlatform(pushListRequest.getPlatform());
            log.info("App传入的平台号: " + pushListRequest.getPlatform());
            log.info("App实际查询的平台号: " + ggPlatform);
            
            reqPushVO.setPlatform(ggPlatform);
            reqPushVO.setChannel(parentChannelNo);
            List<PushVO> pushList = pushService.listPushApp(reqPushVO);
            List<Push> appPushs = new ArrayList<Push>();
            if(pushList != null && !pushList.isEmpty()) {
            	for (PushVO pushVO : pushList) {
            		Push appPush = new Push();
            		BeanUtils.copyProperties(appPush, pushVO);
                    appPush.setContent("app/push/showContent?pushVO.pushId=" + pushVO.getPushId());
                    
            		appPushs.add(appPush);
            	}
            	appResponse.setPushArray(appPushs);
                appResponse.setCounts(String.valueOf(appPushs.size()));
            }
        }
		writeToClient(AppUtil.object2Json(appResponse),response);
		return null;
	}
	
	/**
	 * App Push详情
	 * http://localhost:8080/wxms/app/push/push_app!show.shtml?data={"pushId":"1020"}
	 * @param map
	 * @param pushForm
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/push_app!show.shtml")
	public String show(ModelMap map,@ModelAttribute("pushForm") PushForm pushForm,
			HttpServletResponse response)throws Exception{
		String data = pushForm.getData() ;
		PushDetailRequest pushDetailRequest = AppUtil.string2Object(data, PushDetailRequest.class);
		PushDetailResponse appResponse = new PushDetailResponse();
		if (null == pushDetailRequest || null == pushDetailRequest.getPushId()) {
            appResponse.setStatusCode(PushDetailResponse.REQUEST_DATA_EXCEPTION);
            appResponse.setErrorMsg("请传入 PushID");
        } else {
        	PushVO paramVO = pushForm.getPushVO();
        	paramVO.setPushId(Integer.valueOf(pushDetailRequest.getPushId()));
        	PushVO pushVO = pushService.getPush(paramVO);
            if (pushVO == null) {
                appResponse.setErrorMsg("没有该记录");
            } else {
                BeanUtils.copyProperties(appResponse, pushVO);
                if(DictionaryVO.PUSH_DIRECT_URL.equals(pushVO.getDirectFlag())){
					appResponse.setClassId(DictionaryVO.PUSH_CLASSID_H5);
				}
            }
        }
		writeToClient(AppUtil.object2Json(appResponse),response);
		return null;
	}
	
	/**
	 * 显示Push内容
	 * @param map
	 * @param pushForm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/showContent")
	public String showContent(ModelMap map,@ModelAttribute("pushForm") PushForm pushForm
			)throws Exception{
		PushVO pushVO = pushForm.getPushVO();
		pushVO = pushService.getPush(pushVO);
		pushForm.setPushVO(pushVO);
		return "/push/showContent";
	}
	
	/**
	 * 统计Push用户登陆次数
	 * @param map
	 * @param pushForm,data={"pushId":"1020","platform":"addZtc","version":"IOS4_4"}
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/userCountRate")
	public String userCountRate(ModelMap map,@ModelAttribute("pushForm") PushForm pushForm,
			HttpServletResponse response)throws Exception{
		//pushId platform version
		String data = pushForm.getData();
		PushDetailRequest pushDetailRequest = AppUtil.string2Object(data, PushDetailRequest.class);
		PushDetailResponse appResponse = new PushDetailResponse();
		if (null == pushDetailRequest || !AppUtil.isNotEmpty(pushDetailRequest.getPushId())
				|| !AppUtil.isNotEmpty(pushDetailRequest.getPlatform())
				|| !AppUtil.isNotEmpty(pushDetailRequest.getVersion())) {
			appResponse.setStatusCode(BaseResponse.REQUEST_DATA_EXCEPTION);
			appResponse.setErrorMsg("pushId platform or version is not null! ");
		}else{
			PushStatisticVO paramVO = pushForm.getPushStatisticVO();
			paramVO.setPushId(Integer.valueOf(pushDetailRequest.getPushId()));
			paramVO.setPlatform(pushDetailRequest.getPlatform());
			paramVO.setVersion(pushDetailRequest.getVersion());
			try{
				pushService.userCountRate(paramVO);
			}catch(AppException e){
				appResponse.setStatusCode(BaseResponse.UNKNOW_EXCEPTION);
				appResponse.setErrorMsg("未找到更新Push Statistic的记录, pushId:" 
						+ paramVO.getPushId() + ",platform:" + paramVO.getPlatform()+ ",version:" + paramVO.getVersion());
			}
		}
		writeToClient(AppUtil.object2Json(appResponse),response);
		return null;
	}
	
	/**
	 * 
	 * @param map
	 * @param pushForm,data={"targetUser":"wxcs001","registrationId":"0005df3d2e5","registerType":"1"}
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/registerTargetUser")
	public String registerTargetUser(ModelMap map,@ModelAttribute("pushForm") PushForm pushForm,
			HttpServletResponse response)throws Exception{
		response.setContentType("text/html;charset=utf-8");  
        request.setCharacterEncoding("UTF-8");
		//String data = pushForm.getData();
		String data = new String(pushForm.getData().getBytes("ISO8859-1"), "utf-8");
		PushDetailRequest pushDetailRequest = AppUtil.string2Object(data, PushDetailRequest.class);
		PushDetailResponse appResponse = new PushDetailResponse();
		if (null == pushDetailRequest || !AppUtil.isNotEmpty(pushDetailRequest.getRegistrationId())
				|| !AppUtil.isNotEmpty(pushDetailRequest.getTargetUser())
				|| !AppUtil.isNotEmpty(pushDetailRequest.getRegisterType())) {
			appResponse.setStatusCode(BaseResponse.REQUEST_DATA_EXCEPTION);
			appResponse.setErrorMsg("registrationId registerType or targetUser is not null! ");
		}else{
			PushRegisterVO pushRegisterVO = new PushRegisterVO();
			pushRegisterVO.setRegistrationId(pushDetailRequest.getRegistrationId());
			pushRegisterVO.setTargetUser(pushDetailRequest.getTargetUser());
			pushRegisterVO.setRegisterType(pushDetailRequest.getRegisterType());
			try{
				pushService.registerTargetUser(pushRegisterVO);
			}catch(AppException e){
				appResponse.setStatusCode(BaseResponse.UNKNOW_EXCEPTION);
				appResponse.setErrorMsg("Push目标用户关联设备失败！");
			}
		}
		writeToClient(AppUtil.object2Json(appResponse),response);
		return null;
	}
	
}
