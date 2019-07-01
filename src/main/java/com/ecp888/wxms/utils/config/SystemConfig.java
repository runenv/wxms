package com.ecp888.wxms.utils.config;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 系统配置表
 * @author wuxibo
 * 2014/09/01
 */
@SuppressWarnings("rawtypes")
public class SystemConfig {
	protected static Log logger = LogFactory.getLog(SystemConfig.class);
	private static Properties mConfig;
	
	public static final String APP_ENVIRONMENT_DEV = "dev";
    public static final String APP_ENVIRONMENT_SIT = "sit";
    public static final String APP_ENVIRONMENT_UAT = "uat";
    public static final String APP_ENVIRONMENT_PRODUCT = "product";
	
	public static String SYSTEM_CONFIG_PATH_DEV = "/system-config-dev.properties";
    public static String SYSTEM_CONFIG_PATH_SIT = "/system-config-sit.properties";
    public static String SYSTEM_CONFIG_PATH_UAT = "/system-config-uat.properties";
    public static String SYSTEM_CONFIG_PATH_PRODUCT = "/system-config-product.properties";
	
	//静态代码第一次加载类的时候 读取配置文件
	static {
		mConfig = new Properties();
		try {
			ResourceBundle systemBundle = ResourceBundle.getBundle("system-config");
			String systemConfigPath = SYSTEM_CONFIG_PATH_DEV;
			if(APP_ENVIRONMENT_SIT.equals(systemBundle.getString("app_environment"))){
				systemConfigPath = SYSTEM_CONFIG_PATH_SIT;
			}else if(APP_ENVIRONMENT_UAT.equals(systemBundle.getString("app_environment"))){
				systemConfigPath = SYSTEM_CONFIG_PATH_UAT;
			}else if(APP_ENVIRONMENT_PRODUCT.equals(systemBundle.getString("app_environment"))){
				systemConfigPath = SYSTEM_CONFIG_PATH_PRODUCT;
			}
			// 通过类获取属性文件输入流
			InputStream is = SystemConfig.class.getResourceAsStream(systemConfigPath);
			mConfig.load(is);
			logger.info("successfully loaded default properties.");

			String key = null;
			Enumeration keys = mConfig.keys();
			while (keys.hasMoreElements()) {
				key = (String) keys.nextElement();
				logger.info(key + "=" + mConfig.getProperty(key));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取单String类型的配置属性值
	 */
	public static String getProperty(String key) {
		return mConfig.getProperty(key);
	}
	/**
	 * 获取单String类型的配置属性值,找不到采用默认值
	 */
	public static String getProperty(String key, String defaultValue) {
		String value = SystemConfig.getProperty(key);

		if (value == null) {
			return defaultValue;
		}
		return value;
	}

	/**
	 * 获取单个布尔类型的配置属性值
	 */
	public static boolean getBooleanProperty(String name) {
		return getBooleanProperty(name, false);
	}

	/**
	 *获取单个布尔类型的配置属性值 找不到就采用默认值
	 */
	public static boolean getBooleanProperty(String name, boolean defaultValue) {
		String value = SystemConfig.getProperty(name);

		if (value == null) {
			return defaultValue;
		}
		return (new Boolean(value)).booleanValue();
	}

	/**
	 * 获取整数类型的配置属性值
	 */
	public static int getIntProperty(String name) {
		return getIntProperty(name, 0);
	}

	/**
	 * 获取单个整型的配置属性值 找不到就采用默认值
	 */
	public static int getIntProperty(String name, int defaultValue) {
		// get the value first, then convert
		String value = SystemConfig.getProperty(name);

		if (value == null) {
			return defaultValue;
		}
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	/**
	 * 获取单个整型数组的配置属性值 
	 */
	public static int[] getIntPropertyArray(String name, int[] defaultValue) {
		String value = SystemConfig.getProperty(name);

		if (value == null) {
			return defaultValue;
		}
		try {
			String[] propertyArray = value.split(",");// 将字符用逗开分离
			int[] result = new int[propertyArray.length];
			for (int i = 0; i < propertyArray.length; i++) {//
				result[i] = Integer.parseInt(propertyArray[i]);
			}
			return result;
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	/**
	 * 获取布尔数组的配置属性值 
	 */
	public static boolean[] getBooleanPropertyArray(String name,
			boolean[] defaultValue) {
		String value = SystemConfig.getProperty(name);
		if (value == null) {
			return defaultValue;
		}
		try {
			String[] propertyArray = value.split(",");// 将字符用逗开分离
			boolean[] result = new boolean[propertyArray.length];
			for (int i = 0; i < propertyArray.length; i++) {//
				result[i] = (new Boolean(propertyArray[i])).booleanValue();
			}
			return result;
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	/**
	 * 获取String类型数组的配置属性值 
	 */
	public static String[] getPropertyArray(String name, String[] defaultValue) {
		// get the value first, then convert
		String value = SystemConfig.getProperty(name);
		if (value == null) {
			return defaultValue;
		}
		try {
			String[] propertyArray = value.split(",");// 将字符用逗开分离
			return propertyArray;
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	/**
	 * 所有配置属性键名称列表
	 */
	public static Enumeration keys() {
		return mConfig.keys();
	}
}
