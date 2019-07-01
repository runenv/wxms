package com.ecp888.wxms.utils.interfaces;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)     
@Retention(RetentionPolicy.RUNTIME)
public @interface AppResource {
	
	public String code() default ""; 
	public String desc() default ""; 
}
