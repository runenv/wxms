package com.ecp888.wxms.utils.exception;

import java.io.Serializable;

public class ExceptionContext implements Serializable {

	private static final long serialVersionUID = 20140722L;
	private String errorId;
	private String description;
	private Object associatedObject;
	
	public ExceptionContext(String errorId) {
		super();
		this.errorId = errorId;
		this.description = ExceptionDecMapper.getInstance().getErrorDesc(this.errorId);
	}
	
	public ExceptionContext(String errorId, Serializable associatedObject) {
		super();
		this.errorId = errorId;
		this.associatedObject = associatedObject;
		this.description = ExceptionDecMapper.getInstance().getErrorDesc(this.errorId);
	}
	
	public String getErrorId() {
		return errorId;
	}
	
		
	public String getDescription() {
	    return this.description;
	}
	
		
	public Serializable getAssociatedObject() {
		return (Serializable) associatedObject;
	}
	
}
