package com.ecp888.wxms.utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import com.ecp888.wxms.utils.json.JsonObjectMapper;
import com.ecp888.wxms.vo.user.UserVO;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AppUtil {
	
	protected static ObjectMapper objectMapper = new JsonObjectMapper();
	
	public static boolean isNotEmpty(String str) {
		if(null==str)return false;
		if("".equals(str)) return false;
		return true;
	}
	
	public static String getHostAddress(){
		String hostAddress="";
		InetAddress addr=null;
		try {
			addr = InetAddress.getLocalHost();
			hostAddress=addr.getHostAddress();//获得本机IP　
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return hostAddress;
	}

	public static String getHostName(){
		String hostName="";
		InetAddress addr=null;
		try {
			addr = InetAddress.getLocalHost();
			hostName=addr.getHostName();//获得本机名称
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return hostName;
	}
	
	public static UserVO getCurrentUser(){
		Object object = LocalSession.getInstance().getAttribute("userSession");
		return null == object ? new UserVO() : (UserVO)object;
	}
	
	public static void setCurrentUser(Object object){
		LocalSession.getInstance().setAttribute("userSession",object);
	}
	
	public static String[] tokenizeToStringArray(String str,String delimiters){
		return tokenizeToStringArray(str,delimiters,true,true);
	}

	private static String[] tokenizeToStringArray(String str,
			String delimiters, boolean trimTokens, boolean ignoreEmptyTokens) {
		if(null==str)return null;
		StringTokenizer st=new StringTokenizer(str,delimiters);
		List<String> tokens=new ArrayList<String>();
		while(st.hasMoreTokens()){
			processOneToken(st.nextToken(),trimTokens,ignoreEmptyTokens,tokens);
		}
		return toStringArray(tokens);
	}

	private static String[] toStringArray(Collection<String> collection) {
		if(null==collection)return null;
		return collection.toArray(new String[collection.size()]);
	}

	private static void processOneToken(String stringToken, boolean trimTokens,
			boolean ignoreEmptyTokens, List<String> tokens) {
		if(trimTokens) stringToken=stringToken.trim();
		if(ignoreEmptyTokens || stringToken.length()>0){
			tokens.add(stringToken);
		}
	}
	
	/**
	 * 与当前日期期比较
	 * @param dateStr
	 * @param fmt
	 * @return 0：相等，1：大于当前日期，-1小于当前日期
	 */
	public static int comparetoToday(String dateStr,String fmt){ 
		int flag = 0;
		try {
			Date today = new Date();
			DateFormat df = new SimpleDateFormat(fmt);
			Date date = df.parse(dateStr);
			if(date.getTime() > today.getTime()){
				flag = 1;
			}else if(date.getTime() < today.getTime()){
				flag = -1;
			}else if(date.getTime() == today.getTime()){
				flag = 0;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public static Date getDate(String dateStr,String fmt){
		Date date = null;
		if(null == dateStr) return date;
		try {
			DateFormat df = new SimpleDateFormat(fmt);
			date = df.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	private static boolean isNumber(String str){
		String patternStr = "([-\\+]?[0-9]([0-9]*)(\\.[0-9]+)?)|(^0$)";
        Pattern pattern=Pattern.compile(patternStr);
        Matcher match=pattern.matcher(str);
        if(match.matches()){
             return true;
        }else{
             return false;
        }
    }
	
	public static String getMapValue(Map<String, String> map,String key){
		if(null == map || null == map.get(key)) return "";
		return map.get(key).toString();
	}
	
	public static String getMapNumberValue(Map<String, String> map,String key){
		if(null == map || null == map.get(key)) return "0";
		String returnValue = map.get(key).toString();
		if("null".equals(returnValue.toLowerCase())) return "0";
		if(!isNumber(returnValue)) return "0";
		return map.get(key).toString();
	}
	
	public static String object2Json(Object obj) throws IOException {
        return new String(objectMapper.writeValueAsBytes(obj), "UTF-8");
    }
    
	public static <T> T string2Object(String jsonStr, Class<T> clazz) throws IOException {
    	if(null == jsonStr) return null;
        return objectMapper.readValue(jsonStr, clazz);
    }
	
	/**
	 * 从json HASH表达式中获取一个map，改map支持嵌套功能
	 * @param jsonString
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map jsonToMap(String jsonString) {

		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		Iterator keyIter = jsonObject.keys();
		String key;
		Object value;
		Map valueMap = new HashMap();
		while (keyIter.hasNext()) {
			key = (String) keyIter.next();
			value = jsonObject.get(key).toString();
			valueMap.put(key, value);
		}
		return valueMap;
	}
	
}
