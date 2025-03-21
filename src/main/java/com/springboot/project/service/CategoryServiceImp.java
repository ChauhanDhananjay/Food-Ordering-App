package com.springboot.project.service;

import com.springboot.project.model.Category;
import com.springboot.project.model.Restaurant;
import com.springboot.project.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class CategoryServiceImp implements CategoryService{
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(String name, Long userId) throws Exception{

        Restaurant restaurant = restaurantService.getRestaurantByUserId(userId);
        Category category= new Category();
        category.setName(name);
        category.setRestaurant(restaurant);
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findCategoryByRestaurant(Long id) throws Exception {
        Restaurant restaurant = restaurantService.getRestaurantByUserId(id);
        return categoryRepository.findByRestaurantId(id);
    }

    @Override
    public Category findCategoryById(Long id) throws Exception {
        Optional<Category> optionalCategory=categoryRepository.findById(id);
        if (optionalCategory.isEmpty()){
            throw new Exception("Category Not Found");
        }
        return optionalCategory.get();
    }
}
