package com.madi.orderquery.dto.mapper;

import com.madi.ordercommand.db.entity.Item;
import com.madi.ordercommand.dto.dto.ItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ItemMapper {
    ItemDto toDto(Item item);
    Item toEntity(ItemDto itemDto);
}

