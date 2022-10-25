package ar.model;

public class SalesException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SalesException(String msg) {
		super(msg);
	}
	
	public SalesException(String msg, Throwable e) {
		super(msg, e);
	}
}
