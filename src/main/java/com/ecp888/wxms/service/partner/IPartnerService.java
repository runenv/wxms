package com.ecp888.wxms.service.partner;

import java.util.ArrayList;
import java.util.Map;

import com.ecp888.passport.model.MemberPartner;
import com.ecp888.wxms.utils.exception.AppException;
/**
 * 渠道服务接口
 * @author ecp875
 * @since 2014-08-27
 */
public interface IPartnerService {
	
	/**
	 * 查询所有的父渠道包
	 * @author ecp875
	 * @since 2014-08-27
	 * @return
	 * @throws AppException
	 */
	public Map<String, ArrayList<MemberPartner>> findAllGroupByMobiletype()throws AppException;

	/**
	 * 查询App实际的渠道号
	 * @author ecp875
	 * @since 2014-08-27
	 * @param subChannelNo
	 * @return
	 */
	public String getParentChannelNo(String subChannelNo)throws AppException;
	
	
}
