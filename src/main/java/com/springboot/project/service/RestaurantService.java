package com.springboot.project.service;

import com.springboot.project.dto.RestaurantDto;
import com.springboot.project.model.Restaurant;
import com.springboot.project.model.User;
import com.springboot.project.request.createRestaurantRequest;

import java.util.List;

public interface RestaurantService {
    public  Restaurant createRestaurant(createRestaurantRequest req, User user);
    public Restaurant updateRestaurant(Long restaurantId,createRestaurantRequest updatedRestaurant) throws Exception;
     public void deleteRestaurant(Long restaurant) throws Exception;
     public List<Restaurant> getAllRestaurant();
     public List<Restaurant> searchRestaurant(String keyword);

     public Restaurant findRestaurantById(Long id) throws Exception;

     public Restaurant getRestaurantByUserId(Long userid ) throws Exception;
     public RestaurantDto addToFavorites(Long restaurantId,User user) throws Exception;
     public Restaurant updateRestaurantStatus(Long id)throws Exception;

    }

