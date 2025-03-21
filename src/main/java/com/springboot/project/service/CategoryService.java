package com.springboot.project.service;

import com.springboot.project.model.Category;
import com.springboot.project.model.User;

import java.util.List;

public interface CategoryService {
    public Category createCategory(String name, Long userId) throws Exception;
    public List<Category> findCategoryByRestaurant(Long id) throws Exception;
    public Category findCategoryById(Long id) throws Exception;


}
