package com.ecp888.wxms.service.advert;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.advert.AdvertVO;

/**
 * APP资讯信息服务类
 * @author ecp875
 * @since 2014-08-02
 */
@Consumes("application/json")
@Produces({"application/json","application/xml"})
@Path("/advert")
public interface IAdvertService {
	/**
	 * 查询列表APP资讯信息
	 * @author ecp875
	 * @since 2014-08-02
	 * @param advertVO
	 * @param pageVO
	 * @return
	 * @throws AppException
	 */
	List<AdvertVO> listAdvert(AdvertVO advertVO,PageVO pageVO)throws AppException;
	/**
	 * 排序APP资讯信息
	 * @param advertVO
	 * @throws AppException
	 */
	List<AdvertVO> orderAdvert(AdvertVO advertVO,PageVO pageVO)throws AppException;
	/**
	 * 查询单个APP资讯信息
	 * @author ecp875
	 * @since 2014-08-02
	 * @param advertVO
	 * @return
	 * @throws AppException
	 */
	@GET
	@Path("/getAdvert/{advertId}")
	AdvertVO getAdvert(@PathParam("advertId")AdvertVO advertVO)throws AppException;
	
	/**
	 * 根据标题查询单个APP资讯信息
	 * @param advertVO
	 * @return
	 * @throws AppException
	 */
	public AdvertVO getAdvertByTitle(AdvertVO advertVO) throws AppException;
	
	/**
	 * 保存APP资讯信息
	 * @author ecp875
	 * @since 2014-08-02
	 * @param advertVO
	 * @throws AppException
	 */
	void saveAdvert(AdvertVO advertVO)throws AppException;
	/**
	 * 删除APP资讯信息
	 * @author ecp875
	 * @since 2014-08-02
	 * @param advertVO
	 * @throws AppException
	 */
	void deleteAdvert(AdvertVO advertVO)throws AppException;
	/**
	 * 发布APP资讯信息
	 * @author ecp875
	 * @since 2014-08-02
	 * @param advertVO
	 * @throws AppException
	 */
	void releaseAdvert(AdvertVO advertVO)throws AppException;
	
	/**
	 * 根据平台号、渠道号查询APP资讯列表
	 * @author ecp875
	 * @since 2014-08-02
	 * @param advertVO
	 * @return
	 * @throws AppException
	 */
	List<AdvertVO> listAdvertApp(AdvertVO advertVO)throws AppException;

}
