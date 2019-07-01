package com.ecp888.wxms.utils.exception;

import java.io.Serializable;

public class DatabaseException extends AppException {

	private static final long serialVersionUID = 1L;

	public DatabaseException() {
		super();
	}
	
	public DatabaseException(String errorId) {
		super(new ExceptionContext(errorId));
	}
	
	public DatabaseException(String errorId, Serializable associatedObject) {
		super(new ExceptionContext(errorId, associatedObject));
	}

	public DatabaseException(String errorId, Serializable associatedObject,
			Throwable cause) {
		super(new ExceptionContext(errorId, associatedObject), cause);
	}

	@Override
	public String toString() {
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append(super.toString()).append("<br>");
		strBuffer.append("DatabaseException [").append(getExceptionContext()).append("]").append("<br>");
		StackTraceElement[] stacks = super.getStackTrace();
		if(null==stacks) return strBuffer.toString();
		for(StackTraceElement stack : stacks){
			strBuffer.append(stack).append("<br>");
		}
		return strBuffer.toString();
	}
	

}
