package com.ecp888.wxms.utils.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.ecp888.wxms.utils.AppUtil;
import com.ecp888.wxms.vo.attachment.AttachmentVO;

public class LoginFilter implements Filter {

	public void destroy() {
		
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {

		arg0.setCharacterEncoding("UTF-8");
		arg1.setCharacterEncoding("UTF-8");
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;

		HttpSession session = request.getSession();
		Object object = session.getAttribute("userSession");
		// 获得用户请求的URI
		String url = request.getServletPath();
		String contextPath = request.getContextPath();
		
		if("".equals(AttachmentVO.REAL_PATH)){
			AttachmentVO.REAL_PATH = request.getSession().getServletContext()
					.getRealPath("/").replaceAll("\\\\", "/");
		}
		if("".equals(AttachmentVO.CONTEXT_PATH)){
			AttachmentVO.CONTEXT_PATH = contextPath;
		}
		
		if(url.equals("")) url+="/"; 
		// 登陆页面无需过滤
		if (url.indexOf("/loginUser.jsp") != -1 
				|| url.indexOf("/images/") != -1
				|| url.indexOf("/css/") != -1
				|| url.indexOf("/js/") != -1
				|| url.indexOf("/services/") != -1
				|| url.indexOf("/app/") != -1
				|| url.indexOf("/file/") != -1
				|| url.indexOf("/user/login") != -1
				|| url.indexOf("/user/validateLogin") != -1
				|| url.indexOf("/user/toLogin") != -1) {
			AppUtil.setCurrentUser(object);
			chain.doFilter(arg0, arg1);
			return;
		} else if(null == object){
			// 判断用户session是否过期或是否登录
			response.sendRedirect(contextPath+"/user/toLogin");      
			return;
		}
		AppUtil.setCurrentUser(object);
		chain.doFilter(arg0, arg1);
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}

	

}
