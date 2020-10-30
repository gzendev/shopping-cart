package com.garbarino.shoppingcart.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.garbarino.shoppingcart.ws.model.Item;
import com.garbarino.shoppingcart.ws.model.ItemPk;

@Repository
public interface ItemRepository extends JpaRepository<Item, ItemPk> {

}
