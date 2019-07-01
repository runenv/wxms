package com.ecp888.wxms.dao.push;

import java.util.List;

import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.push.PushRegisterVO;
import com.ecp888.wxms.vo.push.PushStatisticVO;
import com.ecp888.wxms.vo.push.PushVO;
/**
 * APPPush信息DAO
 * @author ecp875
 * @since 2014-08-24
 */
public interface IPushDAO {
	/**
	 * 查询APPPush信息总记录
	 * @author ecp875
	 * @since 2014-08-24
	 * @param pushVO
	 * @return
	 * @throws AppException
	 */
	int countPush(PushVO pushVO)throws AppException;
	/**
	 * 查询APPPush信息列表
	 * @author ecp875
	 * @since 2014-08-24
	 * @param pushVO
	 * @param pageVO
	 * @return
	 * @throws AppException
	 */
	List<PushVO> listPush(PushVO pushVO, PageVO pageVO)throws AppException;
	/**
	 * 根据pushId查询单个APPPush信息
	 * @author ecp875
	 * @since 2014-08-24
	 * @param pushVO
	 * @return
	 * @throws AppException
	 */
	PushVO getPushById(PushVO pushVO)throws AppException;
	/**
	 * 根据Push名称查询单个APPPush信息
	 * @author ecp875
	 * @since 2014-08-24
	 * @param pushVO
	 * @return
	 * @throws AppException
	 */
	PushVO getPushByName(PushVO pushVO)throws AppException;
	/**
	 * 新增APPPush信息
	 * @author ecp875
	 * @since 2014-08-24
	 * @param pushVO
	 * @throws AppException
	 */
	void addPush(PushVO pushVO)throws AppException;
	/**
	 * 修改APPPush信息
	 * @author ecp875
	 * @since 2014-08-24
	 * @param pushVO
	 * @throws AppException
	 */
	void updatePush(PushVO pushVO)throws AppException;
	
	/**
	 * 删除APPPush信息
	 * @author ecp875
	 * @since 2014-08-24
	 * @param pushVO
	 * @throws AppException
	 */
	void deletePush(PushVO pushVO)throws AppException;
	
	//Push平台
	public int createPushPlatform(PushVO pushVO) throws AppException;
	
	public int deletePushPlatform(PushVO pushVO) throws AppException;
	
	public List<String> findPushPlatform(PushVO pushVO) throws AppException;
	
	//Push渠道
	public int createPushChannel(PushVO pushVO) throws AppException;
	
	public int deletePushChannel(PushVO pushVO) throws AppException;
	
	public List<String> findPushChannel(PushVO pushVO) throws AppException;
	
	//Push版本
	public int createPushVersion(PushVO pushVO) throws AppException;
	
	public int deletePushVersion(PushVO pushVO) throws AppException;
	
	public List<String> findPushVersion(PushVO pushVO) throws AppException;
	
	//目标用户
	public List<String> findTargetUser(PushVO pushVO) throws AppException;
	
	public int addTargetUser(PushVO pushVO) throws AppException;
	
	public int deleteTargetUser(PushVO pushVO) throws AppException;
	
	//自定义推送时间
	public List<String> findTaskDate(PushVO pushVO) throws AppException;
	
	public List<String> findTaskDateByTask(PushVO pushVO) throws AppException;
	
	public int addTaskDate(PushVO pushVO) throws AppException;
	
	public int updateTaskDate(PushVO pushVO) throws AppException;
	
	public int deleteTaskDate(PushVO pushVO) throws AppException;
	
	public int deleteTaskDateByDate(PushVO pushVO) throws AppException;
	
	//App Push列表
	List<PushVO> listPushApp(PushVO pushVO)throws AppException;
	
	//查询APP Push数据统计列表
	List<PushStatisticVO> listStatisticPush(PushStatisticVO pushStatisticVO)throws AppException;
	
	List<PushStatisticVO> listStatisticPushRate(PushStatisticVO pushStatisticVO)throws AppException;
	
	PushStatisticVO getStatisticPush(PushStatisticVO pushStatisticVO)throws AppException;
	
	public int addStatisticPush(PushStatisticVO pushStatisticVO) throws AppException;
	
	public int updateStatisticPushById(PushStatisticVO pushStatisticVO) throws AppException;
	
	public int updateStatisticPush(PushStatisticVO pushStatisticVO) throws AppException;

	//目标用户推送
	PushRegisterVO getPushRegister(PushRegisterVO pushRegisterVO)throws AppException;
	List<PushRegisterVO> getPushRegisterByUser(PushRegisterVO pushRegisterVO)throws AppException;
	
	int addPushRegister(PushRegisterVO pushRegisterVO) throws AppException;
	
	int updatePushRegister(PushRegisterVO pushRegisterVO) throws AppException;
	
	int deletePushRegister(PushRegisterVO pushRegisterVO) throws AppException;
	
	List<String> listRegistrationId(PushVO pushVO) throws AppException;
	
}
