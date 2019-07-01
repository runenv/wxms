package com.ecp888.wxms.service.impl.user;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Service;

import com.ecp888.wxms.utils.AppUtil;
import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.utils.interfaces.AnnotationMetadataDelegate;
import com.ecp888.wxms.utils.interfaces.AnnotationMetadataScanner;
import com.ecp888.wxms.utils.interfaces.AppOperate;
import com.ecp888.wxms.utils.interfaces.AppResource;
import com.ecp888.wxms.utils.interfaces.MethodMetadataDelegate;
import com.ecp888.wxms.dao.user.IRoleRightDAO;
import com.ecp888.wxms.service.user.IRoleRightService;
import com.ecp888.wxms.vo.BaseVO;
import com.ecp888.wxms.vo.user.OperationDefinitionVO;
import com.ecp888.wxms.vo.user.ResourceDefinitionVO;
import com.ecp888.wxms.vo.user.RoleRightVO;
import com.ecp888.wxms.vo.user.RoleVO;
import com.ecp888.wxms.vo.user.UserRoleProgramVO;
import com.ecp888.wxms.vo.user.UserVO;

@Service("roleRightService")
public class RoleRightService implements IRoleRightService {
	@Autowired
	IRoleRightDAO roleRightDAO;
	
	public List<RoleRightVO> listRoleRight(Integer roleId) throws AppException {
		return roleRightDAO.listRoleRight(roleId);
	}

	public List<RoleRightVO> listRoleRightByRoleId(Integer roleId)
			throws AppException {
		return roleRightDAO.listRoleRightByRoleId(roleId);
	}

	public boolean hasRoleRight(Map<String, String> map) throws AppException {
		boolean bool=false;
		UserVO currentUser=AppUtil.getCurrentUser();
		UserRoleProgramVO userRoleProgramVO =currentUser.getUserRoleProgramVO();
		if(null==userRoleProgramVO)return bool;
		RoleVO roleVO=userRoleProgramVO.getRoleVO();
		if(null==roleVO)return bool;
		//从数据库中获取(实时性)
		/*List<RoleRightVO> roleRightList= this.listRoleRightByRoleId(currentUser.getCurrentRoleId());
		RoleRightVO roleRightVO=roleRightDAO.getRoleRight(map);	*/ 
		//从会话中获取角色权限列表(提高性能)
		List<RoleRightVO> roleRightList = roleVO.getRoleRightList(); 
		List<RoleRightVO> allRightList = roleVO.getAllRightList();
		RoleRightVO roleRightVO = null;
		
		if(null != allRightList && !allRightList.isEmpty()){
			for (RoleRightVO allRightVO : allRightList) {
				if(map.get("appResource").equals(allRightVO.getRightValue())
					&& map.get("appOperate").equals(allRightVO.getRightItemValue())){
					roleRightVO = allRightVO;
					break;
				}
			}
		}
		if(null==roleRightVO)return bool;
		if(null!=roleRightList && !roleRightList.isEmpty()){
			for(RoleRightVO rrVO:roleRightList){
				if(roleRightVO.getRightId()==rrVO.getRightId()){
					bool=true;
					break;
				}
			}
		}
		return bool;
	}
	
	public boolean hasRoleRight(String rightPoint) throws AppException {
		if(!AppUtil.isNotEmpty(rightPoint))return false;
		String[] point=rightPoint.split("\\$");
		Map<String, String> map=new HashMap<String, String>();
		map.put("appResource", null == point[0] ? "" : point[0]);
		map.put("appOperate", null == point[1] ? "" : point[1]);
		return this.hasRoleRight(map);
	}
	
	public void addRightResource(
			OperationDefinitionVO operationDef)throws AppException{
		 ResourceDefinitionVO  resourceVO=operationDef.getResource();
		 if(null==resourceVO)return;
		RoleRightVO rightVO=new RoleRightVO();
		rightVO.setRightName(resourceVO.getResourceDesc());
		rightVO.setRightValue(resourceVO.getResourceCode());
		rightVO.setRightItemName(operationDef.getOperationDesc());
		rightVO.setRightItemValue(operationDef.getOperationCode());
		rightVO.setResourceType(resourceVO.getResourceType());
		roleRightDAO.addRight(rightVO);
	}
	
	public void synchAppResource()throws AppException{
		
		Map<String,ResourceDefinitionVO> resourceDefs=
				new HashMap<String,ResourceDefinitionVO>();
		Set<AnnotationMetadataDelegate> annotatedServices=
				findAnnotationMetadata(AppResource.class);
		if(null==annotatedServices)return;
		roleRightDAO.deleteRight();
		for(AnnotationMetadataDelegate annotationMetadata:annotatedServices){
			wrapResourceDefinition(annotationMetadata,resourceDefs);
		}
	}

	private Set<AnnotationMetadataDelegate> findAnnotationMetadata(
			Class<? extends Annotation> annotation) {
		String basePackage="* com.ant.it.service.*.*(..)";
		AnnotationMetadataScanner scanner=new AnnotationMetadataScanner();
		String[] basePackages=AppUtil.tokenizeToStringArray(basePackage, ",; \t\n");
		Set<AnnotationMetadataDelegate> annotatedServices=scanner.scan(basePackages, annotation);
		return annotatedServices;
	}
	
	private void wrapResourceDefinition(
			AnnotationMetadataDelegate appResource, 
			Map<String,ResourceDefinitionVO> resourceDefs)throws AppException {
		Map<String, Object> annotationAttrs=appResource.getAnnotationAttributes();
		String code=(String) annotationAttrs.get("code");
		String desc=(String) annotationAttrs.get("desc");
		if(null==code)return;
		ResourceDefinitionVO resourceDef=null;
		if(resourceDefs.containsKey(code)){
			resourceDef=(ResourceDefinitionVO) resourceDefs.get(code);
		}else{
			resourceDef=new ResourceDefinitionVO();
			resourceDef.setResourceCode(code);
			resourceDef.setResourceDesc(desc);
			resourceDef.setResourceType("service");
			resourceDefs.put(code, resourceDef);
		}
		wrapOperationDefinitions(appResource,resourceDef);
		
	}

	private void wrapOperationDefinitions(
			AnnotationMetadataDelegate appResource,
			ResourceDefinitionVO resourceDef) throws AppException {
		Set<MethodMetadataDelegate> permissionOperations=
				appResource.getAnnotatedMethods(AppOperate.class.getName());
		for(MethodMetadataDelegate permissionOperation:permissionOperations){
			OperationDefinitionVO operationDef=
					wrapOperationDefinition(permissionOperation);
			if(null!=operationDef){
				operationDef.setResource(resourceDef);
				OperationDefinitionVO tempVO=null;
				tempVO=resourceDef.checkOperation(operationDef);
				if(null==tempVO){
					resourceDef.addOperation(operationDef);
				}else{
					List urlList=new ArrayList();
					urlList.addAll(Arrays.asList(tempVO.getOperationUrl()));
					urlList.addAll(Arrays.asList(operationDef.getOperationUrl()));
					tempVO.setOperationUrl((String[]) urlList.toArray(new String[urlList.size()]));
				}
				//添加权限资源到t_app_right
				addRightResource(operationDef);
			}
		}
	}

	private OperationDefinitionVO wrapOperationDefinition(
			MethodMetadataDelegate appOperation) {
		OperationDefinitionVO operationDef=new OperationDefinitionVO();
		Map operationAttrs=appOperation.getAnnotationAttributes(AppOperate.class.getName());
		String code=(String) operationAttrs.get("code");
		String desc=(String) operationAttrs.get("desc");
		if(null!=code)code=code.trim();
		if(!AppUtil.isNotEmpty(code))return null;
		operationDef.setOperationCode(code);
		operationDef.setOperationDesc(desc);
		return operationDef;
	}


}
