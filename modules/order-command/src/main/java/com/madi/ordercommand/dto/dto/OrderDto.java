package com.madi.ordercommand.dto.dto;

import com.madi.ordercommand.db.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long customerId;
    private List<ItemDto> items;
    private Currency currency;
}
