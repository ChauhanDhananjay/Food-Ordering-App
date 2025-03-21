package com.springboot.project.request;

import com.springboot.project.model.Category;
import com.springboot.project.model.IngredientItems;
import lombok.Data;

import java.util.List;

@Data
public class createFoodRequest {
    private String name;
    private String description;
    private Long price;
    private Category category;
    private List<String> images;
    private Long restaurantId;
    private boolean vegetarian;
    private boolean seasonal;
    private List<IngredientItems> ingredientItems;
}
