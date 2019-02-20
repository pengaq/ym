package com.yunmai.util.exception;


public class BussinessException extends RuntimeException{

	public enum ERROR{
		
		UNKNOW_FAIL(999,"未知错误");
		
		 
	    private int errcode;
	    private String message; 
	    
	    private ERROR(int errcode,String message)
	    {
	        this.errcode = errcode;
	        this.message = message;
	    }

		public int getErrcode() {
			return errcode;
		}

		public void setErrcode(int errcode) {
			this.errcode = errcode;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
		
	}
	
	private ERROR error;
	
	
	public ERROR getError() {
		return error;
	}
	public void setError(ERROR error) {
		this.error = error;
	}
	
	public BussinessException( ERROR error) {
		super();
		this.error = error;
	}
	
}
