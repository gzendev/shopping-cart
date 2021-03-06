package com.garbarino.cart.process.task.exception;


public final class CartException extends RuntimeException {

	private static final long serialVersionUID = 2070058637450167250L;
	
	private final String message;
	
	public CartException(String message) {
		this.message = message;
	}
	
	public static CartException notFound() {
		return new CartException(Messages.ID_NOT_FOUND);
	}
	
	public String getMessage() {
		return message;
	}
}
