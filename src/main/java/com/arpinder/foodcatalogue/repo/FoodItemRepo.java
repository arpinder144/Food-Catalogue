package com.arpinder.foodcatalogue.repo;

import com.arpinder.foodcatalogue.dto.Restaurant;
import com.arpinder.foodcatalogue.entity.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodItemRepo extends JpaRepository<FoodItem, Long> {
    List<FoodItem> findByRestaurantId(Integer restaurantId);
}
