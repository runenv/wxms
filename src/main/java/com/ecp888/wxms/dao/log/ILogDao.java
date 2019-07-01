package com.ecp888.wxms.dao.log;

import java.util.List;

import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.vo.log.LogVO;

public interface ILogDao {

	public int countLog(LogVO logVO)throws AppException;
	
	public List<LogVO> listLog(LogVO logVO)throws AppException;
	
	public void addLog(LogVO logVO)throws AppException;
	
	public void updateLog(LogVO logVO)throws AppException;
	
	public void deleteLog(LogVO logVO)throws AppException;
}
