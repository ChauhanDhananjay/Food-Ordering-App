package com.springboot.project.service;

import com.springboot.project.model.Category;
import com.springboot.project.model.Food;
import com.springboot.project.model.Restaurant;
import com.springboot.project.request.createFoodRequest;

import java.util.List;

public interface FoodService {
    public Food createFood(createFoodRequest req, Category category, Restaurant restaurant);
    void deleteFood(Long foodId) throws Exception;
    public List<Food> getRestaurantFood(
            Long restaurantId,
            boolean isVegetarian,
            boolean isNonVeg,
            boolean isSeasonal,
            String foodCategory);
    public List<Food> searchFood(String keyword);
    public Food findFoodById(Long foodId) throws Exception;
    public Food updateAvailabilityStatus(Long foodId) throws Exception;
}
