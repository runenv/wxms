package com.ecp888.wxms.utils.exception;

public class CopyOfAppException extends Exception {

	private static final long serialVersionUID = 4580354247708120520L;

	private String errorCode ;
	private String message;
	private Throwable rootCause;

	public CopyOfAppException() {
		super();
	}

	public CopyOfAppException(String message) throws CopyOfAppException {
		super(message);
		this.message=message;
	}

	public CopyOfAppException(String errorCode, Throwable rootCause)
			throws CopyOfAppException {
		super(errorCode, rootCause);
	}
	
	public String getMessage() {
		return message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public Throwable getRootCause() {
		return rootCause;
	}

}
