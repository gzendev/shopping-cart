package com.garbarino.shoppingcart.ws.services;

import java.util.Set;
import com.garbarino.shoppingcart.ws.dto.CartDto;
import com.garbarino.shoppingcart.ws.dto.ProductDto;

public interface CartService {
	
	public CartDto findById(final Long id);
	
	public CartDto save(final CartDto dto);
	
	public  CartDto addProduct(final Long id, final ProductDto dto);
	
	public void deleteProduct(final Long id, final Long pid);
	
	public Set<ProductDto> findAllProducts(final Long id);
	
	public CartDto checkout(final Long id);

}
