package com.madi.orderquery.dto.dto;

import com.madi.orderquery.db.enums.Currency;
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
