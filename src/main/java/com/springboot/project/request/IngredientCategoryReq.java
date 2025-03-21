package com.springboot.project.request;

import lombok.Data;

@Data
public class IngredientCategoryReq {
    private String  name;
    private Long RestaurantId;
}
