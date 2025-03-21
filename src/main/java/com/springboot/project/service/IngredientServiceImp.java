package com.springboot.project.service;

import com.springboot.project.model.Category;
import com.springboot.project.model.IngredientCategory;
import com.springboot.project.model.IngredientItems;
import com.springboot.project.model.Restaurant;
import com.springboot.project.repository.IngredientCategoryRepo;
import com.springboot.project.repository.IngredientItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImp implements IngredientService {
    @Autowired
    private IngredientCategoryRepo ingredientCategoryRepo;

    @Autowired
    private IngredientItemRepo ingredientItemRepo;
    @Autowired
    private RestaurantService restaurantService;

    @Override
    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientCategory category = new IngredientCategory();
        category.setRestaurant(restaurant);
        category.setName(name);
        return ingredientCategoryRepo.save(category);
    }

    @Override
    public IngredientCategory findIngredientCategoryById(Long id) throws Exception {
        Optional<IngredientCategory> optional =ingredientCategoryRepo.findById(id);
        if (optional.isEmpty()){
            throw new Exception("Ingredient Category Not Found");
        }
        return optional.get();
    }

    @Override
    public List<IngredientCategory> findIngredientByRestaurant(Long id) throws Exception {
        restaurantService.findRestaurantById(id);
        return ingredientCategoryRepo.findByRestaurantId(id);
    }

    @Override
    public IngredientItems createIngredientItems(Long restaurantId, String IngredientName, Long CategoryId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientCategory category = findIngredientCategoryById(CategoryId);
        IngredientItems ingredientItems = new IngredientItems();
        ingredientItems.setName(IngredientName);
        ingredientItems.setRestaurant(restaurant);
        ingredientItems.setCategory(category);
        IngredientItems ingredients = ingredientItemRepo.save(ingredientItems);
        category.getItems().add(ingredients);

        return ingredients;
    }

    @Override
    public List<IngredientItems> findRestaurantIngredient(Long restaurantId) throws Exception {
        return ingredientItemRepo.findByRestaurantId(restaurantId);
    }

    @Override
    public IngredientItems updateStock(Long id) throws Exception {
        Optional<IngredientItems> optional = ingredientItemRepo.findById(id);
        if (optional.isEmpty()){
            throw new Exception("Ingredient Not Found");
        }
        IngredientItems ingredientItems = optional.get();
        ingredientItems.setInStock(!ingredientItems.isInStock());
        return ingredientItemRepo.save(ingredientItems);
    }
}
