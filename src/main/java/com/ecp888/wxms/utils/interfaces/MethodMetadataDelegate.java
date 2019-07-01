package com.ecp888.wxms.utils.interfaces;

import java.util.Map;

import org.springframework.core.type.MethodMetadata;

public class MethodMetadataDelegate {

	public static final String CONTAIN_ANNOTATION_METHOD_NAME="containerAnnotationMethodName";
	
	private MethodMetadata methodMetadata;
	
	public MethodMetadataDelegate(MethodMetadata methodMetadata){
		this.methodMetadata=methodMetadata;
	}
	
	public Map<String,Object> getAnnotationAttributes(String annotationType){
		Map attributesMap=this.methodMetadata.getAnnotationAttributes(annotationType);
		if(null!=attributesMap){
			attributesMap.put(CONTAIN_ANNOTATION_METHOD_NAME, 
					this.methodMetadata.getMethodName());
		}
		return attributesMap;
	}
}
