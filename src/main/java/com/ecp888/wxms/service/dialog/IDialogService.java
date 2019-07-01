package com.ecp888.wxms.service.dialog;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.dialog.DialogVO;

/**
 * APP弹窗信息服务类
 * @author ecp875
 * @since 2014-08-02
 */
@Consumes("application/json")
@Produces({"application/json","application/xml"})
@Path("/dialog")
public interface IDialogService {
	/**
	 * 查询列表APP弹窗信息
	 * @author ecp875
	 * @since 2014-08-02
	 * @param dialogVO
	 * @param pageVO
	 * @return
	 * @throws AppException
	 */
	List<DialogVO> listDialog(DialogVO dialogVO,PageVO pageVO)throws AppException;
	/**
	 * 排序APP弹窗信息
	 * @param dialogVO
	 * @throws AppException
	 */
	List<DialogVO> orderDialog(DialogVO dialogVO,PageVO pageVO)throws AppException;
	/**
	 * 查询单个APP弹窗信息
	 * @author ecp875
	 * @since 2014-08-02
	 * @param dialogVO
	 * @return
	 * @throws AppException
	 */
	@GET
	@Path("/getDialog/{dialogId}")
	DialogVO getDialog(@PathParam("dialogId")DialogVO dialogVO)throws AppException;
	
	public DialogVO getDialogByTitle(DialogVO dialogVO) throws AppException;
	
	/**
	 * 保存APP弹窗信息
	 * @author ecp875
	 * @since 2014-08-02
	 * @param dialogVO
	 * @throws AppException
	 */
	void saveDialog(DialogVO dialogVO)throws AppException;
	/**
	 * 删除APP弹窗信息
	 * @author ecp875
	 * @since 2014-08-02
	 * @param dialogVO
	 * @throws AppException
	 */
	void deleteDialog(DialogVO dialogVO)throws AppException;
	/**
	 * 发布APP弹窗信息
	 * @author ecp875
	 * @since 2014-08-02
	 * @param dialogVO
	 * @throws AppException
	 */
	void releaseDialog(DialogVO dialogVO)throws AppException;
	
	/**
	 * 根据平台号、渠道号查询APP弹窗列表
	 * @author ecp875
	 * @since 2014-08-02
	 * @param noticeVO
	 * @return
	 * @throws AppException
	 */
	List<DialogVO> listDialogApp(DialogVO dialogVO)throws AppException;

}
