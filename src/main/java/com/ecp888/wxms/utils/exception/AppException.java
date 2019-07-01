package com.ecp888.wxms.utils.exception;

public class AppException extends Exception implements ExceptionContextProvider{
	
	private static final long serialVersionUID = 1L;
	
	private ExceptionContext exceptionCtx;
	
	private String message;
	
    public AppException() {
		
	}
	
	public AppException(Throwable cause) {
		super(cause);
	}
	
	public AppException(String message) throws AppException {
		super(message);
		this.message=message;
	}


	public AppException(ExceptionContext exceptionCtx) {
		super(exceptionCtx.getErrorId()+"_"+exceptionCtx.getDescription());
		this.exceptionCtx = exceptionCtx;
	}

	public AppException(ExceptionContext exceptionCtx, Throwable cause) {
		super(exceptionCtx.getErrorId()+"_"+exceptionCtx.getDescription(), cause);
		this.exceptionCtx = exceptionCtx;
	}
	
	public ExceptionContext getExceptionCtx() {
		return exceptionCtx;
	}

	public void setExceptionCtx(ExceptionContext exceptionCtx) {
		this.exceptionCtx = exceptionCtx;
	}

	@Override
	public String toString() {
		StringBuffer strException = new StringBuffer();
		if(null != exceptionCtx){
			strException.append(exceptionCtx.getErrorId())
			            .append("{")
			            .append(exceptionCtx.getDescription())
			            .append("}");
			Throwable cause = getCause();
			if(null!=cause){
				strException.append("[").append(cause.toString()).append("]");
			}
		}else{
			strException.append("[系统异常，请联系管理员！]");
		}
		return strException.toString();
	}

	@Override
	public ExceptionContext getExceptionContext() {
         return exceptionCtx;
	}
	
	public String getMessage() {
		return message;
	}
	
    
}
