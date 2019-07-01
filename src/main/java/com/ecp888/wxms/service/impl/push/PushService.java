package com.ecp888.wxms.service.impl.push;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecp888.wxms.dao.push.IPushDAO;
import com.ecp888.wxms.service.attach.IAttachmentService;
import com.ecp888.wxms.service.dict.IDictionaryService;
import com.ecp888.wxms.service.partner.IPartnerService;
import com.ecp888.wxms.service.push.IPushService;
import com.ecp888.wxms.utils.AppUtil;
import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.utils.exception.ExConstants;
import com.ecp888.wxms.utils.exception.FunctionException;
import com.ecp888.wxms.utils.file.ImportExcel;
import com.ecp888.wxms.utils.interfaces.AppOperate;
import com.ecp888.wxms.utils.interfaces.AppResource;
import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.attachment.AttachmentVO;
import com.ecp888.wxms.vo.client.push.PushDetailResponse;
import com.ecp888.wxms.vo.dict.DictionaryItemVO;
import com.ecp888.wxms.vo.dict.DictionaryVO;
import com.ecp888.wxms.vo.push.PushRegisterVO;
import com.ecp888.wxms.vo.push.PushStatisticVO;
import com.ecp888.wxms.vo.push.PushVO;
/**
 * APP Push信息服务类
 * @author ecp875
 * @since 2014-08-24
 */
@Service("pushService")
@AppResource(code="PushService",desc="APP Push管理")
public class PushService implements IPushService {
	
	protected Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	IPushDAO pushDAO;
	@Autowired
	IAttachmentService attachmentService;
	@Autowired
	IDictionaryService dictService;
	@Autowired
	IPartnerService partnerService;
	
	public List<PushVO> listPush(PushVO pushVO, PageVO pageVO)
			throws AppException{
		int total = pushDAO.countPush(pushVO);
		pageVO.setRecordTotal(total);
		List<PushVO> pushList = pushDAO.listPush(pushVO,pageVO);
		for (PushVO pushVO2 : pushList) {
			List<String> platformIds = pushDAO.findPushPlatform(pushVO2);
			//平台名称
			if(null !=platformIds && !platformIds.isEmpty()){
				StringBuffer platformStr = new StringBuffer();
				int i = 0;
				for(String platformId : platformIds){
					DictionaryItemVO dictItemVO = dictService.lookupDict(DictionaryVO.PUSH_PLATFORM, "0", String.valueOf(platformId));
					platformStr.append(dictItemVO.getItemName());
					if(i<platformIds.size() -1){
						platformStr.append(",");
					}
					i++;
				}
				pushVO2.setPlatform(platformStr.toString());
			}
			List<String> versionIds = pushDAO.findPushVersion(pushVO2);
			//版本名称
			if(null !=versionIds && !versionIds.isEmpty()){
				StringBuffer versionStr = new StringBuffer();
				for(int j = 0; j< versionIds.size(); j++){
					DictionaryItemVO dictItemVO = dictService.lookupDict(DictionaryVO.PUSH_VERSION, "0", versionIds.get(j));
					versionStr.append(dictItemVO.getItemName());
					if(j < versionIds.size() - 1){
						versionStr.append(",");
					}
				}
				pushVO2.setVersion(versionStr.toString());
			}
			if(null != pushVO2.getActStatus()){
				DictionaryItemVO dictItemVO = dictService.lookupDict(DictionaryVO.NOTICE_ACTION, "0", String.valueOf(pushVO2.getActStatus()));
				pushVO2.setPushStatusName(dictItemVO.getItemName());
			}
			if(null != pushVO2.getClassId()){
				DictionaryItemVO dictItemVO = dictService.lookupDict(DictionaryVO.NOTICE_CLASS, "0", String.valueOf(pushVO2.getClassId()));
				pushVO2.setClassName(dictItemVO.getItemName());
			}
			//推送的时间
			if(DictionaryVO.PUSH_TYPE_LOAD.equals(pushVO2.getPushType())
					&& (null != pushVO2.getPushStatus() && pushVO2.getPushStatus() != 0)){
				pushVO2.setTaskDate(pushVO2.getPushDate());
			}else{
				//Push推送时间
				List<String> taskDateList = pushDAO.findTaskDate(pushVO2);
				if(null!=taskDateList && !taskDateList.isEmpty()){
					String[] taskDateArray=new String[taskDateList.size()];
					taskDateList.toArray(taskDateArray);
					pushVO2.setTaskDateArray(taskDateArray);
					StringBuffer taskDateStr = new StringBuffer();
					for(int i = 0; i<taskDateList.size(); i++){
						taskDateStr.append(taskDateList.get(i));
						if(i < taskDateList.size()-1){
							taskDateStr.append(",");
						}
					}
					pushVO2.setTaskDate(taskDateStr.toString());
				}
			}
		}
		return pushList;
	}
	
	public List<PushVO> orderPush(PushVO pushVO, PageVO pageVO) throws AppException {
		List<PushVO> pushList = listPush(pushVO, pageVO);
		PushVO currentVO = null;
		PushVO targetVO = null;
		if(null == pushList || pushList.isEmpty() || pushList.size() == 1) return pushList;
		int i = 0;
		for (PushVO tempVO : pushList) {
			if(tempVO.getPushId().intValue() == pushVO.getPushId().intValue()){
				currentVO = tempVO;
				if(PushVO.DIRECTION_UP.equals(pushVO.getDirection())){
					i--;
				}else{
					i++;
				}
				targetVO = pushList.get(i);
				break;
			}
			i++;
		}
		if(currentVO != null && targetVO != null){
			int orderNum = currentVO.getOrderNum();
			currentVO.setOrderNum(targetVO.getOrderNum());
			targetVO.setOrderNum(orderNum);
			pushDAO.updatePush(currentVO);
			pushDAO.updatePush(targetVO);
		}
		pushList = listPush(pushVO, pageVO);
		return pushList;
	}

	public PushVO getPush(PushVO pushVO) throws AppException {
		if(null != pushVO.getPushId()){
			pushVO = pushDAO.getPushById(pushVO);
		}
		if(null == pushVO) return null;
		pushVO.setPartnerMap(partnerService.findAllGroupByMobiletype());
		//Push平台
		List<String> platformIds = pushDAO.findPushPlatform(pushVO);
		if(null!=platformIds && !platformIds.isEmpty()){
			String[] ids=new String[platformIds.size()];
			platformIds.toArray(ids);
			pushVO.setArrId(ids);
		}
		//Push渠道
		List<String> channelIds = pushDAO.findPushChannel(pushVO);
		if(null!=channelIds && !channelIds.isEmpty()){
			String[] ids=new String[channelIds.size()];
			channelIds.toArray(ids);
			pushVO.setArrId1(ids);
		}
		//Push版本
		List<String> versionIds = pushDAO.findPushVersion(pushVO);
		if(null!=versionIds && !versionIds.isEmpty()){
			String[] ids=new String[versionIds.size()];
			versionIds.toArray(ids);
			pushVO.setArrId2(ids);
		}
		//Push推送时间
		List<String> taskDateList = pushDAO.findTaskDate(pushVO);
		if(null!=taskDateList && !taskDateList.isEmpty()){
			String[] taskDateArray=new String[taskDateList.size()];
			taskDateList.toArray(taskDateArray);
			pushVO.setTaskDateArray(taskDateArray);
			StringBuffer taskDateStr = new StringBuffer();
			for(int i = 0; i<taskDateList.size(); i++){
				taskDateStr.append(taskDateList.get(i));
				if(i < taskDateList.size()-1){
					taskDateStr.append(",");
				}
			}
			pushVO.setTaskDate(taskDateStr.toString());
		}
		//目标用户
		pushVO.setTargetUserList(pushDAO.findTargetUser(pushVO));
		if(AppUtil.isNotEmpty(pushVO.getTargetUser())){
			AttachmentVO targetUserAttachVO = pushVO.getTargetUserAttachVO(); 
			targetUserAttachVO.setAttachId(Integer.valueOf(pushVO.getTargetUser()));
			targetUserAttachVO = attachmentService.getAttachment(targetUserAttachVO);
			pushVO.setTargetUserAttachVO(targetUserAttachVO);
		}
		pushVO.setRegistrationIdList(pushDAO.listRegistrationId(pushVO));
		return pushVO;
	}
	
	public PushVO getPushByTitle(PushVO pushVO) throws AppException {
		return pushDAO.getPushByName(pushVO);
	}

	@AppOperate(code="update",desc="添加或更新APP Push")
	public void savePush(PushVO pushVO) throws AppException {
		
		if(DictionaryVO.PUSH_DIRECT_PAGE.equals(pushVO.getDirectFlag())){
			pushVO.setOpenUrl("");
		}
		//PushVO curPush = pushDAO.getPushByName(pushVO);		
		if(null==pushVO.getPushId()){
			/*if (curPush!=null){
				throw new FunctionException(ExConstants.F_PUSH_1001);
			}*/
			pushVO.setPushStatus(Integer.valueOf(DictionaryVO.RELEASE_STATUS_FALSE));
			pushDAO.addPush(pushVO);
			pushVO.setOrderNum(pushVO.getPushId());
		}else{
			pushDAO.updatePush(pushVO);
		}
		if(null == pushVO.getPushId())return;
		//Push平台
		pushDAO.deletePushPlatform(pushVO);
		if (null != pushVO.getArrId()) {
			pushDAO.createPushPlatform(pushVO);
		}
		//Push渠道
		pushDAO.deletePushChannel(pushVO);
		if (null != pushVO.getArrId1()) {
			pushDAO.createPushChannel(pushVO);
		}
		//Push版本
		pushDAO.deletePushVersion(pushVO);
		if (null != pushVO.getArrId2()) {
			pushDAO.createPushVersion(pushVO);
		}
		//自定义推送时间
		pushDAO.deleteTaskDate(pushVO);
		if(AppUtil.isNotEmpty(pushVO.getTaskDate())){
			String[] taskDates = pushVO.getTaskDate().split(",");
			pushVO.setTaskDateArray(taskDates);
			pushDAO.addTaskDate(pushVO);
		}
		//附件记录
		AttachmentVO attachmentVO = pushVO.getAttachmentVO();
		attachmentVO.setAttachMoudle(PushVO.ATTACH_MOUDLE);
		attachmentVO.setAttachKey(pushVO.getPushId());
		if(AppUtil.isNotEmpty(pushVO.getImgUrlPath())){
			if(AppUtil.isNotEmpty(pushVO.getImgUrl())){
				AttachmentVO oldImgUrlAttachVO = new AttachmentVO();
				oldImgUrlAttachVO.setAttachId(Integer.valueOf(pushVO.getImgUrl()));
				attachmentService.deleteAttachmentById(oldImgUrlAttachVO);
			}
			attachmentVO.setAttachTempPath(attachmentVO.getUploadTempPath() + pushVO.getImgUrlPath());
			attachmentService.saveAttachment(attachmentVO);
			pushVO.setImgUrl(String.valueOf(attachmentVO.getAttachId()));
			pushDAO.updatePush(pushVO); //更新广告位ImgUrl
		}
		
		if(AppUtil.isNotEmpty(pushVO.getHtmlUrlPath())){
			if(AppUtil.isNotEmpty(pushVO.getHtmlUrl())){
				AttachmentVO oldHtmlUrlAttachVO = new AttachmentVO();
				oldHtmlUrlAttachVO.setAttachId(Integer.valueOf(pushVO.getHtmlUrl()));
				attachmentService.deleteAttachmentById(oldHtmlUrlAttachVO);
			}
			attachmentVO.setAttachTempPath(attachmentVO.getUploadTempPath() + pushVO.getHtmlUrlPath());
			attachmentService.saveAttachment(attachmentVO);
			pushVO.setHtmlUrl(String.valueOf(attachmentVO.getAttachId()));
			pushDAO.updatePush(pushVO); //更新广告位HtmlUrl
		}
		//目标用户
		if(AppUtil.isNotEmpty(pushVO.getTargetUserPath())){
			if(AppUtil.isNotEmpty(pushVO.getTargetUser())){
				AttachmentVO oldUserAttachVO = pushVO.getTargetUserAttachVO();
				oldUserAttachVO.setAttachId(Integer.valueOf(pushVO.getTargetUser()));
				attachmentService.deleteAttachmentById(oldUserAttachVO);
			}
			attachmentVO.setAttachTempPath(attachmentVO.getUploadTempPath() + pushVO.getTargetUserPath());
			attachmentVO = attachmentService.saveAttachment(attachmentVO); 
			List<String> userTargetList = importTargetUser(attachmentVO.getAttachPath());
			pushVO.setTargetUserList(userTargetList);
			
			pushDAO.deleteTargetUser(pushVO);
			if(null != pushVO.getTargetUserList() 
					&& !pushVO.getTargetUserList().isEmpty()){
				pushDAO.addTargetUser(pushVO);
			}
			if(null != attachmentVO.getAttachId()){
				pushVO.setTargetUser(String.valueOf(attachmentVO.getAttachId())); 
				pushDAO.updatePush(pushVO); 
			}
		}else{
			if(AppUtil.isNotEmpty(pushVO.getTargetUser())){
				AttachmentVO targetAttachVO = attachmentService.
						getAttachment(Integer.valueOf(pushVO.getTargetUser()));
				if(null == targetAttachVO){
					pushDAO.deleteTargetUser(pushVO);
				}
			}
		}
		//根据测试用户发送push信息
		if(AppUtil.isNotEmpty(pushVO.getTestUser())){
			String message = "";
			try {
				PushDetailResponse appResponse = new PushDetailResponse();
				BeanUtils.copyProperties(appResponse, pushVO);
				if(DictionaryVO.PUSH_DIRECT_URL.equals(pushVO.getDirectFlag())){
					appResponse.setClassId(DictionaryVO.PUSH_CLASSID_H5);
				}
				message = AppUtil.object2Json(appResponse);
				
				List<String> registrationIdList = new ArrayList<String>();
				PushRegisterVO pushRegisterVO = new PushRegisterVO();
				pushRegisterVO.setTargetUser(pushVO.getTestUser());
				List<PushRegisterVO> pushRegisterList = pushDAO.getPushRegisterByUser(pushRegisterVO);
				if(null != pushRegisterList && !pushRegisterList.isEmpty()){
					for(PushRegisterVO registerVO : pushRegisterList){
						registrationIdList.add(registerVO.getRegistrationId());
					}
					pushVO.setRegistrationIdList(registrationIdList);
					PushMessage.getInstance(message, null).sendPushByTargetUser(pushVO);
				}
			} catch (IOException e) {
				logger.error("pushVO转换Json出错！" + e);
			} catch (IllegalAccessException e) {
				logger.error("PushDetailResponse克隆persistVO出错！" + e);
			} catch (InvocationTargetException e) {
				logger.error("PushDetailResponse克隆persistVO出错！" + e);
			}
		}
	}

	@AppOperate(code="delete",desc="删除APP Push")
	public void deletePush(PushVO pushVO) throws AppException {
		PushVO persistVO = pushDAO.getPushById(pushVO);
		AttachmentVO attachVO = new AttachmentVO();
		if(AppUtil.isNotEmpty(persistVO.getTargetUser())){
			attachVO.setAttachId(Integer.valueOf(persistVO.getTargetUser()));
			attachmentService.deleteAttachmentById(attachVO);
		}
		pushDAO.deletePush(pushVO);
	}
	
	@AppOperate(code="release",desc="发布APP Push")
	public void releasePush(PushVO pushVO) throws AppException {
		PushVO persistVO = getPush(pushVO);
		persistVO.setPushStatus(pushVO.getPushStatus());
		//发送push信息
		if(DictionaryVO.RELEASE_STATUS_TRUE.equals(String.valueOf(persistVO.getPushStatus()))
				&& DictionaryVO.PUSH_TYPE_LOAD.equals(persistVO.getPushType())){
			//平台,渠道号或目标用户不能为空
			if(!AppUtil.isNotEmpty(pushVO.getTargetUser())){
				if(null == persistVO.getArrId() || null == persistVO.getArrId1()){
					throw new FunctionException(ExConstants.SYS_COMMON_10006);
				}
			}
			String message = "";
			try {
				//根据平台、渠道、版本发送通知
				PushDetailResponse appResponse = new PushDetailResponse();
				BeanUtils.copyProperties(appResponse, persistVO);
				if(DictionaryVO.PUSH_DIRECT_URL.equals(persistVO.getDirectFlag())){
					appResponse.setClassId(DictionaryVO.PUSH_CLASSID_H5);
				}
				message = AppUtil.object2Json(appResponse);
				PushMessage.getInstance(message, null).sendPush(persistVO);
				
			} catch (IOException e) {
				logger.error("pushVO转换Json出错！" + e);
			} catch (IllegalAccessException e) {
				logger.error("PushDetailResponse克隆persistVO出错！" + e);
			} catch (InvocationTargetException e) {
				logger.error("PushDetailResponse克隆persistVO出错！" + e);
			}
		}
		if(Integer.valueOf(DictionaryVO.RELEASE_STATUS_TRUE) == pushVO.getPushStatus()){
			persistVO.setPushDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		}
		pushDAO.updatePush(persistVO);
	}

	public PushVO getPush(Integer pushId) throws AppException {
		PushVO pushVO=new PushVO();
		pushVO.setPushId(pushId);
		return pushDAO.getPushById(pushVO);
	}

	@Override
	public List<PushVO> listPushApp(PushVO pushVO) throws AppException {
		return pushDAO.listPushApp(pushVO);
	}

	@Override
	public List<PushStatisticVO> statisticPush(PushStatisticVO pushStatisticVO)
			throws AppException {
		//默认显示最新的Push
		if(null == pushStatisticVO.getPushId()){
			List<PushVO> pushList = listPush(new PushVO());
			if(null != pushList && !pushList.isEmpty()){
				pushStatisticVO.setPushId(pushList.get(0).getPushId());
			}
		}
		List<PushStatisticVO> pushStatisticList = pushDAO.listStatisticPushRate(pushStatisticVO);
		for(PushStatisticVO statisticVO : pushStatisticList){
			if(AppUtil.isNotEmpty(statisticVO.getPlatform())){
				DictionaryItemVO dictItemVO = dictService.lookupDict(DictionaryVO.PUSH_PLATFORM, "0", statisticVO.getPlatform());
				statisticVO.setPlatform(dictItemVO.getItemName());
			}
			if(AppUtil.isNotEmpty(statisticVO.getVersion())){
				DictionaryItemVO dictItemVO = dictService.lookupDict(DictionaryVO.PUSH_VERSION, "0", statisticVO.getVersion());
				statisticVO.setVersion(dictItemVO.getItemName());
			}
		}
		return pushStatisticList;
	}

	public List<PushVO> listPush(PushVO pushVO) throws AppException {
		if(null == pushVO) pushVO = new PushVO();
		PageVO pageVO = new PageVO();
		int total = pushDAO.countPush(pushVO);
		pageVO.setRecordTotal(total);
		pageVO.setPageSize(total);
		return pushDAO.listPush(pushVO,pageVO);
	}

	public void userCountRate(PushStatisticVO paramVO) throws AppException {
		
		PushStatisticVO statisticVO = pushDAO.getStatisticPush(paramVO);
		if(null == statisticVO){
			throw new FunctionException(ExConstants.SYS_COMMON_10007);
		}
		statisticVO.setUserCount(statisticVO.getUserCount() + 1);
		pushDAO.updateStatisticPushById(statisticVO);
	}

	public void registerTargetUser(PushRegisterVO pushRegisterVO)
			throws AppException {
		
		PushRegisterVO persistVO = pushDAO.getPushRegister(pushRegisterVO);
		
		if(PushRegisterVO.REGISTER_TYPE_LOGIN.equals(pushRegisterVO.getRegisterType())){
			if(null == persistVO){
				pushDAO.addPushRegister(pushRegisterVO);
			}else{
				pushDAO.updatePushRegister(pushRegisterVO);
			}
		}else if(PushRegisterVO.REGISTER_TYPE_LOGOUT.equals(pushRegisterVO.getRegisterType())){
			pushDAO.deletePushRegister(pushRegisterVO);
		}
	}
	
	/**
	 * 导入目标用户
	 * @param fileName
	 * @param path
	 * @return
	 */
	private List<String> importTargetUser(String filePath){
    	List<String>  users = new ArrayList<String>();
    	if(!AppUtil.isNotEmpty(filePath))return users;
    	ImportExcel readExcel = new ImportExcel();
    	ArrayList<ArrayList<String>> rows = null;
    	rows = readExcel.readExcel(filePath);
    	for (ArrayList<String> cells : rows) {
    		users.add(cells.get(0));
    	}
    	return users;
    }
}
