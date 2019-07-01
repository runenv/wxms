package com.ecp888.wxms.utils.interfaces;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;

public class AnnotationMetadataDelegate {

	private String annotationType;
	private AnnotationMetadata annotationMetadata;
	public static final String CONTAIN_ANNOTATION_CLASS_NAME="containAnnotationClassName";
	
	public AnnotationMetadataDelegate(String annotationType,
			AnnotationMetadata annotationMetadata){
		assert(null!=annotationMetadata);
		this.annotationType=annotationType;
		this.annotationMetadata=annotationMetadata;
	}
	
	public Map<String, Object> getAnnotationAttributes(){
		Map attributesMap=this.annotationMetadata
				.getAnnotationAttributes(this.annotationType);
		this.annotationMetadata.getAnnotationAttributes("com.");
		attributesMap.put(CONTAIN_ANNOTATION_CLASS_NAME, 
				this.annotationMetadata.getClassName());
		return attributesMap;
	}
	
	public Set<MethodMetadataDelegate> getAnnotatedMethods(String annotationType){
		Set<MethodMetadataDelegate> annotatedMethodDelegates=
				new HashSet<MethodMetadataDelegate>();
		Set<MethodMetadata> annotatedMethods=
				this.annotationMetadata.getAnnotatedMethods(annotationType);
		for(MethodMetadata methodMetadata:annotatedMethods){
			annotatedMethodDelegates.add(
					new MethodMetadataDelegate(methodMetadata));
		}
		return annotatedMethodDelegates;
	}
	
}
