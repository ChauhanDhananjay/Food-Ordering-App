package com.springboot.project.controller;

import com.springboot.project.model.IngredientCategory;
import com.springboot.project.model.IngredientItems;
import com.springboot.project.request.IngredientCategoryReq;
import com.springboot.project.request.IngredientReq;
import com.springboot.project.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientController {
    @Autowired
    private IngredientService ingredientService;
    @PostMapping("/category")
    private ResponseEntity<IngredientCategory> createIngredientCategory(
            @RequestBody IngredientCategoryReq req
            ) throws Exception {
        IngredientCategory items = ingredientService.createIngredientCategory(req.getName(), req.getRestaurantId());
        return new ResponseEntity<>(items,HttpStatus.CREATED);
    }
    @PostMapping()
    private ResponseEntity<IngredientItems> createIngredientItem(
            @RequestBody IngredientReq req
    ) throws Exception {
        IngredientItems items = ingredientService.createIngredientItems(req.getRestaurantId(), req.getName(), req.getCategoryId());
        return new ResponseEntity<>(items,HttpStatus.CREATED);
    }
    @PutMapping("/{id}/stock")
    private ResponseEntity<IngredientItems> updateIngredientStock(
            @PathVariable Long id
    ) throws Exception {
        IngredientItems items = ingredientService.updateStock(id);
        return new ResponseEntity<>(items,HttpStatus.OK);
    }
    @GetMapping("/restaurant/{id}")
    private ResponseEntity <List<IngredientItems>> getRestaurantIngredient(
            @PathVariable Long id
    ) throws Exception {
        List<IngredientItems> items = ingredientService.findRestaurantIngredient(id);
        return new ResponseEntity<>(items,HttpStatus.OK);
    }
    @GetMapping("/restaurant/{id}/category")
    private ResponseEntity <List<IngredientCategory>> getRestaurantCategory(
            @PathVariable Long id
    ) throws Exception {
        List<IngredientCategory> items = ingredientService.findIngredientByRestaurant(id);
        return new ResponseEntity<>(items,HttpStatus.OK);
    }
}

