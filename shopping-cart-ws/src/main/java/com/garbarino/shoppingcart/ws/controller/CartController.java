package com.garbarino.shoppingcart.ws.controller;

import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.garbarino.shoppingcart.ws.dto.CartDto;
import com.garbarino.shoppingcart.ws.dto.ProductDto;
import com.garbarino.shoppingcart.ws.services.CartService;

@RestController
@RequestMapping("/carts")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@GetMapping("{id}")
	public ResponseEntity<CartDto> findById(@PathVariable final Long id) {
	  return ResponseEntity.ok(cartService.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<CartDto> save(@Valid @RequestBody(required = true) final CartDto dto) {
		return ResponseEntity.ok(cartService.save(dto));
	}
	
	@PostMapping("{id}/products")
	public ResponseEntity<CartDto> addProduct(@PathVariable final Long id, @Valid @RequestBody(required = true) final ProductDto dto) {
		return ResponseEntity.ok(cartService.addProduct(id, dto));
	}
	
	@DeleteMapping("{id}/products/{pid}")
	public ResponseEntity<Long> deleteProduct(@PathVariable Long id, @PathVariable Long pid) {
		cartService.deleteProduct(id, pid);
		return ResponseEntity.ok(pid);
	}
	
	@GetMapping("{id}/products")
	public ResponseEntity<Set<ProductDto>> findAllProducts(@PathVariable final Long id) {
	  return ResponseEntity.ok(cartService.findAllProducts(id));
	}
	
	@PostMapping("{id}/checkout")
	public ResponseEntity<CartDto> checkout(@PathVariable final Long id) {
		return ResponseEntity.ok(cartService.checkout(id));
	}

}
