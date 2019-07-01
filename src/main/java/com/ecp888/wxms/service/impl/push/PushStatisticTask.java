package com.ecp888.wxms.service.impl.push;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.APIConnectionException;
import cn.jpush.api.common.APIRequestException;
import cn.jpush.api.report.MessagesResult;

import com.ecp888.wxms.dao.push.IPushDAO;
import com.ecp888.wxms.utils.ApplicationContextHelper;
import com.ecp888.wxms.utils.WxConstants;
import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.vo.push.PushStatisticVO;

/**
 * 定时任务统计Push点击率等数据
 * @author ecp875
 * @since 2014-09-15
 */
public class PushStatisticTask  implements Job{
	
	protected Log logger = LogFactory.getLog(getClass());

	IPushDAO pushDAO;
	
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		logger.info("quartz push statistic start");
		pushStatistic();
        logger.info("quartz push statistic end");
		
	}
	
	/**
	 * Push点击率等数据统计
	 * @author ecp875
	 * @since 2014-09-15
	 */
	private void pushStatistic(){
		try {
			pushDAO = (IPushDAO)ApplicationContextHelper.getBean("IPushDAO");
			PushStatisticVO conditionVO = new PushStatisticVO();
			List<PushStatisticVO> listStatistic = pushDAO.listStatisticPush(conditionVO);
			if(null != listStatistic && !listStatistic.isEmpty()){
				for (PushStatisticVO statisticVO : listStatistic) {
					long msgId = statisticVO.getMsgId();
					JPushClient jpushClient = new JPushClient(WxConstants.MASTER_SECRET, WxConstants.APP_KEY);
					MessagesResult reportMessages = jpushClient.getReportMessages(String.valueOf(msgId));
						
					List<cn.jpush.api.report.MessagesResult.Message> messages = reportMessages.messages;
					//更新统计数据
					for(cn.jpush.api.report.MessagesResult.Message message : messages){
						int received = message.android.received; 
						received += message.ios.apns_sent;
						statisticVO.setPushCount(received);
						int click = message.android.click;
						click += message.ios.click;
						statisticVO.setHitCount(click);
						if(received != 0){
							pushDAO.updateStatisticPush(statisticVO);
						}
					}
				}
			}
		} catch (AppException e) {
			logger.error("IPushDAO error", e);
		} catch (APIConnectionException e) {
			logger.error("Connection error. Should retry later. ", e);
		} catch (APIRequestException e) {
			logger.error("Error response from JPush server. Should review and fix it. ", e);
			logger.info("HTTP Status: " + e.getStatus());
			logger.info("Error Code: " + e.getErrorCode());
			logger.info("Error Message: " + e.getErrorMessage());
		}
	}
	
	
	public static void main(String[] args) {
		try {
			long msgId = 312;
			JPushClient jpushClient = new JPushClient(WxConstants.MASTER_SECRET, WxConstants.APP_KEY);
			MessagesResult reportMessages = jpushClient.getReportMessages(String.valueOf(msgId));
				
			List<cn.jpush.api.report.MessagesResult.Message> messages = reportMessages.messages;
			//更新统计数据
			for(cn.jpush.api.report.MessagesResult.Message message : messages){
				int received = message.android.received; 
				received += message.ios.apns_sent;
				int click = message.android.click;
				click += message.ios.click;
				
				System.err.println(received +"" + click);
			}
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIRequestException e) {
			e.printStackTrace();
		}
	}
}
