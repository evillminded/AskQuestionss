package com.project.askquestion.exception;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;

//@NoArgsConstructor
//@AllArgsConstructor
public class NotFound extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	
	public NotFound() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}
	public NotFound(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public NotFound(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public NotFound(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
