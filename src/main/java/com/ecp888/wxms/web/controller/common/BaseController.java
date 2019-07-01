package com.ecp888.wxms.web.controller.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.utils.exception.ExConstants;
import com.ecp888.wxms.utils.exception.ExceptionContext;
import com.ecp888.wxms.utils.exception.ExceptionDecMapper;
import com.ecp888.wxms.utils.json.JsonObjectMapper;
import com.ecp888.wxms.vo.user.UserVO;
import com.fasterxml.jackson.databind.ObjectMapper;



@Controller
public abstract class BaseController {
	
	protected Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	protected HttpSession session;
	
	@Autowired
	protected HttpServletRequest request;
	
	@Autowired
	protected ServletContext context;
	
	
	
	/**
	 * 转换App端传入的渠道号
	 * @param channelNo
	 * @return
	 */
	public String convertChannelNo(String channelNo){
		
		if("ecp888".equals(channelNo)){
			channelNo = "ecp888_android";
		}else if("1004".equals(channelNo)){
			channelNo = "ecp888_2.2_1004";
		}else if("1005".equals(channelNo)){
			channelNo = "ecp888_2.2_1005";
		}
		return channelNo;
	}
	
	/**
	 * 转换App端传入的平台号
	 * "addZtc": "202,2",
	   "iosZtc": "203,3",
	   "addGj": "204,d",
	   "iosGj": "205,e",
	   "wap": "201,1",
	   "html5": "210"
	 * @param platform
	 * @return
	 */
	public String convertPlatform(String platform){
		
		if("202".equals(platform) || "2".equals(platform)){
			platform = "addZtc"; //安卓直通车
		}else if("203".equals(platform) || "3".equals(platform)){
			platform = "iosZtc"; //苹果直通车
		}else if("204".equals(platform) || "d".equals(platform)){
			platform = "addGj"; //安卓彩票管家
		}else if("205".equals(platform) || "e".equals(platform)){
			platform = "iosGj"; //苹果彩票管家
		}else if("201".equals(platform) || "1".equals(platform)){
			platform = "wap"; //wap
		}else if("210".equals(platform)){
			platform = "html5"; //客户端HTML5
		}
		return platform;
	}
	
    /**
     * 获取发单客户端IP 
     * @author ecp875
     * @create 2014-07-16
     * @description 
     * @return
     */
    public String getRemoteIP(){
    	if (request == null)
			return "";
    	logger.info("------开始获取用户IP-----");
		String ip = request.getHeader("X-Real-IP");
		logger.info("X-Real-IP="+ip);
		if (StringUtils.isBlank(ip)) {
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
				logger.info("request.getRemoteAddr()="+ip);
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Cdn-Src-Ip");
				logger.info("Cdn-Src-Ip="+ip);
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("X-Forwarded-For");
				logger.info("X-Forwarded-For="+ip);
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
				logger.info("Proxy-Client-IP="+ip);
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
				logger.info("WL-Proxy-Client-IP="+ip);
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_CLIENT_IP");
				logger.info("HTTP_CLIENT_IP="+ip);
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_X_FORWARDED_FOR");
				logger.info("HTTP_X_FORWARDED_FOR="+ip);
			}
		}
		logger.info("获取用户IP地址="+ip);
		if(ip!=null){
			return ip.split(",")[0].trim();
		}
		return ip;
    }
    
    /**
     * 获取用户会话信息
     * @author ecp875
     * @return userVO
     * @since 2014-08-07
     */
    public UserVO getUserInfo(){
    	UserVO userVO=null;
    	session.getId();
    	Object object = session.getAttribute("userSession");
    	if(null == object){
    		userVO = new UserVO();
    	}else{
    		userVO = (UserVO) object;
    	}
    	return userVO;
    }
    
    protected void writeToClient(String str,HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        String callback = request.getParameter("callback");
        StringBuffer sb = new StringBuffer();
        sb.append(str);
        // 处理JSONP跨域请求
        if(callback != null && !"".equals(callback.trim())) {
        	sb.insert(0,"try{ " + callback.trim() + "(");
        	sb.append("); }catch(e){}");
        }
        PrintWriter out = response.getWriter();
		out.write(sb.toString());
		out.flush();
		out.close();
    }
    
    @ExceptionHandler  
    public String exHandler(HttpServletRequest request, Exception ex) {
        
        // 根据不同错误转向不同页面
        if(ex instanceof AppException) {
        	ExceptionContext exContext = ((AppException) ex).getExceptionContext();
        	if(null!=exContext){
        		logger.error(exContext.getDescription(),ex);
            	request.setAttribute("exMsg", exContext.getDescription());
            	request.setAttribute("exCause", ex.toString());
        	}
        }else if(ex.getCause() instanceof SQLException) {
        	logger.error(ex.getMessage(),ex);
        	request.setAttribute("exMsg", "["+ExceptionDecMapper.getInstance()
        			.getErrorDesc(ExConstants.SYS_COMMON_10001)+"]");
        	request.setAttribute("exCause", getExCause(ex));
        }else if(ex instanceof Exception) {
        	logger.error(ex.getMessage(),ex);
        	request.setAttribute("exMsg", "["+ExceptionDecMapper.getInstance()
        			.getErrorDesc(ExConstants.SYS_COMMON_10002)+"]");
        	request.setAttribute("exCause", getExCause(ex));
        }
        return "app/error";  
    }
    
    public String getExCause(Exception ex) {
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append(ex.toString()).append("<br>");
		StackTraceElement[] stacks = ex.getStackTrace();
		if(null==stacks) return strBuffer.toString();
		for(StackTraceElement stack : stacks){
			strBuffer.append(stack).append("<br>");
		}
		return strBuffer.toString();		
	}
    
    
}