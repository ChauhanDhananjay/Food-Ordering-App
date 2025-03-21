package com.springboot.project.controller;

import com.springboot.project.dto.RestaurantDto;
import com.springboot.project.model.Restaurant;
import com.springboot.project.model.User;
import com.springboot.project.request.createRestaurantRequest;
import com.springboot.project.service.RestaurantService;
import com.springboot.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private UserService userService;
    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> crateRestaurant(
            @RequestHeader("Authorization") String jwt,
            @RequestParam String keyword) throws Exception {
        List<Restaurant> restaurants = restaurantService.searchRestaurant(keyword);
        return new ResponseEntity<>(restaurants,HttpStatus.CREATED);
    }
    @GetMapping()
    public ResponseEntity<List<Restaurant>> getAllRestaurant(
            @RequestHeader("Authorization") String jwt) throws Exception {
        List<Restaurant> restaurants = restaurantService.getAllRestaurant();
        return new ResponseEntity<>(restaurants,HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findRestaurantById(
            @RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception {
        Restaurant restaurants = restaurantService.findRestaurantById(id);
        return new ResponseEntity<>(restaurants,HttpStatus.OK);
    }
    @PutMapping("/{id}/add-favorites")
    public ResponseEntity<RestaurantDto> addToFavorites(
            @RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        RestaurantDto restaurants = restaurantService.addToFavorites(id,user);
        return new ResponseEntity<>(restaurants,HttpStatus.OK);

    }


}
