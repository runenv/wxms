package com.ecp888.wxms.service.impl.partner;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.caucho.hessian.client.HessianConnectionException;
import com.caucho.hessian.client.HessianProxyFactory;
import com.caucho.hessian.client.HessianRuntimeException;
import com.ecp888.passport.model.MemberPartner;
import com.ecp888.passport.service.GjMemService;
import com.ecp888.wxms.service.partner.IPartnerService;
import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.utils.exception.ExConstants;
import com.ecp888.wxms.utils.exception.FunctionException;
import com.ecp888.wxms.vo.notice.NoticeVO;
/**
 * 渠道服务实现类
 * @author ecp875
 * @since 2014-08-27
 */
@Service("partnerService")
public class PartnerService implements IPartnerService{

	protected Log logger = LogFactory.getLog(getClass());
	
	/**
	 * 查询所有的父渠道包
	 * @author ecp875
	 * @since 2014-08-27
	 * @return
	 */
    public Map<String, ArrayList<MemberPartner>> findAllGroupByMobiletype() throws AppException{
        List<MemberPartner> allPartner = getPartners(null, null);
        Map<String, ArrayList<MemberPartner>> mapAll = new HashMap<String, ArrayList<MemberPartner>>();

        for(int i = 0; allPartner != null && i < allPartner.size(); i ++) {
        	MemberPartner partner = allPartner.get(i);
            if(partner.getMobiletype() == null) {
                partner.setMobiletype("其他");
            }
            String mobiletype = partner.getMobiletype().toUpperCase();
            List<MemberPartner> partners = mapAll.get(mobiletype);
            if(partners == null) {
                partners = new ArrayList<MemberPartner>();
            }
            partners.add(partner);
            mapAll.put(mobiletype, (ArrayList<MemberPartner>)partners);
        }
        return mapAll;
    }
    
    /**
     * 查询App实际的渠道号
	 * @author ecp875
	 * @since 2014-08-27
     * @param subChannelNo
     * @return
     */
    public String getParentChannelNo(String subChannelNo)throws AppException{
        List<MemberPartner> partners = getPartners(null, subChannelNo);
        if(null != partners && !partners.isEmpty()) {
        	MemberPartner partner = partners.get(0);
            return partner.getPartnerid();
        }
        return subChannelNo;
    }
	
    /**
	 * Hessian 请求渠道信息
	 * @param flag
	 * @param partnerSubId
	 * @return
	 */
	public List<MemberPartner> getPartners(String flag, String partnerSubId)throws AppException{
		List<MemberPartner> partnerList = null;
		HessianProxyFactory factory = new HessianProxyFactory();
		GjMemService gjMemService = null;
		try {
			gjMemService = (GjMemService) factory.create(GjMemService.class, NoticeVO.PARTNER_URL);
			List<MemberPartner> partners =  gjMemService.findAllClientPartner();
			if (flag == null && partnerSubId == null) {
				partnerList = partners;
			} else if (partnerSubId != null) {
				for (MemberPartner p : partners) {
					if (p.getPartnerid().equals(partnerSubId)) {
						if (partnerList == null) {
							partnerList = new ArrayList<MemberPartner>();
						}
						partnerList.add(p);
					}
				}
			}
		}catch(HessianConnectionException e){
			logger.error(e.getMessage(),e);
			//渠道接口hessian未启动或者连接超时
			throw new FunctionException(ExConstants.SYS_COMMON_10003, e);
		}catch(HessianRuntimeException e) {
			logger.error(e.getMessage(),e);
			//渠道接口hessian调用出错
			throw new FunctionException(ExConstants.SYS_COMMON_10004, e);
		} catch (MalformedURLException e) {
			logger.error(e.getMessage(),e);
			//URL协议、格式或者路径错误
			throw new FunctionException(ExConstants.SYS_COMMON_10005, e);
		}
		return partnerList;
	}
	
	public static void main(String[] args) {
		try {
			new PartnerService().findAllGroupByMobiletype();
		} catch (AppException e) {
			e.printStackTrace();
		}
	}
}
