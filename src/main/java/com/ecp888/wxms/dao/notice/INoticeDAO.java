package com.ecp888.wxms.dao.notice;

import java.util.List;
import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.notice.NoticeVO;
/**
 * APP广告位信息DAO
 * @author ecp875
 * @since 2014-08-02
 */
public interface INoticeDAO {
	/**
	 * 查询APP广告位信息总记录
	 * @author ecp875
	 * @since 2014-07-07
	 * @param noticeVO
	 * @return
	 * @throws AppException
	 */
	int countNotice(NoticeVO noticeVO)throws AppException;
	/**
	 * 查询APP广告位信息列表
	 * @author ecp875
	 * @since 2014-07-07
	 * @param noticeVO
	 * @param pageVO
	 * @return
	 * @throws AppException
	 */
	List<NoticeVO> listNotice(NoticeVO noticeVO, PageVO pageVO)throws AppException;
	/**
	 * 根据noticeId查询单个APP广告位信息
	 * @author ecp875
	 * @since 2014-07-07
	 * @param noticeVO
	 * @return
	 * @throws AppException
	 */
	NoticeVO getNoticeById(NoticeVO noticeVO)throws AppException;
	/**
	 * 根据广告位名称查询单个APP广告位信息
	 * @author ecp875
	 * @since 2014-07-07
	 * @param noticeVO
	 * @return
	 * @throws AppException
	 */
	NoticeVO getNoticeByName(NoticeVO noticeVO)throws AppException;
	/**
	 * 新增APP广告位信息
	 * @author ecp875
	 * @since 2014-07-07
	 * @param noticeVO
	 * @throws AppException
	 */
	void addNotice(NoticeVO noticeVO)throws AppException;
	/**
	 * 修改APP广告位信息
	 * @author ecp875
	 * @since 2014-07-07
	 * @param noticeVO
	 * @throws AppException
	 */
	void updateNotice(NoticeVO noticeVO)throws AppException;
	
	/**
	 * 删除APP广告位信息
	 * @author ecp875
	 * @since 2014-07-07
	 * @param noticeVO
	 * @throws AppException
	 */
	void deleteNotice(NoticeVO noticeVO)throws AppException;
	
	//广告位平台
	public int createNoticePlatform(NoticeVO noticeVO) throws AppException;
	
	public int deleteNoticePlatform(NoticeVO noticeVO) throws AppException;
	
	public List<String> findNoticePlatform(NoticeVO noticeVO) throws AppException;
	
	//广告位渠道
	public int createNoticeChannel(NoticeVO noticeVO) throws AppException;
	
	public int deleteNoticeChannel(NoticeVO noticeVO) throws AppException;
	
	public List<String> findNoticeChannel(NoticeVO noticeVO) throws AppException;
	
	public List<String> findTargetUser(NoticeVO noticeVO) throws AppException;
	
	public int addTargetUser(NoticeVO noticeVO) throws AppException;
	
	public int deleteTargetUser(NoticeVO noticeVO) throws AppException;
	
	List<NoticeVO> listNoticeApp(NoticeVO noticeVO) throws AppException;
	
}
