package com.ecp888.wxms.utils.interfaces;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.ClassUtils;
import org.springframework.util.SystemPropertyUtils;

public class AnnotationMetadataScanner {

	private Log logger=LogFactory.getLog(AnnotationMetadataScanner.class);
	
	private String resourcePattern="**/*.class";
	private ResourcePatternResolver resourcePatternResolver
		=new PathMatchingResourcePatternResolver();
	private MetadataReaderFactory metadataReaderFactory=
			new CachingMetadataReaderFactory(this.resourcePatternResolver);
	private final List<TypeFilter> includeFilters=
			new LinkedList<TypeFilter>();
	private final List<TypeFilter> excludeFilters=
			new LinkedList<TypeFilter>();
	
	public void addIncludeFilter(TypeFilter includeFilter){
		this.includeFilters.add(includeFilter);
	}
	
	public void addExcludeFilter(TypeFilter excludeFilter){
		this.excludeFilters.add(0,excludeFilter);
	}
	
	public Set<AnnotationMetadataDelegate> 
		scan(String[] basePackages,Class<? extends Annotation> annotation){
		addIncludeFilter(new AnnotationTypeFilter(annotation));
		Set annotatedResources=new LinkedHashSet();
		if(null==basePackages || basePackages.length==0){
			return annotatedResources;
		}
		for(String basePackage:basePackages){
			annotatedResources.addAll(findAnnotatedResources(basePackage,annotation.getName()));
		}
		return annotatedResources;
	}

	private Collection findAnnotatedResources
		(String basePackage, String annotationType) {
		Set annotatedResources=new LinkedHashSet();
		try{
			String packageSearchPath="classpath*:"+resolveBasePackage(basePackage)+"/"
					+this.resourcePattern;
			Resource[] resources=this.resourcePatternResolver.getResources(packageSearchPath);
			for(Resource resource:resources){
				AnnotationMetadataDelegate annotatedResource=
						findAnnotatedResource(resource, annotationType);
				if(null!=annotatedResource){
					annotatedResources.add(annotatedResource);
				}
			}
		}catch(IOException e){
			e.printStackTrace();
			this.logger.error(e);
		}
		return annotatedResources;
	}

	private AnnotationMetadataDelegate findAnnotatedResource(Resource resource,
			String annotationType) {
		if(resource.isReadable()){
			try {
				MetadataReader metadataReader=metadataReaderFactory.getMetadataReader(resource);
				if(isAnnotatedResource(metadataReader)){
					AnnotationMetadata annotationMetadata=
							metadataReader.getAnnotationMetadata();
					return new AnnotationMetadataDelegate(annotationType,annotationMetadata);
				}else{
					logger.warn("Ignored becuse not readable:"+resource);
				}
			} catch (IOException e) {
				e.printStackTrace();
				this.logger.error(e);
			}
		}
		return null;
	}

	private boolean isAnnotatedResource(MetadataReader metadataReader) 
		throws IOException{
		for(TypeFilter tf:this.excludeFilters){
			if(tf.match(metadataReader, this.metadataReaderFactory)){
				return false;
			}
		}
		for(TypeFilter tf:this.includeFilters){
			if(tf.match(metadataReader, this.metadataReaderFactory)){
				return true;
			}
		}
		return false;
	}
	
	protected String resolveBasePackage(String basePackage){
		return ClassUtils.convertClassNameToResourcePath(
				SystemPropertyUtils.resolvePlaceholders(basePackage));
	}
	
}
