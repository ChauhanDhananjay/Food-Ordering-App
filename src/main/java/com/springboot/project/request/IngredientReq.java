package com.springboot.project.request;

import lombok.Data;

@Data
public class IngredientReq {
    private String name;
    private Long categoryId;
    private Long restaurantId;
}
