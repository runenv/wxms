package com.ecp888.wxms.service.impl.user;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecp888.wxms.utils.AppUtil;
import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.utils.exception.ExConstants;
import com.ecp888.wxms.utils.exception.FunctionException;
import com.ecp888.wxms.utils.interfaces.AppOperate;
import com.ecp888.wxms.utils.interfaces.AppResource;
import com.ecp888.wxms.service.user.IRoleRightService;
import com.ecp888.wxms.vo.user.UserVO;

public class RightAspect {
	private Log logger = LogFactory.getLog(RightAspect.class);
	@Autowired
	IRoleRightService roleRightService;
	
	@SuppressWarnings("unchecked")
	public Object roleRightPoint(ProceedingJoinPoint point) throws Throwable {
		
		String methodName = point.getSignature().getName();
		// 目标方法不为空  
		if (!AppUtil.isNotEmpty(methodName)) {
			return point.proceed();
		}else if (methodName.startsWith("set") || methodName.startsWith("get")) {
			return point.proceed();
		}else if (methodName.startsWith("saveRoleRight") 
				&& null!=AppUtil.getCurrentUser()
				&& AppUtil.getCurrentUser().getCurrentRoleId()==1) {
			return point.proceed();
		}else{
			Class targetClass = point.getTarget().getClass();  
			boolean hasAppResource=targetClass.isAnnotationPresent(AppResource.class);
			if(!hasAppResource)return point.proceed();
			AppResource appResource =(AppResource) targetClass.getAnnotation(AppResource.class); 

			MethodSignature methodSignature=(MethodSignature) point.getSignature();  
			Class<?>[] param = methodSignature.getParameterTypes();  
			Method method = targetClass.getMethod(methodName,param);
			
			if (method != null) {
				boolean hasAppOperate = method.isAnnotationPresent(AppOperate.class);
				if (!hasAppOperate) return point.proceed();
				AppOperate appOperate =method.getAnnotation(AppOperate.class); 
				Map<String, String> paramMap=new HashMap<String, String>();
				paramMap.put("appResource",appResource.code());
				paramMap.put("appOperate",appOperate.code());
				boolean rightFlag=roleRightService.hasRoleRight(paramMap);
				if(!rightFlag){
					throw new FunctionException(ExConstants.F_USER_1003);
				}
			}
		}
		return point.proceed();
	}
}
