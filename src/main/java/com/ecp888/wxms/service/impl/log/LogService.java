package com.ecp888.wxms.service.impl.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.dao.log.ILogDao;
import com.ecp888.wxms.service.log.ILogService;
import com.ecp888.wxms.vo.log.LogVO;

/**
 * 系统日志服务类
 * @author ecp875
 * @since 2014-07-07
 */
@Service("logService")
public class LogService implements ILogService{

	@Autowired
	private ILogDao logDao;
	
	public void saveLog(LogVO logVO) throws AppException {
		if(null==logVO)return;
		if(null!=logVO.getLogId()){
			logDao.updateLog(logVO);
		}else{
			logDao.addLog(logVO);
		}
	}

}
