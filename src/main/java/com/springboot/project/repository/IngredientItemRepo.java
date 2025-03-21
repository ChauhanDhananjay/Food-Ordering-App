package com.springboot.project.repository;

import com.springboot.project.model.IngredientItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientItemRepo extends JpaRepository<IngredientItems,Long> {
    List<IngredientItems> findByRestaurantId(Long id);

}
