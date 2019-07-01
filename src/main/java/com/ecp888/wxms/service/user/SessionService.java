package com.ecp888.wxms.service.user;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.ecp888.wxms.vo.user.UserVO;

public class SessionService {

	private static SessionService instance = null;

	//Map<String,UserVO> session = new HashMap<String,UserVO>();
	//保存变量的ThreadLocal，保持在同一线程中同步数据
	private static final ThreadLocal<Map<String,Object>> SESSION_MAP = new ThreadLocal<Map<String,Object>>();

	public static synchronized SessionService getInstance() {
		if (instance == null) {
			instance = new SessionService();
		}
		return instance;
	}

	private SessionService() {
	}
	
	public Object get(String attribute) {
        Map<String,Object> map = SESSION_MAP.get();
        System.out.println(map.containsKey("userSession"));
        return map.get(attribute);
    }
	
	public <T> T get(String attribute, Class<T> clazz) {
        return (T) get(attribute);
    }
	
	public void set(String attribute, Object value) {
        Map<String, Object> map = SESSION_MAP.get();
        if (map == null) {
            map = new HashMap<String, Object>();
            SESSION_MAP.set(map);
        }
        map.put(attribute, value);
    }
	
	
	
	
	

	public Map getSession(String id) {
		/*UserVO userVO = session.get(id);
		if (session == null) {
			session = new HashMap();
			//mc.add(id, session);
		}
		return session;*/
		
		return null;
	}

	public void saveSession(String id, Map session) {
		
	}

	public void removeSession(String id) {

	}


	

}
