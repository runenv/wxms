package com.ecp888.wxms.service.impl.bulletin;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecp888.wxms.dao.bulletin.IBulletinDAO;
import com.ecp888.wxms.service.attach.IAttachmentService;
import com.ecp888.wxms.service.dict.IDictionaryService;
import com.ecp888.wxms.service.partner.IPartnerService;
import com.ecp888.wxms.service.bulletin.IBulletinService;
import com.ecp888.wxms.utils.AppUtil;
import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.utils.exception.ExConstants;
import com.ecp888.wxms.utils.exception.FunctionException;
import com.ecp888.wxms.utils.file.ImportExcel;
import com.ecp888.wxms.utils.interfaces.AppOperate;
import com.ecp888.wxms.utils.interfaces.AppResource;
import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.attachment.AttachmentVO;
import com.ecp888.wxms.vo.dict.DictionaryItemVO;
import com.ecp888.wxms.vo.dict.DictionaryVO;
import com.ecp888.wxms.vo.bulletin.BulletinVO;
/**
 * APP公告信息服务类
 * @author ecp875
 * @since 2014-08-02
 */
@Service("bulletinService")
@AppResource(code="BulletinService",desc="APP公告管理")
public class BulletinService implements IBulletinService {
	
	protected Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	IBulletinDAO bulletinDAO;
	@Autowired
	IAttachmentService attachmentService;
	@Autowired
	IDictionaryService dictService;
	@Autowired
	IPartnerService partnerService;
	
	public List<BulletinVO> listBulletin(BulletinVO bulletinVO, PageVO pageVO)
			throws AppException{
		int total = bulletinDAO.countBulletin(bulletinVO);
		pageVO.setRecordTotal(total);
		List<BulletinVO> bulletinList = bulletinDAO.listBulletin(bulletinVO,pageVO);
		for (BulletinVO bulletinVO2 : bulletinList) {
			List<String> platformIds = bulletinDAO.findBulletinPlatform(bulletinVO2);
			if(null !=platformIds && !platformIds.isEmpty()){
				StringBuffer platformStr = new StringBuffer();
				int i = 0;
				for(String platformId : platformIds){
					DictionaryItemVO dictItemVO = dictService.lookupDict(DictionaryVO.BULLETIN_PLATFORM, "0", String.valueOf(platformId));
					platformStr.append(dictItemVO.getItemName());
					if(i<platformIds.size() -1){
						platformStr.append(",");
					}
					i++;
				}
				bulletinVO2.setPlatform(platformStr.toString());
			}
			if(null != bulletinVO2.getActStatus()){
				DictionaryItemVO dictItemVO = dictService.lookupDict(DictionaryVO.NOTICE_ACTION, "0", String.valueOf(bulletinVO2.getActStatus()));
				bulletinVO2.setActStatusName(dictItemVO.getItemName());
			}
			if(null != bulletinVO2.getClassId()){
				DictionaryItemVO dictItemVO = dictService.lookupDict(DictionaryVO.NOTICE_CLASS, "0", String.valueOf(bulletinVO2.getClassId()));
				bulletinVO2.setClassName(dictItemVO.getItemName());
			}
		}
		return bulletinList;
	}
	
	public List<BulletinVO> orderBulletin(BulletinVO bulletinVO, PageVO pageVO) throws AppException {
		List<BulletinVO> bulletinList = listBulletin(bulletinVO, pageVO);
		BulletinVO currentVO = null;
		BulletinVO targetVO = null;
		if(null == bulletinList || bulletinList.isEmpty() || bulletinList.size() == 1) return bulletinList;
		int i = 0;
		for (BulletinVO tempVO : bulletinList) {
			if(tempVO.getBulletinId().intValue() == bulletinVO.getBulletinId().intValue()){
				currentVO = tempVO;
				if(BulletinVO.DIRECTION_UP.equals(bulletinVO.getDirection())){
					i--;
				}else{
					i++;
				}
				targetVO = bulletinList.get(i);
				break;
			}
			i++;
		}
		if(currentVO != null && targetVO != null){
			int orderNum = currentVO.getOrderNum();
			currentVO.setOrderNum(targetVO.getOrderNum());
			targetVO.setOrderNum(orderNum);
			bulletinDAO.updateBulletin(currentVO);
			bulletinDAO.updateBulletin(targetVO);
		}
		bulletinList = listBulletin(bulletinVO, pageVO);
		return bulletinList;
	}

	public BulletinVO getBulletin(BulletinVO bulletinVO) throws AppException {
		if(null != bulletinVO.getBulletinId()){
			bulletinVO = bulletinDAO.getBulletinById(bulletinVO);
		}
		if(null == bulletinVO) return null;
		bulletinVO.setPartnerMap(partnerService.findAllGroupByMobiletype());
		//公告平台
		List<String> platformIds = bulletinDAO.findBulletinPlatform(bulletinVO);
		if(null!=platformIds && !platformIds.isEmpty()){
			String[] ids=new String[platformIds.size()];
			platformIds.toArray(ids);
			bulletinVO.setArrId(ids);
		}
		//公告渠道
		List<String> channelIds = bulletinDAO.findBulletinChannel(bulletinVO);
		if(null!=channelIds && !channelIds.isEmpty()){
			String[] ids=new String[channelIds.size()];
			channelIds.toArray(ids);
			bulletinVO.setArrId1(ids);
		}
		//目标用户
		bulletinVO.setTargetUserList(bulletinDAO.findTargetUser(bulletinVO));
		if(AppUtil.isNotEmpty(bulletinVO.getTargetUser())){
			AttachmentVO targetUserAttachVO = bulletinVO.getTargetUserAttachVO(); 
			targetUserAttachVO.setAttachId(Integer.valueOf(bulletinVO.getTargetUser()));
			targetUserAttachVO = attachmentService.getAttachment(targetUserAttachVO);
			bulletinVO.setTargetUserAttachVO(targetUserAttachVO);
		}
		return bulletinVO;
	}
	
	public BulletinVO getBulletinByTitle(BulletinVO bulletinVO) throws AppException {
		return bulletinDAO.getBulletinByName(bulletinVO);
	}

	@AppOperate(code="update",desc="添加或更新APP公告")
	public void saveBulletin(BulletinVO bulletinVO) throws AppException {
		
		BulletinVO curBulletin = bulletinDAO.getBulletinByName(bulletinVO);		
		if(null==bulletinVO.getBulletinId()){
			if (curBulletin!=null){
				throw new FunctionException(ExConstants.F_BULLETIN_1001);
			}
			bulletinVO.setBulletinStatus(Integer.valueOf(DictionaryVO.RELEASE_STATUS_FALSE));
			bulletinDAO.addBulletin(bulletinVO);
			bulletinVO.setOrderNum(bulletinVO.getBulletinId());
		}else{
			bulletinDAO.updateBulletin(bulletinVO);
		}
		
		if(null == bulletinVO.getBulletinId())return;
		//公告平台
		bulletinDAO.deleteBulletinPlatform(bulletinVO);
		if (null != bulletinVO.getArrId()) {
			bulletinDAO.createBulletinPlatform(bulletinVO);
		}
		//公告渠道
		bulletinDAO.deleteBulletinChannel(bulletinVO);
		if (null != bulletinVO.getArrId1()) {
			bulletinDAO.createBulletinChannel(bulletinVO);
		}
		//附件记录
		AttachmentVO attachmentVO = bulletinVO.getAttachmentVO();
		attachmentVO.setAttachMoudle(BulletinVO.ATTACH_MOUDLE);
		attachmentVO.setAttachKey(bulletinVO.getBulletinId());
		
		if(AppUtil.isNotEmpty(bulletinVO.getImgUrlPath())){
			if(AppUtil.isNotEmpty(bulletinVO.getImgUrl())){
				AttachmentVO oldImgUrlAttachVO = new AttachmentVO();
				oldImgUrlAttachVO.setAttachId(Integer.valueOf(bulletinVO.getImgUrl()));
				attachmentService.deleteAttachmentById(oldImgUrlAttachVO);
			}
			attachmentVO.setAttachTempPath(attachmentVO.getUploadTempPath() + bulletinVO.getImgUrlPath());
			attachmentService.saveAttachment(attachmentVO);
			bulletinVO.setImgUrl(String.valueOf(attachmentVO.getAttachId()));
			bulletinDAO.updateBulletin(bulletinVO); //更新广告位ImgUrl
		}
		
		if(AppUtil.isNotEmpty(bulletinVO.getHtmlUrlPath())){
			if(AppUtil.isNotEmpty(bulletinVO.getHtmlUrl())){
				AttachmentVO oldHtmlUrlAttachVO = new AttachmentVO();
				oldHtmlUrlAttachVO.setAttachId(Integer.valueOf(bulletinVO.getHtmlUrl()));
				attachmentService.deleteAttachmentById(oldHtmlUrlAttachVO);
			}
			attachmentVO.setAttachTempPath(attachmentVO.getUploadTempPath() + bulletinVO.getHtmlUrlPath());
			attachmentService.saveAttachment(attachmentVO);
			bulletinVO.setHtmlUrl(String.valueOf(attachmentVO.getAttachId()));
			bulletinDAO.updateBulletin(bulletinVO); //更新广告位HtmlUrl
		}
		//目标用户
		if(AppUtil.isNotEmpty(bulletinVO.getTargetUserPath())){
			if(AppUtil.isNotEmpty(bulletinVO.getTargetUser())){
				AttachmentVO oldUserAttachVO = bulletinVO.getTargetUserAttachVO();
				oldUserAttachVO.setAttachId(Integer.valueOf(bulletinVO.getTargetUser()));
				attachmentService.deleteAttachmentById(oldUserAttachVO);
			}
			attachmentVO.setAttachTempPath(attachmentVO.getUploadTempPath() + bulletinVO.getTargetUserPath());
			attachmentVO = attachmentService.saveAttachment(attachmentVO); 
			List<String> userTargetList = importTargetUser(attachmentVO.getAttachPath());
			bulletinVO.setTargetUserList(userTargetList);
			
			bulletinDAO.deleteTargetUser(bulletinVO);
			if(null != bulletinVO.getTargetUserList() 
					&& !bulletinVO.getTargetUserList().isEmpty()){
				bulletinDAO.addTargetUser(bulletinVO);
			}
			if(null != attachmentVO.getAttachId()){
				bulletinVO.setTargetUser(String.valueOf(attachmentVO.getAttachId())); 
				bulletinDAO.updateBulletin(bulletinVO); 
			}
		}else{
			if(AppUtil.isNotEmpty(bulletinVO.getTargetUser())){
				AttachmentVO targetAttachVO = attachmentService.getAttachment(Integer.valueOf(bulletinVO.getTargetUser()));
				if(null == targetAttachVO){
					bulletinDAO.deleteTargetUser(bulletinVO);
				}
			}
		}
	}

	@AppOperate(code="delete",desc="删除APP公告")
	public void deleteBulletin(BulletinVO bulletinVO) throws AppException {
		BulletinVO persistVO = bulletinDAO.getBulletinById(bulletinVO);
		AttachmentVO attachVO = new AttachmentVO();
		if(AppUtil.isNotEmpty(persistVO.getHtmlUrl())){
			attachVO.setAttachId(Integer.valueOf(persistVO.getHtmlUrl()));
			attachmentService.deleteAttachmentById(attachVO);
		}
		if(AppUtil.isNotEmpty(persistVO.getTargetUser())){
			attachVO.setAttachId(Integer.valueOf(persistVO.getTargetUser()));
			attachmentService.deleteAttachmentById(attachVO);
		}
		bulletinDAO.deleteBulletin(bulletinVO);
	}

	@AppOperate(code="release",desc="发布APP公告")
	public void releaseBulletin(BulletinVO bulletinVO) throws AppException {
		BulletinVO persistVO = bulletinDAO.getBulletinById(bulletinVO);
		persistVO.setBulletinStatus(bulletinVO.getBulletinStatus());
		bulletinDAO.updateBulletin(persistVO);
	}

	public BulletinVO getBulletin(Integer bulletinId) throws AppException {
		BulletinVO bulletinVO=new BulletinVO();
		bulletinVO.setBulletinId(bulletinId);
		return bulletinDAO.getBulletinById(bulletinVO);
	}

	public List<BulletinVO> listBulletinApp(BulletinVO bulletinVO)
			throws AppException {
		return bulletinDAO.listBulletinApp(bulletinVO);
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
