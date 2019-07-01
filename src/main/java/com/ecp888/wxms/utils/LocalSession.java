package com.ecp888.wxms.utils;

import java.util.HashMap;
import java.util.Map;

public class LocalSession {

	private static LocalSession instance = null;

	//保存变量的ThreadLocal，保持在同一线程中同步数据
	private static final ThreadLocal<Map<String,Object>> SESSION_MAP = new ThreadLocal<Map<String,Object>>();

	public static synchronized LocalSession getInstance() {
		if (instance == null) {
			instance = new LocalSession();
		}
		return instance;
	}

	private LocalSession() {
	}
	
	public Object getAttribute(String attribute) {
        Map<String,Object> map = SESSION_MAP.get();
        return null == map ? null : map.get(attribute);
    }
	
	public void setAttribute(String attribute, Object value) {
        Map<String, Object> map = SESSION_MAP.get();
        if (map == null) {
            map = new HashMap<String, Object>();
            SESSION_MAP.set(map);
        }
        map.put(attribute, value);
    }

}
