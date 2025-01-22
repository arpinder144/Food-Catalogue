package com.arpinder.foodcatalogue.mapper;

import com.arpinder.foodcatalogue.dto.FoodItemDTO;
import com.arpinder.foodcatalogue.entity.FoodItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FoodItemMapper {
    FoodItemMapper INSTANCE = Mappers.getMapper(FoodItemMapper.class);

    FoodItemDTO foodItemToFoodItemDTO(FoodItem foodItem);
    FoodItem foodItemDTOToFoodItem(FoodItemDTO foodItemDTO);
}
