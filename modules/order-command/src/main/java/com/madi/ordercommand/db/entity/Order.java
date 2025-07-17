package com.madi.ordercommand.db.entity;

import com.madi.ordercommand.db.enums.Currency;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Long customerId;
    @Enumerated(value = EnumType.STRING)
    private Currency currency;
}
