package com.ecp888.wxms.service.impl.advert;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecp888.wxms.dao.advert.IAdvertDAO;
import com.ecp888.wxms.service.attach.IAttachmentService;
import com.ecp888.wxms.service.dict.IDictionaryService;
import com.ecp888.wxms.service.advert.IAdvertService;
import com.ecp888.wxms.service.partner.IPartnerService;
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
import com.ecp888.wxms.vo.advert.AdvertVO;
/**
 * APP资讯信息服务类
 * @author ecp875
 * @since 2014-08-02
 */
@Service("advertService")
@AppResource(code="AdvertService",desc="APP资讯管理")
public class AdvertService implements IAdvertService {
	
	protected Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	IAdvertDAO advertDAO;
	@Autowired
	IAttachmentService attachmentService;
	@Autowired
	IDictionaryService dictService;
	@Autowired
	IPartnerService partnerService;
	
	public List<AdvertVO> listAdvert(AdvertVO advertVO, PageVO pageVO)
			throws AppException{
		int total = advertDAO.countAdvert(advertVO);
		pageVO.setRecordTotal(total);
		List<AdvertVO> advertList = advertDAO.listAdvert(advertVO,pageVO);
		for (AdvertVO advertVO2 : advertList) {
			List<String> platformIds = advertDAO.findAdvertPlatform(advertVO2);
			if(null !=platformIds && !platformIds.isEmpty()){
				StringBuffer platformStr = new StringBuffer();
				int i = 0;
				for(String platformId : platformIds){
					DictionaryItemVO dictItemVO = dictService.lookupDict(DictionaryVO.NOTICE_PLATFORM, "0", String.valueOf(platformId));
					platformStr.append(dictItemVO.getItemName());
					if(i<platformIds.size() -1){
						platformStr.append(",");
					}
					i++;
				}
				advertVO2.setPlatform(platformStr.toString());
			}
			if(null != advertVO2.getActStatus()){
				DictionaryItemVO dictItemVO = dictService.lookupDict(DictionaryVO.NOTICE_ACTION, "0", String.valueOf(advertVO2.getActStatus()));
				advertVO2.setActStatusName(dictItemVO.getItemName());
			}
			if(null != advertVO2.getClassId()){
				DictionaryItemVO dictItemVO = dictService.lookupDict(DictionaryVO.NOTICE_CLASS, "0", String.valueOf(advertVO2.getClassId()));
				advertVO2.setClassName(dictItemVO.getItemName());
			}
		}
		return advertList;
	}
	
	public List<AdvertVO> orderAdvert(AdvertVO advertVO, PageVO pageVO) throws AppException {
		List<AdvertVO> advertList = listAdvert(advertVO, pageVO);
		AdvertVO currentVO = null;
		AdvertVO targetVO = null;
		if(null == advertList || advertList.isEmpty() || advertList.size() == 1) return advertList;
		int i = 0;
		for (AdvertVO tempVO : advertList) {
			if(tempVO.getAdvertId().intValue() == advertVO.getAdvertId().intValue()){
				currentVO = tempVO;
				if(AdvertVO.DIRECTION_UP.equals(advertVO.getDirection())){
					i--;
				}else{
					i++;
				}
				targetVO = advertList.get(i);
				break;
			}
			i++;
		}
		if(currentVO != null && targetVO != null){
			int orderNum = currentVO.getOrderNum();
			currentVO.setOrderNum(targetVO.getOrderNum());
			targetVO.setOrderNum(orderNum);
			advertDAO.updateAdvert(currentVO);
			advertDAO.updateAdvert(targetVO);
		}
		advertList = listAdvert(advertVO, pageVO);
		return advertList;
	}

	public AdvertVO getAdvert(AdvertVO advertVO) throws AppException {
		if(null != advertVO.getAdvertId()){
			advertVO = advertDAO.getAdvertById(advertVO);
		}
		if(null == advertVO) return null;
		advertVO.setPartnerMap(partnerService.findAllGroupByMobiletype());
		//资讯平台
		List<String> platformIds = advertDAO.findAdvertPlatform(advertVO);
		if(null!=platformIds && !platformIds.isEmpty()){
			String[] ids=new String[platformIds.size()];
			platformIds.toArray(ids);
			advertVO.setArrId(ids);
		}
		//资讯渠道
		List<String> channelIds = advertDAO.findAdvertChannel(advertVO);
		if(null!=channelIds && !channelIds.isEmpty()){
			String[] ids=new String[channelIds.size()];
			channelIds.toArray(ids);
			advertVO.setArrId1(ids);
		}
		//目标用户
		advertVO.setTargetUserList(advertDAO.findTargetUser(advertVO));
		if(AppUtil.isNotEmpty(advertVO.getTargetUser())){
			AttachmentVO targetUserAttachVO = advertVO.getTargetUserAttachVO(); 
			targetUserAttachVO.setAttachId(Integer.valueOf(advertVO.getTargetUser()));
			targetUserAttachVO = attachmentService.getAttachment(targetUserAttachVO);
			advertVO.setTargetUserAttachVO(targetUserAttachVO);
		}
		return advertVO;
	}
	
	public AdvertVO getAdvertByTitle(AdvertVO advertVO) throws AppException {
		return advertDAO.getAdvertByName(advertVO);
	}

	@AppOperate(code="update",desc="添加或更新APP资讯")
	public void saveAdvert(AdvertVO advertVO) throws AppException {
		
		AdvertVO curAdvert = advertDAO.getAdvertByName(advertVO);		
		if(null==advertVO.getAdvertId()){
			if (curAdvert!=null){
				throw new FunctionException(ExConstants.F_ADVERT_1001);
			}
			advertVO.setAdvertStatus(Integer.valueOf(DictionaryVO.RELEASE_STATUS_FALSE));
			advertDAO.addAdvert(advertVO);
			advertVO.setOrderNum(advertVO.getAdvertId());
		}else{
			advertDAO.updateAdvert(advertVO);
		}
		
		if(null == advertVO.getAdvertId())return;
		//资讯平台
		advertDAO.deleteAdvertPlatform(advertVO);
		if (null != advertVO.getArrId()) {
			advertDAO.createAdvertPlatform(advertVO);
		}
		//资讯渠道
		advertDAO.deleteAdvertChannel(advertVO);
		if (null != advertVO.getArrId1()) {
			advertDAO.createAdvertChannel(advertVO);
		}
		//附件记录
		AttachmentVO attachmentVO = advertVO.getAttachmentVO();
		attachmentVO.setAttachMoudle(AdvertVO.ATTACH_MOUDLE);
		attachmentVO.setAttachKey(advertVO.getAdvertId());
		if(AppUtil.isNotEmpty(advertVO.getImgUrlPath())){
			if(AppUtil.isNotEmpty(advertVO.getImgUrl())){
				AttachmentVO oldImgUrlAttachVO = new AttachmentVO();
				oldImgUrlAttachVO.setAttachId(Integer.valueOf(advertVO.getImgUrl()));
				attachmentService.deleteAttachmentById(oldImgUrlAttachVO);
			}
			attachmentVO.setAttachTempPath(attachmentVO.getUploadTempPath() + advertVO.getImgUrlPath());
			attachmentService.saveAttachment(attachmentVO);
			advertVO.setImgUrl(String.valueOf(attachmentVO.getAttachId()));
			advertDAO.updateAdvert(advertVO); //更新广告位ImgUrl
		}
		if(AppUtil.isNotEmpty(advertVO.getHtmlUrlPath())){
			if(AppUtil.isNotEmpty(advertVO.getHtmlUrl())){
				AttachmentVO oldHtmlUrlAttachVO = new AttachmentVO();
				oldHtmlUrlAttachVO.setAttachId(Integer.valueOf(advertVO.getHtmlUrl()));
				attachmentService.deleteAttachmentById(oldHtmlUrlAttachVO);
			}
			attachmentVO.setAttachTempPath(attachmentVO.getUploadTempPath() + advertVO.getHtmlUrlPath());
			attachmentService.saveAttachment(attachmentVO);
			advertVO.setHtmlUrl(String.valueOf(attachmentVO.getAttachId()));
			advertDAO.updateAdvert(advertVO); //更新广告位HtmlUrl
		}
		//目标用户
		if(AppUtil.isNotEmpty(advertVO.getTargetUserPath())){
			if(AppUtil.isNotEmpty(advertVO.getTargetUser())){
				AttachmentVO oldUserAttachVO = advertVO.getTargetUserAttachVO();
				oldUserAttachVO.setAttachId(Integer.valueOf(advertVO.getTargetUser()));
				attachmentService.deleteAttachmentById(oldUserAttachVO);
			}
			attachmentVO.setAttachTempPath(attachmentVO.getUploadTempPath() + advertVO.getTargetUserPath());
			attachmentVO = attachmentService.saveAttachment(attachmentVO); 
			List<String> userTargetList = importTargetUser(attachmentVO.getAttachPath());
			advertVO.setTargetUserList(userTargetList);
			
			advertDAO.deleteTargetUser(advertVO);
			if(null != advertVO.getTargetUserList() 
					&& !advertVO.getTargetUserList().isEmpty()){
				advertDAO.addTargetUser(advertVO);
			}
			if(null != attachmentVO.getAttachId()){
				advertVO.setTargetUser(String.valueOf(attachmentVO.getAttachId())); 
				advertDAO.updateAdvert(advertVO); 
			}
		}else{
			if(AppUtil.isNotEmpty(advertVO.getTargetUser())){
				AttachmentVO targetAttachVO = attachmentService.getAttachment(Integer.valueOf(advertVO.getTargetUser()));
				if(null == targetAttachVO){
					advertDAO.deleteTargetUser(advertVO);
				}
			}
		}
	}

	@AppOperate(code="delete",desc="删除APP资讯")
	public void deleteAdvert(AdvertVO advertVO) throws AppException {
		AdvertVO persistVO = advertDAO.getAdvertById(advertVO);
		AttachmentVO attachVO = new AttachmentVO();
		if(AppUtil.isNotEmpty(persistVO.getImgUrl())){
			attachVO.setAttachId(Integer.valueOf(persistVO.getImgUrl()));
			attachmentService.deleteAttachmentById(attachVO);
		}
		if(AppUtil.isNotEmpty(persistVO.getHtmlUrl())){
			attachVO.setAttachId(Integer.valueOf(persistVO.getHtmlUrl()));
			attachmentService.deleteAttachmentById(attachVO);
		}
		if(AppUtil.isNotEmpty(persistVO.getTargetUser())){
			attachVO.setAttachId(Integer.valueOf(persistVO.getTargetUser()));
			attachmentService.deleteAttachmentById(attachVO);
		}
		advertDAO.deleteAdvert(advertVO);
	}
	
	@AppOperate(code="release",desc="发布APP资讯")
	public void releaseAdvert(AdvertVO advertVO) throws AppException {
		AdvertVO persistVO = advertDAO.getAdvertById(advertVO);
		persistVO.setAdvertStatus(advertVO.getAdvertStatus());
		advertDAO.updateAdvert(persistVO);
	}

	public AdvertVO getAdvert(Integer advertId) throws AppException {
		AdvertVO advertVO=new AdvertVO();
		advertVO.setAdvertId(advertId);
		return advertDAO.getAdvertById(advertVO);
	}

	public List<AdvertVO> listAdvertApp(AdvertVO advertVO) throws AppException {

		advertVO.setAdvertStatus(Integer.valueOf(DictionaryVO.RELEASE_STATUS_TRUE));
		return advertDAO.listAdvertApp(advertVO);
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
