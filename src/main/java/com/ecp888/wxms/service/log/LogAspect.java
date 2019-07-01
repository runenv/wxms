package com.ecp888.wxms.service.log;

import java.lang.reflect.Method;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import com.ecp888.wxms.utils.AppUtil;
import com.ecp888.wxms.utils.interfaces.Audit;
import com.ecp888.wxms.vo.log.LogVO;
import com.ecp888.wxms.vo.user.UserVO;
/**
 * 系统日志拦截类
 * @author ecp875
 * @since 2014-07-07
 */
public class LogAspect {

	private Log logger = LogFactory.getLog(LogAspect.class);
	@Autowired
	private ILogService logService;
	
	@SuppressWarnings("rawtypes")
	public Object recordLog(ProceedingJoinPoint point) throws Throwable {
		
		String methodName = point.getSignature().getName();
		// 目标方法不为空  
		if (!AppUtil.isNotEmpty(methodName)) {
			return point.proceed();
		}else if (methodName.startsWith("set") || methodName.startsWith("get")) {
			return point.proceed();
		}else{
			Object[] param = point.getArgs(); 
			Class<? extends Object> targetClass = point.getTarget().getClass();  
			Class[] clazz = new Class[param.length];
			for(int i=0;i<param.length;i++){
				clazz[i] = param[i].getClass();
			}
			Method method = targetClass.getMethod(methodName,clazz);
			if (method != null) {
				boolean hasAnnotation = method.isAnnotationPresent(Audit.class);
				if (hasAnnotation) {
					Audit annotation =method.getAnnotation(Audit.class); 
					String methodDesc = annotation.description();
					if (logger.isDebugEnabled()){  
						logger.debug("Audit method:" + method.getName() + " Description:" + methodDesc);  
                    } 
					try{
						LogVO logVO=new LogVO();
						UserVO userVO=AppUtil.getCurrentUser();
						logVO.setUserIp(AppUtil.getHostAddress());
						logVO.setOperate(methodDesc);
						logVO.setCreateUser(userVO.getUserId());
						logVO.setUpdateUser(userVO.getUserId());
						logService.saveLog(logVO);
					}catch(Exception e){
						e.printStackTrace();
						logger.error(e.getMessage()); 
					}
				}
			}
		}
		return point.proceed();
	}
	
}
