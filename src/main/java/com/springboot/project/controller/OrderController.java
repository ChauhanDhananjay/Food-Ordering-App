package com.springboot.project.controller;
import com.springboot.project.model.CartItem;
import com.springboot.project.model.Order;
import com.springboot.project.model.User;
import com.springboot.project.repository.OrderServiceRepo;
import com.springboot.project.request.AddCardItemReq;
import com.springboot.project.request.OrderReq;
import com.springboot.project.service.OrderService;
import com.springboot.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @PostMapping("/order")
    public ResponseEntity<Order> createOrder(
            @RequestBody OrderReq req,
            @RequestParam("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        Order order =orderService.createOrder(req,user);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
    @GetMapping("/order/user")
    public ResponseEntity<List<Order>>getOrderHistory(
            @RequestParam("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        List<Order> orders =orderService.getUserOrder(user.getId());
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    }
