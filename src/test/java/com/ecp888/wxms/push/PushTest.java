package com.ecp888.wxms.push;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.APIConnectionException;
import cn.jpush.api.common.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.PushPayload.Builder;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

public class PushTest {

	protected static Log logger = LogFactory.getLog(PushTest.class);
	
	// demo App defined in resources/jpush-api.conf 
	private static final String appKey ="0aabc85fea290be98609cb55";
	private static final String masterSecret = "ca80b5df51e3440ab58e4678";
	
	public static final String ALERT = "彩票直通车";
	public static final String TITLE = "彩票直通车V4.3上线啦!";
    public static final String MSG_CONTENT = "因为懂得所以更努力...";
    public static final String REGISTRATION_ID = "0900e8d85ef";
    public static final String TAG = "tag_api";
	
	public static void main(String[] args) {
		
		testSendPush();
	}
	
	public static void testSendPush() {
        JPushClient jpushClient = new JPushClient(masterSecret, appKey, 3);
        PushPayload payload = buildPush();
        try {
            PushResult result = jpushClient.sendPush(payload);
            logger.info("Got result - " + result);
            
        } catch (APIConnectionException e) {
            logger.error("Connection error. Should retry later. ", e);
            
        } catch (APIRequestException e) {
            logger.error("Error response from JPush server. Should review and fix it. ", e);
            logger.info("HTTP Status: " + e.getStatus());
            logger.info("Error Code: " + e.getErrorCode());
            logger.info("Error Message: " + e.getErrorMessage());
            logger.info("Msg ID: " + e.getMsgId());
        }
	}
	
	
	public static PushPayload buildPush(){
		Map<String, String> extras = new HashMap<String, String>();
		extras.put("openUrl", "http://www.baidu.com");
		PushPayload pushPayload = null;
		Builder newBuilder = PushPayload.newBuilder();
		newBuilder.setPlatform(Platform.android());
		newBuilder.setNotification(Notification.android(ALERT, TITLE, extras));
		
		Audience audience = Audience.newBuilder()
			.addAudienceTarget(AudienceTarget.alias("addZtc", "iosZtc"))	//platform
			.addAudienceTarget(AudienceTarget.tag("ecp888_android", "ecp888_2.2_1004")) //channelNo
			.addAudienceTarget(AudienceTarget.tag("Android 4.0", "bbb")) //version
        	.build();
		newBuilder.setAudience(audience);
		
		Message message = Message.newBuilder()
				.setMsgContent(MSG_CONTENT).addExtra("from", "JPush").build();
		newBuilder.setMessage(message);
		
		pushPayload = newBuilder.build();
		return pushPayload;
	}
	
	
	
	
	
	
	
	
	
	
	public static PushPayload buildPushObject_all_all_alert() {
	    return PushPayload.alertAll(ALERT);
	}
	
    public static PushPayload buildPushObject_all_alias_alert() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias("alias1"))
                .setNotification(Notification.alert(ALERT))
                .build();
    }
    
    public static PushPayload buildPushObject_android_tag_alertWithTitle() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.tag("tag1"))
                .setNotification(Notification.android(ALERT, TITLE, null))
                .build();
    }
    
    public static PushPayload buildPushObject_ios_tagAnd_alertWithExtrasAndMessage() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.tag_and("tag1", "tag_all"))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(ALERT)
                                .setBadge(5)
                                .setSound("happy")
                                .addExtra("from", "JPush")
                                .build())
                        .build())
                 .setMessage(Message.content(MSG_CONTENT))
                 .setOptions(Options.newBuilder()
                         .setApnsProduction(true)
                         .build())
                 .build();
    }
    
    public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.newBuilder()
                        .addAudienceTarget(AudienceTarget.tag("tag1", "tag2"))
                        .addAudienceTarget(AudienceTarget.alias("alias1", "alias2"))
                        .build())
                .setMessage(Message.newBuilder()
                        .setMsgContent(MSG_CONTENT)
                        .addExtra("from", "JPush")
                        .build())
                .build();
    }
}
