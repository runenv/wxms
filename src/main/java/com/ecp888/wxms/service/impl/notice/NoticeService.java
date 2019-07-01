package com.ecp888.wxms.service.impl.notice;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecp888.wxms.dao.notice.INoticeDAO;
import com.ecp888.wxms.service.attach.IAttachmentService;
import com.ecp888.wxms.service.dict.IDictionaryService;
import com.ecp888.wxms.service.notice.INoticeService;
import com.ecp888.wxms.service.partner.IPartnerService;
import com.ecp888.wxms.utils.AppUtil;
import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.utils.exception.ExConstants;
import com.ecp888.wxms.utils.exception.FunctionException;
import com.ecp888.wxms.utils.file.AttachmentUtil;
import com.ecp888.wxms.utils.file.ImportExcel;
import com.ecp888.wxms.utils.interfaces.AppOperate;
import com.ecp888.wxms.utils.interfaces.AppResource;
import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.attachment.AttachmentVO;
import com.ecp888.wxms.vo.dict.DictionaryItemVO;
import com.ecp888.wxms.vo.dict.DictionaryVO;
import com.ecp888.wxms.vo.notice.NoticeVO;
/**
 * APP广告位信息服务类
 * @author ecp875
 * @since 2014-08-02
 */
@Service("noticeService")
@AppResource(code="NoticeService",desc="APP广告位管理")
public class NoticeService implements INoticeService {
	
	protected Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	INoticeDAO noticeDAO;
	@Autowired
	IAttachmentService attachmentService;
	@Autowired
	IDictionaryService dictService;
	@Autowired
	IPartnerService partnerService;
	
	public List<NoticeVO> listNotice(NoticeVO noticeVO, PageVO pageVO)
			throws AppException{
		int total = noticeDAO.countNotice(noticeVO);
		pageVO.setRecordTotal(total);
		List<NoticeVO> noticeList = noticeDAO.listNotice(noticeVO,pageVO);
		for (NoticeVO noticeVO2 : noticeList) {
			List<String> platformIds = noticeDAO.findNoticePlatform(noticeVO2);
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
				noticeVO2.setPlatform(platformStr.toString());
			}
			if(null != noticeVO2.getActStatus()){
				DictionaryItemVO dictItemVO = dictService.lookupDict(DictionaryVO.NOTICE_ACTION, "0", String.valueOf(noticeVO2.getActStatus()));
				noticeVO2.setActStatusName(dictItemVO.getItemName());
			}
			if(null != noticeVO2.getClassId()){
				DictionaryItemVO dictItemVO = dictService.lookupDict(DictionaryVO.NOTICE_CLASS, "0", String.valueOf(noticeVO2.getClassId()));
				noticeVO2.setClassName(dictItemVO.getItemName());
			}
		}
		return noticeList;
	}
	
	public List<NoticeVO> orderNotice(NoticeVO noticeVO, PageVO pageVO) throws AppException {
		List<NoticeVO> noticeList = listNotice(noticeVO, pageVO);
		NoticeVO currentVO = null;
		NoticeVO targetVO = null;
		if(null == noticeList || noticeList.isEmpty() || noticeList.size() == 1) return noticeList;
		int i = 0;
		for (NoticeVO tempVO : noticeList) {
			if(tempVO.getNoticeId().intValue() == noticeVO.getNoticeId().intValue()){
				currentVO = tempVO;
				if(NoticeVO.DIRECTION_UP.equals(noticeVO.getDirection())){
					i--;
				}else{
					i++;
				}
				targetVO = noticeList.get(i);
				break;
			}
			i++;
		}
		if(currentVO != null && targetVO != null){
			int orderNum = currentVO.getOrderNum();
			currentVO.setOrderNum(targetVO.getOrderNum());
			targetVO.setOrderNum(orderNum);
			noticeDAO.updateNotice(currentVO);
			noticeDAO.updateNotice(targetVO);
		}
		noticeList = listNotice(noticeVO, pageVO);
		return noticeList;
	}

	public NoticeVO getNotice(NoticeVO noticeVO) throws AppException {
		if(null != noticeVO.getNoticeId()){
			noticeVO = noticeDAO.getNoticeById(noticeVO);
		}
		if(null == noticeVO) return null;
		noticeVO.setPartnerMap(partnerService.findAllGroupByMobiletype());
		//广告位平台
		List<String> platformIds = noticeDAO.findNoticePlatform(noticeVO);
		if(null!=platformIds && !platformIds.isEmpty()){
			String[] ids=new String[platformIds.size()];
			platformIds.toArray(ids);
			noticeVO.setArrId(ids);
		}
		//广告位渠道
		List<String> channelIds = noticeDAO.findNoticeChannel(noticeVO);
		if(null!=channelIds && !channelIds.isEmpty()){
			String[] ids=new String[channelIds.size()];
			channelIds.toArray(ids);
			noticeVO.setArrId1(ids);
		}
		//目标用户
		noticeVO.setTargetUserList(noticeDAO.findTargetUser(noticeVO));
		if(AppUtil.isNotEmpty(noticeVO.getTargetUser())){
			AttachmentVO targetUserAttachVO = noticeVO.getTargetUserAttachVO(); 
			targetUserAttachVO.setAttachId(Integer.valueOf(noticeVO.getTargetUser()));
			targetUserAttachVO = attachmentService.getAttachment(targetUserAttachVO);
			noticeVO.setTargetUserAttachVO(targetUserAttachVO);
		}
		return noticeVO;
	}
	
	public NoticeVO getNoticeByTitle(NoticeVO noticeVO) throws AppException {
		return noticeDAO.getNoticeByName(noticeVO);
	}

	@AppOperate(code="update",desc="添加或更新APP广告位")
	public void saveNotice(NoticeVO noticeVO) throws AppException {
		
		if(DictionaryVO.NOTICE_DIRECT_PAGE.equals(noticeVO.getDirectFlag())){
			noticeVO.setOpenUrl("");
		}
		NoticeVO curNotice = noticeDAO.getNoticeByName(noticeVO);		
		if(null==noticeVO.getNoticeId()){
			if (curNotice!=null){
				throw new FunctionException(ExConstants.F_NOTICE_1001);
			}
			noticeVO.setNoticeStatus(Integer.valueOf(DictionaryVO.RELEASE_STATUS_FALSE));
			noticeDAO.addNotice(noticeVO);
			noticeVO.setOrderNum(noticeVO.getNoticeId());
		}else{
			noticeDAO.updateNotice(noticeVO);
		}
		
		if(null == noticeVO.getNoticeId())return;
		//广告位平台
		noticeDAO.deleteNoticePlatform(noticeVO);
		if (null != noticeVO.getArrId()) {
			noticeDAO.createNoticePlatform(noticeVO);
		}
		//广告位渠道
		noticeDAO.deleteNoticeChannel(noticeVO);
		if (null != noticeVO.getArrId1()) {
			noticeDAO.createNoticeChannel(noticeVO);
		}
		
		//附件记录
		AttachmentVO attachmentVO = noticeVO.getAttachmentVO();
		attachmentVO.setAttachMoudle(NoticeVO.ATTACH_MOUDLE);
		attachmentVO.setAttachKey(noticeVO.getNoticeId());
		
		if(AppUtil.isNotEmpty(noticeVO.getImgUrlPath())){
			if(AppUtil.isNotEmpty(noticeVO.getImgUrl())){
				AttachmentVO oldImgUrlAttachVO = new AttachmentVO();
				oldImgUrlAttachVO.setAttachId(Integer.valueOf(noticeVO.getImgUrl()));
				attachmentService.deleteAttachmentById(oldImgUrlAttachVO);
			}
			attachmentVO.setAttachTempPath(attachmentVO.getUploadTempPath() + noticeVO.getImgUrlPath());
			attachmentService.saveAttachment(attachmentVO);
			noticeVO.setImgUrl(String.valueOf(attachmentVO.getAttachId()));
			noticeDAO.updateNotice(noticeVO); //更新广告位ImgUrl
		}
		
		if(AppUtil.isNotEmpty(noticeVO.getHtmlUrlPath())){
			if(AppUtil.isNotEmpty(noticeVO.getHtmlUrl())){
				AttachmentVO oldHtmlUrlAttachVO = new AttachmentVO();
				oldHtmlUrlAttachVO.setAttachId(Integer.valueOf(noticeVO.getHtmlUrl()));
				attachmentService.deleteAttachmentById(oldHtmlUrlAttachVO);
			}
			attachmentVO.setAttachTempPath(attachmentVO.getUploadTempPath() + noticeVO.getHtmlUrlPath());
			attachmentService.saveAttachment(attachmentVO);
			noticeVO.setHtmlUrl(String.valueOf(attachmentVO.getAttachId()));
			noticeDAO.updateNotice(noticeVO); //更新广告位HtmlUrl
		}
		//目标用户
		if(AppUtil.isNotEmpty(noticeVO.getTargetUserPath())){
			if(AppUtil.isNotEmpty(noticeVO.getTargetUser())){
				AttachmentVO oldUserAttachVO = noticeVO.getTargetUserAttachVO();
				oldUserAttachVO.setAttachId(Integer.valueOf(noticeVO.getTargetUser()));
				attachmentService.deleteAttachmentById(oldUserAttachVO);
			}
			attachmentVO.setAttachTempPath(attachmentVO.getUploadTempPath() + noticeVO.getTargetUserPath());
			attachmentVO = attachmentService.saveAttachment(attachmentVO); 
			List<String> userTargetList = importTargetUser(attachmentVO.getAttachPath());
			noticeVO.setTargetUserList(userTargetList);
			
			noticeDAO.deleteTargetUser(noticeVO);
			if(null != noticeVO.getTargetUserList() 
					&& !noticeVO.getTargetUserList().isEmpty()){
				noticeDAO.addTargetUser(noticeVO);
			}
			if(null != attachmentVO.getAttachId()){
				noticeVO.setTargetUser(String.valueOf(attachmentVO.getAttachId())); 
				noticeDAO.updateNotice(noticeVO); 
			}
		}else{
			if(AppUtil.isNotEmpty(noticeVO.getTargetUser())){
				AttachmentVO targetAttachVO = attachmentService.getAttachment(Integer.valueOf(noticeVO.getTargetUser()));
				if(null == targetAttachVO){
					noticeDAO.deleteTargetUser(noticeVO);
				}
			}
		}
	}

	@AppOperate(code="delete",desc="删除APP广告位")
	public void deleteNotice(NoticeVO noticeVO) throws AppException {
		NoticeVO persistVO = noticeDAO.getNoticeById(noticeVO);
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
		noticeDAO.deleteNotice(noticeVO);
	}
	
	@AppOperate(code="release",desc="发布APP广告位")
	public void releaseNotice(NoticeVO noticeVO) throws AppException {
		NoticeVO persistVO = noticeDAO.getNoticeById(noticeVO);
		persistVO.setNoticeStatus(noticeVO.getNoticeStatus());
		noticeDAO.updateNotice(persistVO);
	}

	public NoticeVO getNotice(Integer noticeId) throws AppException {
		NoticeVO noticeVO=new NoticeVO();
		noticeVO.setNoticeId(noticeId);
		return noticeDAO.getNoticeById(noticeVO);
	}

	public List<NoticeVO> listNoticeApp(NoticeVO noticeVO) throws AppException {

		noticeVO.setNoticeStatus(Integer.valueOf(DictionaryVO.RELEASE_STATUS_TRUE));
		return noticeDAO.listNoticeApp(noticeVO);
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
