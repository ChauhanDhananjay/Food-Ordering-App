package com.springboot.project.controller;

import com.springboot.project.model.Order;
import com.springboot.project.model.User;
import com.springboot.project.request.OrderReq;
import com.springboot.project.service.OrderService;
import com.springboot.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminOrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @GetMapping("/order/restaurant/{id}")
    public ResponseEntity<List<Order>>getOrderHistory(
            @PathVariable Long id,
            @RequestParam (required = false) String orderStatus,
            @RequestParam("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Order> orders =orderService.getRestaurantOrders(id,orderStatus);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
    @PutMapping("/order/{id}/{orderStatus")
    public ResponseEntity<Order>updateOrderStatus(
            @PathVariable Long id,
            @PathVariable String orderStatus,
            @RequestParam("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Order orders =orderService.updateOrder(id,orderStatus);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
