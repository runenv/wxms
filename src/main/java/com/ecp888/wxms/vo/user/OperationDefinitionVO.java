package com.ecp888.wxms.vo.user;

public class OperationDefinitionVO {

	private int operationId;
	private String operationCode;
	private String operationDesc;
	private String[] operationUrl;
	public String[] getOperationUrl() {
		return operationUrl;
	}
	public void setOperationUrl(String[] operationUrl) {
		this.operationUrl = operationUrl;
	}
	private ResourceDefinitionVO Resource=new ResourceDefinitionVO();
	public ResourceDefinitionVO getResource() {
		return Resource;
	}
	public void setResource(ResourceDefinitionVO resource) {
		Resource = resource;
	}
	public int getOperationId() {
		return operationId;
	}
	public void setOperationId(int operationId) {
		this.operationId = operationId;
	}
	public String getOperationCode() {
		return operationCode;
	}
	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}
	public String getOperationDesc() {
		return operationDesc;
	}
	public void setOperationDesc(String operationDesc) {
		this.operationDesc = operationDesc;
	}
}
