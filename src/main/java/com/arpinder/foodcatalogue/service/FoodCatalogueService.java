package com.arpinder.foodcatalogue.service;

import com.arpinder.foodcatalogue.dto.FoodCataloguePage;
import com.arpinder.foodcatalogue.dto.FoodItemDTO;
import com.arpinder.foodcatalogue.dto.Restaurant;
import com.arpinder.foodcatalogue.entity.FoodItem;
import com.arpinder.foodcatalogue.mapper.FoodItemMapper;
import com.arpinder.foodcatalogue.repo.FoodItemRepo;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FoodCatalogueService {
    @Autowired
    FoodItemRepo foodItemRepo;

    @Autowired
    private RestTemplate restTemplate;

    public FoodItemDTO addFoodItem(FoodItemDTO foodItemDTO) {
        FoodItem foodItemSavedInDB = FoodItemMapper.INSTANCE.foodItemDTOToFoodItem(foodItemDTO);
        foodItemRepo.save(foodItemSavedInDB);
        return FoodItemMapper.INSTANCE.foodItemToFoodItemDTO(foodItemSavedInDB);
    }

    public FoodCataloguePage fetchFoodCataloguePageDetails(Integer restaurantId) {
        //foodList
        //restaurantList
        List<FoodItem> foodItemList= fetchFoodItemList(restaurantId);
        Restaurant restaurant = fetchRestaurantDetailsFromRestaurantMS(restaurantId);
        return createFoodCataloguePage(foodItemList, restaurant);


    }

    private FoodCataloguePage createFoodCataloguePage(List<FoodItem> foodItemList, Restaurant restaurant) {
        FoodCataloguePage foodCataloguePage = new FoodCataloguePage();
        foodCataloguePage.setRestaurant(restaurant);
        foodCataloguePage.setFoodItemList(foodItemList);
        return foodCataloguePage;

    }

    private Restaurant fetchRestaurantDetailsFromRestaurantMS(Integer restaurantId) {
        return restTemplate.getForObject("http://RESTAURANT-SERVICE/restaurant/fetchById/"+restaurantId, Restaurant.class);

    }

    private List<FoodItem> fetchFoodItemList(Integer restaurantId) {
       return foodItemRepo.findByRestaurantId(restaurantId);

    }



}
