package com.madi.ordercommand.db.repository;

import com.madi.ordercommand.db.entity.Item;
import com.madi.ordercommand.db.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemRepository extends JpaRepository<Item, Long> {
    void deleteAllByOrder(Order order);
}
