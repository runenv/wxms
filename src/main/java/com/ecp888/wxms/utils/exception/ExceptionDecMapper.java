package com.ecp888.wxms.utils.exception;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExceptionDecMapper {
	
	private static ExceptionDecMapper excDecMapper;
	
	private final Pattern errorIdPattern = Pattern
			.compile("[DFS]_([A-Z0-9]{3,10}_([0-9]{4,5}))");
	
	private ResourceBundle errorDescBundle;
	
    public static synchronized ExceptionDecMapper getInstance(){
    	if(null == excDecMapper){
    		excDecMapper = new ExceptionDecMapper();
    		excDecMapper.init();
    	}
    	return excDecMapper;
    }

	private void init() {
		errorDescBundle = ResourceBundle.getBundle("exceptionDesc");		
	}
	
	private boolean isCommonError(String errorNum){
		if(null==errorNum || "".equals(errorNum))return false;
		/**
		 * 例：F-USER-10001，F:异常类型，USER：模块，10001编号
		 * 大于10000为系统公共异常,直接取编号，本例中为10001
		 * 其他为模块-编号，本例中为USER-1000
		 */
		return (errorNum.charAt(0) == '0' || Integer.valueOf(errorNum)>10000)?true : false;
	}
	
	public String getErrorDesc(String errorId){
		Matcher matcher = errorIdPattern.matcher(errorId);
		if(matcher.find()){
			String errorNum = matcher.group(2);
			// if common Error : only use errornumer else with comp name
			String key = isCommonError(errorNum)?errorNum:matcher.group(1);		
			try{
				return errorDescBundle.getString(key);
			}catch(MissingResourceException mre){
				return ExConstants.UNKNOW_ERROR;
			}
		}
		return ExConstants.UNKNOW_ERROR;
	}
	
	public static void main(String[] args) {
		ExceptionDecMapper dec = new ExceptionDecMapper();
		String errorId = "F_ATTACH_1001";
		Matcher matcher = dec.errorIdPattern.matcher(errorId);
		System.out.println(matcher.find());
	}
    
}
