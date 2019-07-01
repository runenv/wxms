package com.ecp888.wxms.web.controller.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.support.XmlWebApplicationContext;


public class WebInit implements ServletContextListener {
	
	
	protected Log logger = LogFactory.getLog(getClass());
	private XmlWebApplicationContext xwac =  null;
	private ServletContext context;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		
		context = event.getServletContext();
		final String ctx = context.getContextPath(); 
		context.setAttribute("ctx", ctx); //统一上下文路径,页面调用${ctx}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
		final String version = dateFormat.format(new Date());
		context.setAttribute("version", version); //发布版本
		
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		if(xwac!=null){
			xwac.close();
		}
	}

}
