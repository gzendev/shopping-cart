package com.garbarino.shoppingcart.ws.services.impl;

import java.math.BigDecimal;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.garbarino.shoppingcart.ws.dto.CartDto;
import com.garbarino.shoppingcart.ws.dto.ProductDto;
import com.garbarino.shoppingcart.ws.enums.CartStatus;
import com.garbarino.shoppingcart.ws.exception.CartException;
import com.garbarino.shoppingcart.ws.exception.ResourceNotFoundException;
import com.garbarino.shoppingcart.ws.model.Cart;
import com.garbarino.shoppingcart.ws.model.Item;
import com.garbarino.shoppingcart.ws.model.ItemPk;
import com.garbarino.shoppingcart.ws.model.Product;
import com.garbarino.shoppingcart.ws.repository.CartRepository;
import com.garbarino.shoppingcart.ws.repository.ItemRepository;
import com.garbarino.shoppingcart.ws.repository.ProductRepository;
import com.garbarino.shoppingcart.ws.services.CartService;

@Service
public class JpaCartService implements CartService {
	
	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ProductRepository productRespository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Override
	public CartDto findById(final Long id) {
		Cart cart = cartRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found cart with id = " + id));
		return Cart.fromModel(cart);
	}

	@Override
	public CartDto save(final CartDto dto) {
		Cart cart = cartRepository.save(CartDto.toModel(dto));
		if(cart == null)
			throw new CartException("Processing error for " + dto.getEmail());
		return Cart.fromModel(cart);
	}

	@Override
	public CartDto addProduct(final Long id, final ProductDto dto) {
		Cart cart = cartRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found cart with id = " + id));
		Product product = productRespository.findById(dto.getId()).orElseThrow(() -> new ResourceNotFoundException("Not found product with id = " + dto.getId()));
		BigDecimal unitPrice = dto.getUnitPrice() == null ? product.getUnitPrice() : dto.getUnitPrice();
		cart.getItems().add(new Item(new ItemPk(cart, product), dto.getQuantity(), unitPrice, unitPrice.multiply(new BigDecimal(dto.getQuantity()))));
		Cart model = cartRepository.save(cart);
		if(model == null)
			throw new CartException("Processing error Cart id " + id);
		return Cart.fromModel(model);
	}

	@Override
	public void deleteProduct(Long id, Long pid) {
		Cart cart = cartRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found cart with id = " + id));
		Product product = productRespository.findById(pid).orElseThrow(() -> new ResourceNotFoundException("Not found product with id = " + pid));
		itemRepository.deleteById(new ItemPk(cart, product));
	}

	@Override
	public Set<ProductDto> findAllProducts(Long id) {
		Cart cart = cartRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found cart with id = " + id));
		CartDto dto = Cart.fromModel(cart);
		return dto.getProducts();
	}

	@Override
	public CartDto checkout(Long id) {
		Cart cart = cartRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found cart with id = " + id));
		cart.setStatus(CartStatus.READY);
		cartRepository.save(cart);
		Cart model = cartRepository.save(cart);
		if(model == null)
			throw new CartException("Processing error Cart id " + id);
		return Cart.fromModel(model);
	}
	
}
