package com.yunmai.commons;

public class BusinessProcessException extends Exception {

	/**
	 * 业务异常处理
	 */
	private static final long serialVersionUID = 1L;
	
	
	public BusinessProcessException() {
		super();
	}

	public BusinessProcessException(String message) {
		super(message);
	}

	public BusinessProcessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessProcessException(Throwable cause) {
		super(cause);
	}

}
