package com.ecp888.wxms.vo.user;

import java.util.ArrayList;
import java.util.List;

public class ResourceDefinitionVO {

	private String resourceCode;
	private String resourceDesc;
	private String resourceType;
	private List<OperationDefinitionVO> operationDefList=
			new ArrayList<OperationDefinitionVO>();
	
	public List<OperationDefinitionVO> getOperationDefList() {
		return operationDefList;
	}
	public void setOperationDefList(List<OperationDefinitionVO> operationDefList) {
		this.operationDefList = operationDefList;
	}
	public String getResourceCode() {
		return resourceCode;
	}
	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}
	public String getResourceDesc() {
		return resourceDesc;
	}
	public void setResourceDesc(String resourceDesc) {
		this.resourceDesc = resourceDesc;
	}
	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	
	public OperationDefinitionVO checkOperation(
			OperationDefinitionVO operationDefVO){
		if(this.operationDefList.contains(operationDefVO)){
			for(OperationDefinitionVO opVO:this.operationDefList){
				if(opVO.equals(operationDefVO)){
					return opVO;
				}
			}
		}
		return null;
	}
	
	public void addOperation(OperationDefinitionVO operationDefVO){
		if(!this.operationDefList.contains(operationDefVO)){
			operationDefList.add(operationDefVO);
		}
	}
}
