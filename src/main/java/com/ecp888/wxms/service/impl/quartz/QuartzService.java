package com.ecp888.wxms.service.impl.quartz;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import cn.jpush.api.push.PushResult;

import com.ecp888.wxms.dao.push.IPushDAO;
import com.ecp888.wxms.dao.quartz.IPushQuartzDAO;
import com.ecp888.wxms.service.impl.push.PushMessage;
import com.ecp888.wxms.utils.AppUtil;
import com.ecp888.wxms.utils.ApplicationContextHelper;
import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.vo.client.push.PushDetailResponse;
import com.ecp888.wxms.vo.dict.DictionaryVO;
import com.ecp888.wxms.vo.push.PushVO;

/**
 * 消息推送调度
 * @author wuxibo
 * @since 2014/09/01
 * 
 */
public class QuartzService implements Job{
	protected Log logger = LogFactory.getLog(getClass());

    IPushQuartzDAO pushQuartzDAO;
	IPushDAO pushDAO;
    public void execute(JobExecutionContext context) throws JobExecutionException{
    	logger.info("quartz push start");
        this.pushMessage();
        logger.info("quartz push end");
    }

	/**消息推送*/
    public void pushMessage(){
    	pushQuartzDAO = (IPushQuartzDAO)ApplicationContextHelper.getBean("IPushQuartzDAO");
    	pushDAO = (IPushDAO)ApplicationContextHelper.getBean("IPushDAO");
    	PushVO pushVO = null;
    	PushResult result = null;
    	PushDetailResponse appResponse = null;
    	List<String> targetList = null;
    	List<String> platformList = null;
    	List<String> channelList = null;
    	List<String> versionList = null;
    	try{
			List<PushVO> pushList = pushQuartzDAO.getPushList();
			if(null != pushList && !pushList.isEmpty()){
    			for(int i=0;i<pushList.size();i++){
    				pushVO = pushList.get(i);

    				targetList = pushQuartzDAO.getTarget(pushVO);
    				platformList = pushQuartzDAO.getPlatform(pushVO);
    				channelList = pushQuartzDAO.getChannel(pushVO);
    				versionList = pushQuartzDAO.getVersion(pushVO);
    				pushVO.setTargetUserList(targetList);//用户
    				pushVO.setArrId((String[])platformList.toArray(new String[platformList.size()]));//平台
    				pushVO.setArrId1((String[])channelList.toArray(new String[channelList.size()]));//渠道
    				pushVO.setArrId2((String[])versionList.toArray(new String[versionList.size()]));//版本
    				pushVO.setRegistrationIdList(pushDAO.listRegistrationId(pushVO));
    				appResponse = new PushDetailResponse();
    				BeanUtils.copyProperties(appResponse, pushVO);
    				if(DictionaryVO.PUSH_DIRECT_URL.equals(pushVO.getDirectFlag())){
    					appResponse.setClassId(DictionaryVO.PUSH_CLASSID_H5);
    				}
    				result = PushMessage.getInstance(AppUtil.object2Json(appResponse), null).sendPush(pushVO);
    				if(null!=result){
    					//pushDAO.deleteTaskDateByDate(pushVO);
    					pushDAO.updateTaskDate(pushVO); //更新发送状态为1，表示已发送
    					logger.info("quartz push success");
    				}
        		}
    		}
		}catch (AppException e) {
			e.printStackTrace();
		}catch(Exception e1){
			e1.printStackTrace();
		}
    }
}