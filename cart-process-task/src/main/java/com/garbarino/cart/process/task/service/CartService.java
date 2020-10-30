package com.garbarino.cart.process.task.service;

import com.garbarino.cart.process.task.exception.CartException;

public interface CartService {
	
	public void process() throws CartException;

}
