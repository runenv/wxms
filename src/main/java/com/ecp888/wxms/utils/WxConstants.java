package com.ecp888.wxms.utils;

import com.ecp888.wxms.utils.config.SystemConfig;

/**
 * 系统常量类
 * @author wuxibo
 * 2014/09/01
 */
public class WxConstants{
	
	public static final String APP_ENVIRONMENT_DEV = "dev";
    public static final String APP_ENVIRONMENT_SIT = "sit";
    public static final String APP_ENVIRONMENT_UAT = "uat";
    public static final String APP_ENVIRONMENT_PRODUCT = "product";
    
    public static final int APP_RESOURCE_POINT = 999;
	
	//应用标识(AppKey)
    public static final String APP_KEY= SystemConfig.getProperty("app_key", "app_key");
    
    //API MasterSecret
    public static final String MASTER_SECRET= SystemConfig.getProperty("master_secret", "master_secret");
    
    public static final String APP_ENVIRONMENT = SystemConfig.getProperty("app_environment", "app_environment");
    
    
    
}
