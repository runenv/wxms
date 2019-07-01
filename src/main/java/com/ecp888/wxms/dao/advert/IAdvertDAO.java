package com.ecp888.wxms.dao.advert;

import java.util.List;
import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.advert.AdvertVO;
/**
 * APP资讯信息DAO
 * @author ecp875
 * @since 2014-08-02
 */
public interface IAdvertDAO {
	/**
	 * 查询APP资讯信息总记录
	 * @author ecp875
	 * @since 2014-07-07
	 * @param advertVO
	 * @return
	 * @throws AppException
	 */
	int countAdvert(AdvertVO advertVO)throws AppException;
	/**
	 * 查询APP资讯信息列表
	 * @author ecp875
	 * @since 2014-07-07
	 * @param advertVO
	 * @param pageVO
	 * @return
	 * @throws AppException
	 */
	List<AdvertVO> listAdvert(AdvertVO advertVO, PageVO pageVO)throws AppException;
	/**
	 * 根据advertId查询单个APP资讯信息
	 * @author ecp875
	 * @since 2014-07-07
	 * @param advertVO
	 * @return
	 * @throws AppException
	 */
	AdvertVO getAdvertById(AdvertVO advertVO)throws AppException;
	/**
	 * 根据资讯名称查询单个APP资讯信息
	 * @author ecp875
	 * @since 2014-07-07
	 * @param advertVO
	 * @return
	 * @throws AppException
	 */
	AdvertVO getAdvertByName(AdvertVO advertVO)throws AppException;
	/**
	 * 新增APP资讯信息
	 * @author ecp875
	 * @since 2014-07-07
	 * @param advertVO
	 * @throws AppException
	 */
	void addAdvert(AdvertVO advertVO)throws AppException;
	/**
	 * 修改APP资讯信息
	 * @author ecp875
	 * @since 2014-07-07
	 * @param advertVO
	 * @throws AppException
	 */
	void updateAdvert(AdvertVO advertVO)throws AppException;
	
	/**
	 * 删除APP资讯信息
	 * @author ecp875
	 * @since 2014-07-07
	 * @param advertVO
	 * @throws AppException
	 */
	void deleteAdvert(AdvertVO advertVO)throws AppException;
	
	//资讯平台
	public int createAdvertPlatform(AdvertVO advertVO) throws AppException;
	
	public int deleteAdvertPlatform(AdvertVO advertVO) throws AppException;
	
	public List<String> findAdvertPlatform(AdvertVO advertVO) throws AppException;
	
	//资讯渠道
	public int createAdvertChannel(AdvertVO advertVO) throws AppException;
	
	public int deleteAdvertChannel(AdvertVO advertVO) throws AppException;
	
	public List<String> findAdvertChannel(AdvertVO advertVO) throws AppException;
	
	public List<String> findTargetUser(AdvertVO advertVO) throws AppException;
	
	public int addTargetUser(AdvertVO advertVO) throws AppException;
	
	public int deleteTargetUser(AdvertVO advertVO) throws AppException;
	
	List<AdvertVO> listAdvertApp(AdvertVO advertVO) throws AppException;
	
}
