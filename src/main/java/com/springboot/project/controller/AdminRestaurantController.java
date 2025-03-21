package com.springboot.project.controller;

import com.springboot.project.model.Restaurant;
import com.springboot.project.model.User;
import com.springboot.project.request.createRestaurantRequest;
import com.springboot.project.response.MessageResponse;
import com.springboot.project.service.RestaurantService;
import com.springboot.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/restaurants")
public class AdminRestaurantController {
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private UserService userService;
    @PostMapping()
    public ResponseEntity<Restaurant> crateRestaurant(
            @RequestBody createRestaurantRequest req,
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user =userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.createRestaurant(req,user);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(
            @RequestBody createRestaurantRequest req,
            @RequestHeader("Authorization") String jwt,@PathVariable Long id) throws Exception {
        User user =userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.updateRestaurant(id,req);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteRestaurant(
            @RequestHeader("Authorization") String jwt,@PathVariable Long id) throws Exception {
        User user =userService.findUserByJwtToken(jwt);
        restaurantService.deleteRestaurant(id);
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage("Restaurant Deleted Successfully");
        return new ResponseEntity<>(messageResponse,HttpStatus.CREATED);
    }
    @PutMapping("/{id}/status")
    public ResponseEntity<Restaurant> updaterestaurantStatus(
            @RequestBody createRestaurantRequest req,
            @RequestHeader("Authorization") String jwt,@PathVariable Long id) throws Exception {
        User user =userService.findUserByJwtToken(jwt);
        Restaurant restaurant=restaurantService.updateRestaurantStatus(id);
        return new ResponseEntity<>(restaurant,HttpStatus.OK);
    }
    @GetMapping("/user")
    public ResponseEntity<Restaurant> findRestaurantByUserId(
            @RequestBody createRestaurantRequest req,
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user =userService.findUserByJwtToken(jwt);
        Restaurant restaurant=restaurantService.findRestaurantById(user.getId());
        return new ResponseEntity<>(restaurant,HttpStatus.OK);
    }
}
