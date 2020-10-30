package com.garbarino.shoppingcart.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.garbarino.shoppingcart.ws.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

}
