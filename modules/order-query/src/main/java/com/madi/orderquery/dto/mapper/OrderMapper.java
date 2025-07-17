package com.madi.orderquery.dto.mapper;

import com.madi.orderquery.db.entity.Order;
import com.madi.orderquery.dto.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = ItemMapper.class)
public interface OrderMapper {
    OrderDto toDto(Order order);
    Order toEntity(OrderDto orderDto);
}