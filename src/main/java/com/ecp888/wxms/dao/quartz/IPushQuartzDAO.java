package com.ecp888.wxms.dao.quartz;

import java.util.List;

import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.vo.push.PushVO;

public interface IPushQuartzDAO {

	List<PushVO> getPushList() throws AppException;
	
	List<String> getTarget(PushVO pushVO) throws AppException;
	
	List<String> getVersion(PushVO pushVO) throws AppException;
	
	List<String> getChannel(PushVO pushVO) throws AppException;
	
	List<String> getPlatform(PushVO pushVO) throws AppException;
	
}
