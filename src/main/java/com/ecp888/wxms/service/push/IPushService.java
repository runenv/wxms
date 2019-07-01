package com.ecp888.wxms.service.push;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.push.PushRegisterVO;
import com.ecp888.wxms.vo.push.PushStatisticVO;
import com.ecp888.wxms.vo.push.PushVO;

/**
 * APP Push信息服务类
 * @author ecp875
 * @since 2014-08-24
 */
@Consumes("application/json")
@Produces({"application/json","application/xml"})
@Path("/push")
public interface IPushService {
	/**
	 * 查询列表APP Push信息
	 * @author ecp875
	 * @since 2014-08-24
	 * @param pushVO
	 * @param pageVO
	 * @return
	 * @throws AppException
	 */
	List<PushVO> listPush(PushVO pushVO,PageVO pageVO)throws AppException;
	/**
	 * 排序APP Push信息
	 * @param pushVO
	 * @throws AppException
	 */
	List<PushVO> orderPush(PushVO pushVO,PageVO pageVO)throws AppException;
	
	/**
	 * 查询APP Push列表
	 * @author ecp875
	 * @since 2014-09-12
	 * @param pushVO
	 * @return
	 * @throws AppException
	 */
	@GET
	@Path("/listPush")
	List<PushVO> listPush(@PathParam("pushId")PushVO pushVO)throws AppException;
	
	/**
	 * 查询单个APP Push信息
	 * @author ecp875
	 * @since 2014-08-24
	 * @param pushVO
	 * @return
	 * @throws AppException
	 */
	@GET
	@Path("/getPush/{pushId}")
	PushVO getPush(@PathParam("pushId")PushVO pushVO)throws AppException;
	
	public PushVO getPushByTitle(PushVO pushVO) throws AppException;
	
	/**
	 * 保存APP Push信息
	 * @author ecp875
	 * @since 2014-08-24
	 * @param pushVO
	 * @throws AppException
	 */
	void savePush(PushVO pushVO)throws AppException;
	/**
	 * 删除APP Push信息
	 * @author ecp875
	 * @since 2014-08-24
	 * @param pushVO
	 * @throws AppException
	 */
	void deletePush(PushVO pushVO)throws AppException;
	/**
	 * 发布APP Push信息
	 * @author ecp875
	 * @since 2014-08-24
	 * @param pushVO
	 * @throws AppException
	 */
	void releasePush(PushVO pushVO)throws AppException;
	/**
	 * 根据平台号、渠道号查询APP Push列表
	 * @author ecp875
	 * @since 2014-08-24
	 * @param pushVO
	 * @return
	 * @throws AppException
	 */
	List<PushVO> listPushApp(PushVO pushVO)throws AppException;
	
	/**
	 * 查询APP Push数据统计列表
	 * @author ecp875
	 * @since 2014-09-10
	 * @param pushStatisticVO
	 * @return
	 * @throws AppException
	 */
	List<PushStatisticVO> statisticPush(PushStatisticVO pushStatisticVO)throws AppException;
	
	/**
	 * 统计Push用户登陆次数
	 * @author ecp875
	 * @since 2014-09-10
	 * @param paramVO
	 * @throws AppException
	 */
	void userCountRate(PushStatisticVO paramVO)throws AppException;
	
	/**
	 * Push目标用户关联设备
	 * @author ecp875
	 * @since 2014-09-10
	 * @param pushRegisterVO
	 * @return
	 * @throws AppException
	 */
	void registerTargetUser(PushRegisterVO pushRegisterVO)throws AppException;

}
