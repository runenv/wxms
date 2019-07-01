package com.ecp888.wxms.utils.exception;

import java.io.Serializable;

public class FunctionException extends AppException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	public FunctionException() {
		
	}
	
	public FunctionException(String errorId) {
		super(new ExceptionContext(errorId));
	}
	
	public FunctionException(String errorId,Serializable associatedObject) {
		super(new ExceptionContext(errorId, associatedObject));
	}

	public FunctionException( String errorId, Serializable associatedObject, Throwable cause) {
		super(new ExceptionContext(errorId, associatedObject), cause);
	}

	@Override
	public String toString() {
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append(super.toString()).append("<br>");
		strBuffer.append("FunctionException [").append(getExceptionContext()).append("]").append("<br>");
		StackTraceElement[] stacks = super.getStackTrace();
		if(null==stacks) return strBuffer.toString();
		for(StackTraceElement stack : stacks){
			strBuffer.append(stack).append("<br>");
		}
		return strBuffer.toString();		
	}

		
    
}
