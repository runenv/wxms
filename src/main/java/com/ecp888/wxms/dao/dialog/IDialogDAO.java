package com.ecp888.wxms.dao.dialog;

import java.util.List;

import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.dialog.DialogVO;
/**
 * APP弹窗信息DAO
 * @author ecp875
 * @since 2014-08-02
 */
public interface IDialogDAO {
	/**
	 * 查询APP弹窗信息总记录
	 * @author ecp875
	 * @since 2014-07-07
	 * @param dialogVO
	 * @return
	 * @throws AppException
	 */
	int countDialog(DialogVO dialogVO)throws AppException;
	/**
	 * 查询APP弹窗信息列表
	 * @author ecp875
	 * @since 2014-07-07
	 * @param dialogVO
	 * @param pageVO
	 * @return
	 * @throws AppException
	 */
	List<DialogVO> listDialog(DialogVO dialogVO, PageVO pageVO)throws AppException;
	/**
	 * 根据dialogId查询单个APP弹窗信息
	 * @author ecp875
	 * @since 2014-07-07
	 * @param dialogVO
	 * @return
	 * @throws AppException
	 */
	DialogVO getDialogById(DialogVO dialogVO)throws AppException;
	/**
	 * 根据弹窗名称查询单个APP弹窗信息
	 * @author ecp875
	 * @since 2014-07-07
	 * @param dialogVO
	 * @return
	 * @throws AppException
	 */
	DialogVO getDialogByName(DialogVO dialogVO)throws AppException;
	/**
	 * 新增APP弹窗信息
	 * @author ecp875
	 * @since 2014-07-07
	 * @param dialogVO
	 * @throws AppException
	 */
	void addDialog(DialogVO dialogVO)throws AppException;
	/**
	 * 修改APP弹窗信息
	 * @author ecp875
	 * @since 2014-07-07
	 * @param dialogVO
	 * @throws AppException
	 */
	void updateDialog(DialogVO dialogVO)throws AppException;
	
	/**
	 * 删除APP弹窗信息
	 * @author ecp875
	 * @since 2014-07-07
	 * @param dialogVO
	 * @throws AppException
	 */
	void deleteDialog(DialogVO dialogVO)throws AppException;
	
	//弹窗平台
	public int createDialogPlatform(DialogVO dialogVO) throws AppException;
	
	public int deleteDialogPlatform(DialogVO dialogVO) throws AppException;
	
	public List<String> findDialogPlatform(DialogVO dialogVO) throws AppException;
	
	//弹窗渠道
	public int createDialogChannel(DialogVO dialogVO) throws AppException;
	
	public int deleteDialogChannel(DialogVO dialogVO) throws AppException;
	
	public List<String> findDialogChannel(DialogVO dialogVO) throws AppException;
	
	//弹窗版本
	public int createDialogVersion(DialogVO dialogVO) throws AppException;
	
	public int deleteDialogVersion(DialogVO dialogVO) throws AppException;
	
	public List<String> findDialogVersion(DialogVO dialogVO) throws AppException;
	
	//目标用户
	public List<String> findTargetUser(DialogVO dialogVO) throws AppException;
	
	public int addTargetUser(DialogVO dialogVO) throws AppException;
	
	public int deleteTargetUser(DialogVO dialogVO) throws AppException;
	
	List<DialogVO> listDialogApp(DialogVO dialogVO)throws AppException;
	
}
