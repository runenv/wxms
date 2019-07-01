package com.ecp888.wxms.service.impl.push;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import cn.jpush.api.JPushClient;
import cn.jpush.api.common.APIConnectionException;
import cn.jpush.api.common.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.PushPayload.Builder;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.ecp888.wxms.dao.dict.IDictionaryDao;
import com.ecp888.wxms.dao.push.IPushDAO;
import com.ecp888.wxms.utils.AppUtil;
import com.ecp888.wxms.utils.ApplicationContextHelper;
import com.ecp888.wxms.utils.WxConstants;
import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.vo.dict.DictionaryItemVO;
import com.ecp888.wxms.vo.dict.DictionaryVO;
import com.ecp888.wxms.vo.push.PushStatisticVO;
import com.ecp888.wxms.vo.push.PushVO;
/**
 * 推送Push消息类
 * @author ecp875
 * @since 2014-09-05
 */
public class PushMessage {
	
	protected static Log logger = LogFactory.getLog(PushMessage.class);
	
	//public static final String APP_KEY ="0aabc85fea290be98609cb55";
	//public static final String MASTER_SECRET = "ca80b5df51e3440ab58e4678";
	
	public static final String KEY_PLATFORM = "platform";
	public static final String PLATFORM_ANDROID = "platform_android";
	public static final String PLATFORM_IOS = "platform_ios";
	public static int badge = 1;
	
	private static PushMessage instance = null;
	
	public static synchronized PushMessage getInstance(String message,
			Map<String, String> extras) {
		if (instance == null) {
			instance = new PushMessage();
		}
		PushMessage.message = message;
		PushMessage.extras = extras;
		return instance;
	}
	
	private static String message;
	private static Map<String, String> extras;
	
	private PushMessage(){
		
	}
	
	/**
	 * 推送Push消息
	 * @author ecp875
	 * @since 2014-09-05
	 * @param pushVO
	 * @param extras
	 */
	public PushResult sendPush(PushVO pushVO) {
		PushResult result = null;
		List<DictionaryItemVO> dicItemList = null;
		try {
			IDictionaryDao dictionaryDao = (IDictionaryDao)ApplicationContextHelper.getBean("IDictionaryDao");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("dictionaryName", DictionaryVO.PUSH_VERSION);
			map.put("parentId", "0");
			dicItemList = dictionaryDao.lookupDict(map);
		} catch (AppException e) {
			logger.error("PUSH_VERSION is Null!" + e);
		}
		if(null == pushVO) return result;
		if(null == pushVO.getArrId() || null == pushVO.getArrId1()){
			//根据目标用户发送Push
			List<String> registrationIdList = pushVO.getRegistrationIdList();
			if(null != registrationIdList && !registrationIdList.isEmpty()){
				PushPayload payload = buildPush(pushVO.getContent(), pushVO.getTitle(),registrationIdList);
				if(null != payload){
					logger.info("======send by user Content：" + pushVO.getContent() + ";Title：" + pushVO.getTitle());
					sendPush(payload);
				}
			}
		}else{
			String[] version = pushVO.getArrId2();
			if((null == version || version.length== 0) && null != dicItemList){
				List<String> versionList = new ArrayList<String>();
				for(DictionaryItemVO itemVO : dicItemList){
					versionList.add(itemVO.getItemCode());
				}
				version = new String[versionList.size()];
				versionList.toArray(version);
			}
			//根据tags发送Push
			String[] platformArr = new String[1];
			for(String platformStr : pushVO.getArrId()){
				platformArr[0] = platformStr;
				String[] versionArr = new String[1];
				for(String versionStr : version){
					versionArr[0] = versionStr;
					PushPayload payload = buildPush(pushVO.getContent(), pushVO.getTitle(), 
							platformArr,versionArr,pushVO.getArrId1(), extras);
					if(null != payload){
						logger.info("======send by tags Content：" + pushVO.getContent() + ";Title：" + pushVO.getTitle());
						result = sendPush(payload);
						//push数据统计
						pushVO.setPlatform(platformStr);
						pushVO.setVersion(versionStr);
						statisticPush(result, pushVO);
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * 根据目标用户推送Push消息
	 * @param pushVO
	 * @return
	 */
	public PushResult sendPushByTargetUser(PushVO pushVO) {
		PushResult result = null;
		List<String> registrationIdList = pushVO.getRegistrationIdList();
		
		PushPayload payload = buildPush(pushVO.getContent(), pushVO.getTitle(),registrationIdList);
		if(null != payload){
			logger.info("======send by user Content：" + pushVO.getContent() + ";Title：" + pushVO.getTitle());
			result = sendPush(payload);
		}
		return result;
	}
	
	/**
	 * 统计Push
	 * @author ecp875
	 * @since 2014-09-10
	 * @param pushResult
	 * @param pushVO
	 */
	private void statisticPush(PushResult pushResult,PushVO pushVO){
		try {
			IPushDAO pushDAO = (IPushDAO)ApplicationContextHelper.getBean("IPushDAO");
			PushStatisticVO pushStatisticVO = null;
			pushStatisticVO = new PushStatisticVO();
			pushStatisticVO.setPushId(pushVO.getPushId());
			pushStatisticVO.setPlatform(pushVO.getPlatform());
			pushStatisticVO.setVersion(pushVO.getVersion());
			pushStatisticVO.setMsgId(null == pushResult ? 0 : pushResult.msg_id);
			pushDAO.addStatisticPush(pushStatisticVO);
		} catch (AppException e) {
			logger.error(e); 
		}
	}
	
	/**
	 * 发送push消息
	 * @author ecp875
	 * @since 2014-09-05
	 * @param payload
	 */
	private PushResult sendPush(PushPayload payload){
		PushResult result = null;
		JPushClient jpushClient = new JPushClient(WxConstants.MASTER_SECRET, WxConstants.APP_KEY, 3);
        try {
            result = jpushClient.sendPush(payload);
            logger.info("Got result - " + result);
        } catch (APIConnectionException e) {
            logger.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            logger.error("Error response from JPush server. Should review and fix it. ", e);
            logger.info("HTTP Status: " + e.getStatus());
            logger.info("Error Code: " + e.getErrorCode());
            logger.info("Error Message: " + e.getErrorMessage());
            logger.info("Msg ID: " + e.getMsgId());
        }catch(IllegalArgumentException e){
        	logger.info("parameter is incorrect." + e);
        }catch(Exception e){
        	logger.info("Send Jpush is incorrect.");
        }
        return result;
	}
	
	/**
	 * 构建Push消息
	 * @author ecp875
	 * @since 2014-09-05
	 * @param pushVO
	 * @param extras
	 * @param tags 平台数组
	 * @param alias 渠道数组
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private PushPayload buildPush(String content,String title,
			String[] tags,String[] tagAnds,String[] alias,Map<String, String> extras){
		
		PushPayload pushPayload = null;
		if(!AppUtil.isNotEmpty(content) 
				|| null == tags || null == alias) return pushPayload;
		
		Builder newBuilder = PushPayload.newBuilder();
		
		if(PLATFORM_ANDROID.equals(AppUtil.getMapValue(extras, KEY_PLATFORM))){
			newBuilder.setPlatform(Platform.android());
			newBuilder.setNotification(Notification.android(content,null==title?"":title, extras));
		}else if(PLATFORM_IOS.equals(AppUtil.getMapValue(extras, KEY_PLATFORM))){
			newBuilder.setPlatform(Platform.ios());
			newBuilder.setNotification(Notification.newBuilder().addPlatformNotification(
					IosNotification.newBuilder().setAlert(content).addExtras(extras).build()).build());
		}else{
			Map map = AppUtil.jsonToMap(message);
			String msg = "/app/push/push_app!show.shtml?data={\"pushId\":\""+AppUtil.getMapValue(map, "pushId")+"\"}";
			newBuilder.setPlatform(Platform.all());
			newBuilder.setNotification(Notification.newBuilder()
					.addPlatformNotification(
							AndroidNotification.newBuilder()
								.setAlert(content)
								.addExtras(AppUtil.jsonToMap(message)).build())
					.addPlatformNotification(
							IosNotification.newBuilder().setAlert(content)
								/*.setBadge(badge++)*/.setSound("sound")
								.addExtra("messageUrl", msg).build())
					.build());
			
			if(WxConstants.APP_ENVIRONMENT_PRODUCT.equals(WxConstants.APP_ENVIRONMENT)){
				newBuilder.setOptions(Options.newBuilder().setApnsProduction(true).build());
			}else{
				newBuilder.setOptions(Options.newBuilder().setApnsProduction(false).build());
			}
		}
		
		//Message messageObj = Message.newBuilder().setMsgContent(message).build();
		//extras为空时根据平台、渠道、版本发送Push,否则发送广播，并附加目标用户至extras
		cn.jpush.api.push.model.audience.Audience.Builder audienceBuilder = Audience.newBuilder();
		if(null == extras){
			audienceBuilder.addAudienceTarget(AudienceTarget.tag(tags)); 
			audienceBuilder.addAudienceTarget(AudienceTarget.tag_and(tagAnds)); 
			audienceBuilder.addAudienceTarget(AudienceTarget.alias(alias));
		}else{
			audienceBuilder.setAll(true);
			newBuilder.setPlatform(Platform.android());
			newBuilder.setNotification(Notification.android(content,null==title?"":title, extras));
			//messageObj = Message.newBuilder().setMsgContent(message).addExtras(extras).build();
		}
		Audience audience = audienceBuilder.build();
		newBuilder.setAudience(audience);
		
		/*newBuilder.setMessage(messageObj);*/
		pushPayload = newBuilder.build();
		
		return pushPayload;
	}
	
	/**
	 * 根据registrationId构建Push消息
	 * @author ecp875
	 * @since 2014-09-05
	 * @param content
	 * @param title
	 * @param registrationIds
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private PushPayload buildPush(String content,String title,List<String> registrationIds){
		
		PushPayload pushPayload = null;
		if(!AppUtil.isNotEmpty(content) || null == registrationIds) return pushPayload;
		
		Map map = AppUtil.jsonToMap(message);
		String msg = "/app/push/push_app!show.shtml?data={\"pushId\":\""+AppUtil.getMapValue(map, "pushId")+"\"}";
		Builder newBuilder = PushPayload.newBuilder();
		newBuilder.setPlatform(Platform.all());
		newBuilder.setNotification(Notification.newBuilder()
				.addPlatformNotification(
						AndroidNotification.newBuilder().setAlert(content)
							.addExtras(AppUtil.jsonToMap(message)).build())
				.addPlatformNotification(
						IosNotification.newBuilder().setAlert(content)
							/*.setBadge(badge++)*/.setSound("sound")
							.addExtra("messageUrl", msg).build())
				.build());
		newBuilder.setAudience(Audience.registrationId(registrationIds));
		if(WxConstants.APP_ENVIRONMENT_PRODUCT.equals(WxConstants.APP_ENVIRONMENT)){
			newBuilder.setOptions(Options.newBuilder().setApnsProduction(true).build());
		}else{
			newBuilder.setOptions(Options.newBuilder().setApnsProduction(false).build());
		}
		/*Message messageObj = Message.newBuilder().setMsgContent(message).build();
		newBuilder.setMessage(messageObj);*/
		pushPayload = newBuilder.build();
		
		return pushPayload;
	}
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		PushMessage.message = message;
	}

	public Map<String, String> getExtras() {
		return extras;
	}

	public void setExtras(Map<String, String> extras) {
		PushMessage.extras = extras;
	}
	
}
