package com.ecp888.wxms.service.notice;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.notice.NoticeVO;

/**
 * APP广告位信息服务类
 * @author ecp875
 * @since 2014-08-02
 */
@Consumes("application/json")
@Produces({"application/json","application/xml"})
@Path("/notice")
public interface INoticeService {
	/**
	 * 查询列表APP广告位信息
	 * @author ecp875
	 * @since 2014-08-02
	 * @param noticeVO
	 * @param pageVO
	 * @return
	 * @throws AppException
	 */
	List<NoticeVO> listNotice(NoticeVO noticeVO,PageVO pageVO)throws AppException;
	/**
	 * 排序APP广告位信息
	 * @param noticeVO
	 * @throws AppException
	 */
	List<NoticeVO> orderNotice(NoticeVO noticeVO,PageVO pageVO)throws AppException;
	/**
	 * 查询单个APP广告位信息
	 * @author ecp875
	 * @since 2014-08-02
	 * @param noticeVO
	 * @return
	 * @throws AppException
	 */
	@GET
	@Path("/getNotice/{noticeId}")
	NoticeVO getNotice(@PathParam("noticeId")NoticeVO noticeVO)throws AppException;
	
	public NoticeVO getNoticeByTitle(NoticeVO noticeVO) throws AppException;
	
	/**
	 * 保存APP广告位信息
	 * @author ecp875
	 * @since 2014-08-02
	 * @param noticeVO
	 * @throws AppException
	 */
	void saveNotice(NoticeVO noticeVO)throws AppException;
	/**
	 * 删除APP广告位信息
	 * @author ecp875
	 * @since 2014-08-02
	 * @param noticeVO
	 * @throws AppException
	 */
	void deleteNotice(NoticeVO noticeVO)throws AppException;
	/**
	 * 发布APP广告位信息
	 * @author ecp875
	 * @since 2014-08-02
	 * @param noticeVO
	 * @throws AppException
	 */
	void releaseNotice(NoticeVO noticeVO)throws AppException;
	
	/**
	 * 根据平台号、渠道号查询APP广告位列表
	 * @author ecp875
	 * @since 2014-08-02
	 * @param noticeVO
	 * @return
	 * @throws AppException
	 */
	List<NoticeVO> listNoticeApp(NoticeVO noticeVO)throws AppException;

}
