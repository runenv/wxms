package com.ecp888.wxms.web.controller.dialog;

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

import com.ecp888.wxms.service.dialog.IDialogService;
import com.ecp888.wxms.service.partner.IPartnerService;
import com.ecp888.wxms.utils.AppUtil;
import com.ecp888.wxms.vo.client.BaseResponse;
import com.ecp888.wxms.vo.client.dialog.Dialog;
import com.ecp888.wxms.vo.client.dialog.DialogDetailRequest;
import com.ecp888.wxms.vo.client.dialog.DialogDetailResponse;
import com.ecp888.wxms.vo.client.dialog.DialogListRequest;
import com.ecp888.wxms.vo.client.dialog.DialogListResponse;
import com.ecp888.wxms.vo.dialog.DialogVO;
import com.ecp888.wxms.web.controller.common.BaseController;

/**
 * APP Dialog信息Controller
 * @author ecp875
 * @since 2014-08-24
 */
@Controller
@RequestMapping(value = "/dialog")
public class DialogAppController extends BaseController{
	
	Log log = LogFactory.getLog(getClass());
	@Autowired
	IDialogService dialogService;
	@Autowired
	IPartnerService partnerService;
	/**
	 * App Dialog列表
	 * http://localhost:8080/wxms/dialog/app/dialog_app!index.shtml?data={"channelNo":"1143","platform":"2"}
	 * @param map
	 * @param dialogForm
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/dialog_app!index.shtml")
	public String index(ModelMap map,@ModelAttribute("dialogForm") DialogForm dialogForm,
			HttpServletResponse response)throws Exception{
		DialogVO reqDialogVO = dialogForm.getDialogVO();
		DialogListResponse appResponse = new DialogListResponse();
		DialogListRequest dialogListRequest = null;
		//String data = dialogForm.getData() ;
		String data = new String(dialogForm.getData().getBytes("ISO8859-1"), "utf-8");
		
		dialogListRequest = AppUtil.string2Object(data, DialogListRequest.class);
		if (dialogListRequest.getPlatform() == null || dialogListRequest.getChannelNo() == null) {
            appResponse.setStatusCode(BaseResponse.REQUEST_DATA_EXCEPTION);
        } else {
        	//渠道号
            String channelNo = convertChannelNo(dialogListRequest.getChannelNo());
            String parentChannelNo = partnerService.getParentChannelNo(channelNo);
            log.info("App传入的渠道号: " + dialogListRequest.getChannelNo());
            log.info("App实际查询的渠道号: " + parentChannelNo);
            
            String ggPlatform = convertPlatform(dialogListRequest.getPlatform());
            log.info("App传入的平台号: " + dialogListRequest.getPlatform());
            log.info("App实际查询的平台号: " + ggPlatform);
            
            reqDialogVO.setPlatform(ggPlatform);
            reqDialogVO.setChannel(parentChannelNo);
            reqDialogVO.setVersion(dialogListRequest.getVersion());
            reqDialogVO.setUserName(dialogListRequest.getUserName());
            List<DialogVO> dialogList = dialogService.listDialogApp(reqDialogVO);
            List<Dialog> appDialogs = new ArrayList<Dialog>();
            if(dialogList != null && !dialogList.isEmpty()) {
            	for (DialogVO dialogVO : dialogList) {
            		Dialog appDialog = new Dialog();
            		BeanUtils.copyProperties(appDialog, dialogVO);
            		DialogVO persistVO = dialogService.getDialog(dialogVO);
            		appDialog.setContent(persistVO.getContent());
                    appDialog.setContentUrl("/dialog/app/showContent?dialogVO.dialogId=" + dialogVO.getDialogId());
            		appDialogs.add(appDialog);
            	}
            	appResponse.setDialogArray(appDialogs);
                appResponse.setCounts(String.valueOf(appDialogs.size()));
            }
        }
		writeToClient(AppUtil.object2Json(appResponse),response);
		return null;
	}
	
	/**
	 * App Dialog详情
	 * http://localhost:8080/wxms/dialog/app/dialog_app!show.shtml?data={"dialogId":"178"}
	 * @param map
	 * @param dialogForm
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/dialog_app!show.shtml")
	public String show(ModelMap map,@ModelAttribute("dialogForm") DialogForm dialogForm,
			HttpServletResponse response)throws Exception{
		String data = dialogForm.getData() ;
		DialogDetailRequest dialogDetailRequest = AppUtil.string2Object(data, DialogDetailRequest.class);
		DialogDetailResponse appResponse = new DialogDetailResponse();
		if (null == dialogDetailRequest || null == dialogDetailRequest.getDialogId()) {
            appResponse.setStatusCode(DialogDetailResponse.REQUEST_DATA_EXCEPTION);
            appResponse.setErrorMsg("请传入 DialogID");
        } else {
        	DialogVO paramVO = dialogForm.getDialogVO();
        	paramVO.setDialogId(Integer.valueOf(dialogDetailRequest.getDialogId()));
        	DialogVO dialogVO = dialogService.getDialog(paramVO);
            if (dialogVO == null) {
                appResponse.setErrorMsg("没有该记录");
            } else {
                BeanUtils.copyProperties(appResponse, dialogVO);
                DialogVO persistVO = dialogService.getDialog(dialogVO);
                appResponse.setContent(persistVO.getContent());
                appResponse.setContentUrl("/dialog/app/showContent?dialogVO.dialogId=" + dialogVO.getDialogId());
            }
        }
		writeToClient(AppUtil.object2Json(appResponse),response);
		return null;
	}
	
	/**
	 * 显示弹窗内容
	 * @param map
	 * @param dialogForm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/showContent")
	public String showContent(ModelMap map,@ModelAttribute("dialogForm") DialogForm dialogForm
			)throws Exception{
		DialogVO dialogVO = dialogForm.getDialogVO();
		dialogVO = dialogService.getDialog(dialogVO);
		dialogForm.setDialogVO(dialogVO);
		return "/dialog/showContent";
	}
}
