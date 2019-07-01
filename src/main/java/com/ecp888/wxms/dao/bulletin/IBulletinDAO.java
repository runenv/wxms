package com.ecp888.wxms.dao.bulletin;

import java.util.List;
import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.bulletin.BulletinVO;
/**
 * APP公告信息DAO
 * @author ecp875
 * @since 2014-08-02
 */
public interface IBulletinDAO {
	/**
	 * 查询APP公告信息总记录
	 * @author ecp875
	 * @since 2014-07-07
	 * @param bulletinVO
	 * @return
	 * @throws AppException
	 */
	int countBulletin(BulletinVO bulletinVO)throws AppException;
	/**
	 * 查询APP公告信息列表
	 * @author ecp875
	 * @since 2014-07-07
	 * @param bulletinVO
	 * @param pageVO
	 * @return
	 * @throws AppException
	 */
	List<BulletinVO> listBulletin(BulletinVO bulletinVO, PageVO pageVO)throws AppException;
	/**
	 * 根据bulletinId查询单个APP公告信息
	 * @author ecp875
	 * @since 2014-07-07
	 * @param bulletinVO
	 * @return
	 * @throws AppException
	 */
	BulletinVO getBulletinById(BulletinVO bulletinVO)throws AppException;
	/**
	 * 根据公告名称查询单个APP公告信息
	 * @author ecp875
	 * @since 2014-07-07
	 * @param bulletinVO
	 * @return
	 * @throws AppException
	 */
	BulletinVO getBulletinByName(BulletinVO bulletinVO)throws AppException;
	/**
	 * 新增APP公告信息
	 * @author ecp875
	 * @since 2014-07-07
	 * @param bulletinVO
	 * @throws AppException
	 */
	void addBulletin(BulletinVO bulletinVO)throws AppException;
	/**
	 * 修改APP公告信息
	 * @author ecp875
	 * @since 2014-07-07
	 * @param bulletinVO
	 * @throws AppException
	 */
	void updateBulletin(BulletinVO bulletinVO)throws AppException;
	
	/**
	 * 删除APP公告信息
	 * @author ecp875
	 * @since 2014-07-07
	 * @param bulletinVO
	 * @throws AppException
	 */
	void deleteBulletin(BulletinVO bulletinVO)throws AppException;
	
	//公告平台
	public int createBulletinPlatform(BulletinVO bulletinVO) throws AppException;
	
	public int deleteBulletinPlatform(BulletinVO bulletinVO) throws AppException;
	
	public List<String> findBulletinPlatform(BulletinVO bulletinVO) throws AppException;
	
	//公告渠道
	public int createBulletinChannel(BulletinVO bulletinVO) throws AppException;
	
	public int deleteBulletinChannel(BulletinVO bulletinVO) throws AppException;
	
	public List<String> findBulletinChannel(BulletinVO bulletinVO) throws AppException;
	
	public List<String> findTargetUser(BulletinVO bulletinVO) throws AppException;
	
	public int addTargetUser(BulletinVO bulletinVO) throws AppException;
	
	public int deleteTargetUser(BulletinVO bulletinVO) throws AppException;
	
	List<BulletinVO> listBulletinApp(BulletinVO bulletinVO)throws AppException;
	
}
