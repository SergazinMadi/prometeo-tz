package com.madi.orderquery.db.repository;

import com.madi.orderquery.db.entity.Item;
import com.madi.orderquery.db.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    void deleteAllByOrder(Order order);

    List<Item> findAllByOrder(Order order);
}
