package com.ecp888.wxms.service.impl.dialog;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecp888.wxms.dao.dialog.IDialogDAO;
import com.ecp888.wxms.service.attach.IAttachmentService;
import com.ecp888.wxms.service.dict.IDictionaryService;
import com.ecp888.wxms.service.dialog.IDialogService;
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
import com.ecp888.wxms.vo.dialog.DialogVO;
/**
 * APP弹窗信息服务类
 * @author ecp875
 * @since 2014-08-02
 */
@Service("dialogService")
@AppResource(code="DialogService",desc="APP弹窗管理")
public class DialogService implements IDialogService {
	
	protected Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	IDialogDAO dialogDAO;
	@Autowired
	IAttachmentService attachmentService;
	@Autowired
	IDictionaryService dictService;
	@Autowired
	IPartnerService partnerService;
	
	public List<DialogVO> listDialog(DialogVO dialogVO, PageVO pageVO)
			throws AppException{
		int total = dialogDAO.countDialog(dialogVO);
		pageVO.setRecordTotal(total);
		List<DialogVO> dialogList = dialogDAO.listDialog(dialogVO,pageVO);
		for (DialogVO dialogVO2 : dialogList) {
			List<String> platformIds = dialogDAO.findDialogPlatform(dialogVO2);
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
				dialogVO2.setPlatform(platformStr.toString());
			}
			//版本名称
			List<String> versionIds = dialogDAO.findDialogVersion(dialogVO2);
			if(null !=versionIds && !versionIds.isEmpty()){
				StringBuffer versionStr = new StringBuffer();
				for(int j = 0; j< versionIds.size(); j++){
					DictionaryItemVO dictItemVO = dictService.lookupDict(DictionaryVO.PUSH_VERSION, "0", versionIds.get(j));
					versionStr.append(dictItemVO.getItemName());
					if(j < versionIds.size() - 1){
						versionStr.append(",");
					}
				}
				dialogVO2.setVersion(versionStr.toString());
			}
			if(null != dialogVO2.getActStatus()){
				DictionaryItemVO dictItemVO = dictService.lookupDict(DictionaryVO.NOTICE_ACTION, "0", String.valueOf(dialogVO2.getActStatus()));
				dialogVO2.setActStatusName(dictItemVO.getItemName());
			}
			if(null != dialogVO2.getClassId()){
				DictionaryItemVO dictItemVO = dictService.lookupDict(DictionaryVO.NOTICE_CLASS, "0", String.valueOf(dialogVO2.getClassId()));
				dialogVO2.setClassName(dictItemVO.getItemName());
			}
		}
		return dialogList;
	}
	
	public List<DialogVO> orderDialog(DialogVO dialogVO, PageVO pageVO) throws AppException {
		List<DialogVO> dialogList = listDialog(dialogVO, pageVO);
		DialogVO currentVO = null;
		DialogVO targetVO = null;
		if(null == dialogList || dialogList.isEmpty() || dialogList.size() == 1) return dialogList;
		int i = 0;
		for (DialogVO tempVO : dialogList) {
			if(tempVO.getDialogId().intValue() == dialogVO.getDialogId().intValue()){
				currentVO = tempVO;
				if(DialogVO.DIRECTION_UP.equals(dialogVO.getDirection())){
					i--;
				}else{
					i++;
				}
				targetVO = dialogList.get(i);
				break;
			}
			i++;
		}
		if(currentVO != null && targetVO != null){
			int orderNum = currentVO.getOrderNum();
			currentVO.setOrderNum(targetVO.getOrderNum());
			targetVO.setOrderNum(orderNum);
			dialogDAO.updateDialog(currentVO);
			dialogDAO.updateDialog(targetVO);
		}
		dialogList = listDialog(dialogVO, pageVO);
		return dialogList;
	}

	public DialogVO getDialog(DialogVO dialogVO) throws AppException {
		if(null != dialogVO.getDialogId()){
			dialogVO = dialogDAO.getDialogById(dialogVO);
		}
		if(null == dialogVO) return null;
		dialogVO.setPartnerMap(partnerService.findAllGroupByMobiletype());
		//弹窗平台
		List<String> platformIds = dialogDAO.findDialogPlatform(dialogVO);
		if(null!=platformIds && !platformIds.isEmpty()){
			String[] ids=new String[platformIds.size()];
			platformIds.toArray(ids);
			dialogVO.setArrId(ids);
		}
		//弹窗渠道
		List<String> channelIds = dialogDAO.findDialogChannel(dialogVO);
		if(null!=channelIds && !channelIds.isEmpty()){
			String[] ids=new String[channelIds.size()];
			channelIds.toArray(ids);
			dialogVO.setArrId1(ids);
		}
		//Push版本
		List<String> versionIds = dialogDAO.findDialogVersion(dialogVO);
		if(null!=versionIds && !versionIds.isEmpty()){
			String[] ids=new String[versionIds.size()];
			versionIds.toArray(ids);
			dialogVO.setArrId2(ids);
		}
		//目标用户
		dialogVO.setTargetUserList(dialogDAO.findTargetUser(dialogVO));
		if(AppUtil.isNotEmpty(dialogVO.getTargetUser())){
			AttachmentVO targetUserAttachVO = dialogVO.getTargetUserAttachVO(); 
			targetUserAttachVO.setAttachId(Integer.valueOf(dialogVO.getTargetUser()));
			targetUserAttachVO = attachmentService.getAttachment(targetUserAttachVO);
			dialogVO.setTargetUserAttachVO(targetUserAttachVO);
		}
		return dialogVO;
	}
	
	public DialogVO getDialogByTitle(DialogVO dialogVO) throws AppException {
		return dialogDAO.getDialogByName(dialogVO);
	}

	@AppOperate(code="update",desc="添加或更新APP弹窗")
	public void saveDialog(DialogVO dialogVO) throws AppException {
		
		DialogVO curDialog = dialogDAO.getDialogByName(dialogVO);		
		if(null==dialogVO.getDialogId()){
			if (curDialog!=null){
				throw new FunctionException(ExConstants.F_DIALOG_1001);
			}
			dialogVO.setDialogStatus(Integer.valueOf(DictionaryVO.RELEASE_STATUS_FALSE));
			dialogDAO.addDialog(dialogVO);
			dialogVO.setOrderNum(dialogVO.getDialogId());
		}else{
			dialogDAO.updateDialog(dialogVO);
		}
		
		if(null == dialogVO.getDialogId())return;
		//弹窗平台
		dialogDAO.deleteDialogPlatform(dialogVO);
		if (null != dialogVO.getArrId()) {
			dialogDAO.createDialogPlatform(dialogVO);
		}
		//弹窗渠道
		dialogDAO.deleteDialogChannel(dialogVO);
		if (null != dialogVO.getArrId1()) {
			dialogDAO.createDialogChannel(dialogVO);
		}
		//弹窗版本
		dialogDAO.deleteDialogVersion(dialogVO);
		if (null != dialogVO.getArrId2()) {
			dialogDAO.createDialogVersion(dialogVO);
		}
		//附件记录
		AttachmentVO attachmentVO = dialogVO.getAttachmentVO();
		attachmentVO.setAttachMoudle(DialogVO.ATTACH_MOUDLE);
		attachmentVO.setAttachKey(dialogVO.getDialogId());
		
		if(AppUtil.isNotEmpty(dialogVO.getImgUrlPath())){
			if(AppUtil.isNotEmpty(dialogVO.getImgUrl())){
				AttachmentVO oldImgUrlAttachVO = new AttachmentVO();
				oldImgUrlAttachVO.setAttachId(Integer.valueOf(dialogVO.getImgUrl()));
				attachmentService.deleteAttachmentById(oldImgUrlAttachVO);
			}
			attachmentVO.setAttachTempPath(attachmentVO.getUploadTempPath() + dialogVO.getImgUrlPath());
			attachmentService.saveAttachment(attachmentVO);
			dialogVO.setImgUrl(String.valueOf(attachmentVO.getAttachId()));
			dialogDAO.updateDialog(dialogVO); //更新广告位ImgUrl
		}
		
		if(AppUtil.isNotEmpty(dialogVO.getHtmlUrlPath())){
			if(AppUtil.isNotEmpty(dialogVO.getHtmlUrl())){
				AttachmentVO oldHtmlUrlAttachVO = new AttachmentVO();
				oldHtmlUrlAttachVO.setAttachId(Integer.valueOf(dialogVO.getHtmlUrl()));
				attachmentService.deleteAttachmentById(oldHtmlUrlAttachVO);
			}
			attachmentVO.setAttachTempPath(attachmentVO.getUploadTempPath() + dialogVO.getHtmlUrlPath());
			attachmentService.saveAttachment(attachmentVO);
			dialogVO.setHtmlUrl(String.valueOf(attachmentVO.getAttachId()));
			dialogDAO.updateDialog(dialogVO); //更新广告位HtmlUrl
		}
		//目标用户
		if(AppUtil.isNotEmpty(dialogVO.getTargetUserPath())){
			if(AppUtil.isNotEmpty(dialogVO.getTargetUser())){
				AttachmentVO oldUserAttachVO = dialogVO.getTargetUserAttachVO();
				oldUserAttachVO.setAttachId(Integer.valueOf(dialogVO.getTargetUser()));
				attachmentService.deleteAttachmentById(oldUserAttachVO);
			}
			attachmentVO.setAttachTempPath(attachmentVO.getUploadTempPath() + dialogVO.getTargetUserPath());
			attachmentVO = attachmentService.saveAttachment(attachmentVO); 
			List<String> userTargetList = importTargetUser(attachmentVO.getAttachPath());
			dialogVO.setTargetUserList(userTargetList);
			
			dialogDAO.deleteTargetUser(dialogVO);
			if(null != dialogVO.getTargetUserList() 
					&& !dialogVO.getTargetUserList().isEmpty()){
				dialogDAO.addTargetUser(dialogVO);
			}
			if(null != attachmentVO.getAttachId()){
				dialogVO.setTargetUser(String.valueOf(attachmentVO.getAttachId())); 
				dialogDAO.updateDialog(dialogVO); 
			}
		}else{
			if(AppUtil.isNotEmpty(dialogVO.getTargetUser())){
				AttachmentVO targetAttachVO = attachmentService.getAttachment(Integer.valueOf(dialogVO.getTargetUser()));
				if(null == targetAttachVO){
					dialogDAO.deleteTargetUser(dialogVO);
				}
			}
		}
	}

	@AppOperate(code="delete",desc="删除APP弹窗")
	public void deleteDialog(DialogVO dialogVO) throws AppException {
		DialogVO persistVO = dialogDAO.getDialogById(dialogVO);
		AttachmentVO attachVO = new AttachmentVO();
		if(AppUtil.isNotEmpty(persistVO.getImgUrl())){
			attachVO.setAttachId(Integer.valueOf(persistVO.getImgUrl()));
			attachmentService.deleteAttachmentById(attachVO);
		}
		if(AppUtil.isNotEmpty(persistVO.getTargetUser())){
			attachVO.setAttachId(Integer.valueOf(persistVO.getTargetUser()));
			attachmentService.deleteAttachmentById(attachVO);
		}
		dialogDAO.deleteDialog(dialogVO);
	}
	
	@AppOperate(code="release",desc="发布APP弹窗")
	public void releaseDialog(DialogVO dialogVO) throws AppException {
		DialogVO persistVO = dialogDAO.getDialogById(dialogVO);
		persistVO.setDialogStatus(dialogVO.getDialogStatus());
		dialogDAO.updateDialog(persistVO);
	}

	public DialogVO getDialog(Integer dialogId) throws AppException {
		DialogVO dialogVO=new DialogVO();
		dialogVO.setDialogId(dialogId);
		return dialogDAO.getDialogById(dialogVO);
	}

	public List<DialogVO> listDialogApp(DialogVO dialogVO) throws AppException {
		dialogVO.setDialogStatus(Integer.valueOf(DictionaryVO.RELEASE_STATUS_TRUE));
		return dialogDAO.listDialogApp(dialogVO);
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
