package com.garbarino.cart.process.task.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import com.garbarino.cart.process.task.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Async
    public <S extends Product> List<S> saveAll(Iterable<S> entities);
}
