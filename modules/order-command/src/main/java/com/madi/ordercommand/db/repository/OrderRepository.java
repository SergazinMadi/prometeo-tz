package com.madi.ordercommand.db.repository;

import com.madi.ordercommand.db.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
