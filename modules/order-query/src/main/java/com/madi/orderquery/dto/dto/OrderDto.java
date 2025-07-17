package com.madi.orderquery.dto.dto;

import com.madi.ordercommand.db.enums.Currency;
import com.madi.ordercommand.dto.dto.ItemDto;
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
