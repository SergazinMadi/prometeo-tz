package com.madi.orderquery.dto.mapper;

import com.madi.ordercommand.db.entity.Order;
import com.madi.ordercommand.dto.dto.OrderDto;
import com.madi.ordercommand.dto.mapper.ItemMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = ItemMapper.class)
public interface OrderMapper {
    OrderDto toDto(Order order);
    Order toEntity(OrderDto orderDto);
}