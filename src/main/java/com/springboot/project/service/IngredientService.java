package com.springboot.project.service;

import com.springboot.project.model.IngredientCategory;
import com.springboot.project.model.IngredientItems;

import java.util.List;

public interface IngredientService {
    public IngredientCategory createIngredientCategory(String name,Long restaurantId) throws Exception;
    public IngredientCategory findIngredientCategoryById(Long id) throws Exception;
    public List<IngredientCategory> findIngredientByRestaurant(Long id) throws Exception;
    public IngredientItems createIngredientItems(Long restaurantId,String IngredientName,Long CategoryId) throws Exception;
    public List<IngredientItems> findRestaurantIngredient(Long restaurantId) throws Exception;
    public IngredientItems updateStock(Long id) throws Exception;
 }
