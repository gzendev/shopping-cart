package com.garbarino.cart.process.task.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import com.garbarino.cart.process.task.enums.CartStatus;
import com.garbarino.cart.process.task.exception.CartException;
import com.garbarino.cart.process.task.model.Cart;
import com.garbarino.cart.process.task.model.Item;
import com.garbarino.cart.process.task.model.Product;
import com.garbarino.cart.process.task.repository.CartRepository;
import com.garbarino.cart.process.task.repository.ProductRepository;
import com.garbarino.cart.process.task.service.CartService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Profile(value = {"dev", "qa", "ci", "prd"})
public class JpaCartService implements CartService {
	
	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public void process() throws CartException {
		List<Cart> carts = cartRepository.findAllByStatus(CartStatus.READY);
		
		if(carts == null || carts.isEmpty())
			log.info("Not found cart with status " + CartStatus.READY.getName());

		Set<Product> prodUpdated = new HashSet<Product>();
		for (Cart cart : carts) {
			boolean enableCart = true;
			prodUpdated.clear();
			
			for (Item item : cart.getItems()) {
				Product product = item.getPk().getProduct();
				Integer stock = product.getStock();
				Integer quantity = item.getQuantity();
				if(quantity > stock) {
					enableCart = false;
					break;
				} else {
					product.setStock(stock-quantity);
					prodUpdated.add(product);
				}
			}
			
			if(enableCart) {
				cart.setStatus(CartStatus.PROCESSED);
				log.info("Cart " + CartStatus.PROCESSED.getName() + " with Id " + cart.getId() + " and User: " + cart.getUser().getEmail());
			} else {
				cart.setStatus(CartStatus.FAILED);
				log.info("Cart " + CartStatus.FAILED.getName() + " with Id " + cart.getId() + " and User: " + cart.getUser().getEmail());
			}
			
			cartRepository.save(cart);
			productRepository.saveAll(prodUpdated);
		}
	}
	
}
