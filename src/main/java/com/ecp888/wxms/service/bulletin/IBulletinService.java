package com.ecp888.wxms.service.bulletin;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.bulletin.BulletinVO;

/**
 * APP公告信息服务类
 * @author ecp875
 * @since 2014-08-02
 */
@Consumes("application/json")
@Produces({"application/json","application/xml"})
@Path("/bulletin")
public interface IBulletinService {
	/**
	 * 查询列表APP公告信息
	 * @author ecp875
	 * @since 2014-08-02
	 * @param bulletinVO
	 * @param pageVO
	 * @return
	 * @throws AppException
	 */
	List<BulletinVO> listBulletin(BulletinVO bulletinVO,PageVO pageVO)throws AppException;
	/**
	 * 排序APP公告信息
	 * @param bulletinVO
	 * @throws AppException
	 */
	List<BulletinVO> orderBulletin(BulletinVO bulletinVO,PageVO pageVO)throws AppException;
	/**
	 * 查询单个APP公告信息
	 * @author ecp875
	 * @since 2014-08-02
	 * @param bulletinVO
	 * @return
	 * @throws AppException
	 */
	@GET
	@Path("/getBulletin/{bulletinId}")
	BulletinVO getBulletin(@PathParam("bulletinId")BulletinVO bulletinVO)throws AppException;
	
	public BulletinVO getBulletinByTitle(BulletinVO bulletinVO) throws AppException;
	
	/**
	 * 保存APP公告信息
	 * @author ecp875
	 * @since 2014-08-02
	 * @param bulletinVO
	 * @throws AppException
	 */
	void saveBulletin(BulletinVO bulletinVO)throws AppException;
	/**
	 * 删除APP公告信息
	 * @author ecp875
	 * @since 2014-08-02
	 * @param bulletinVO
	 * @throws AppException
	 */
	void deleteBulletin(BulletinVO bulletinVO)throws AppException;
	/**
	 * 发布APP公告信息
	 * @author ecp875
	 * @since 2014-08-02
	 * @param bulletinVO
	 * @throws AppException
	 */
	void releaseBulletin(BulletinVO bulletinVO)throws AppException;
	
	/**
	 * 根据平台号、渠道号查询APP公告列表
	 * @author ecp875
	 * @since 2014-08-02
	 * @param bulletinVO
	 * @return
	 * @throws AppException
	 */
	List<BulletinVO> listBulletinApp(BulletinVO bulletinVO)throws AppException;

}
