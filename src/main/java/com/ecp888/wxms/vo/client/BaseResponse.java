package com.ecp888.wxms.vo.client;

/**
 * App响应封装类
 * @author ecp875
 * @since 2014-08-24
 */
public class BaseResponse {

	public static final String REQUEST_DATA_EXCEPTION = "-999";
    public static final String UNKNOW_EXCEPTION = "9999";

    private String statusCode = "0";
    private String errorMsg;
    
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
    
    
}
