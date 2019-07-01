package com.ecp888.wxms.service.log;

import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.vo.log.LogVO;

/**
 * 系统日志服务类
 * @author ecp875
 * @since 2014-07-07
 */
public interface ILogService {

	void saveLog(LogVO logVO)throws AppException ;
}
