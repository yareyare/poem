package com.ivy.exception;

public class BaseException extends Exception{

	private static final long serialVersionUID = 3464089633733527470L;

	public static final String DB_EXCEPTION = "DB EXCEPTION !";
	
	public static final String BUSI_EXCEPTION = "BUSI EXCEPTION !";
	
	public BaseException(){}
	
	public BaseException(String msg){
		super(msg);
	}
	
	public BaseException( Throwable cause) {
        super(cause);
    }
	
	public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
