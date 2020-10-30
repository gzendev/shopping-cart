package com.garbarino.cart.process.task.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import com.garbarino.cart.process.task.enums.CartStatus;
import com.garbarino.cart.process.task.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
	
	@Query(value = "SELECT c FROM Cart c WHERE c.status = :status")
	public List<Cart> findAllByStatus(@Param("status") final CartStatus status);
	
	@Async
    public <S extends Cart> S save(S entity);
}
