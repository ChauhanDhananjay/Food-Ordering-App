package com.springboot.project.controller;

import com.springboot.project.model.Cart;
import com.springboot.project.model.CartItem;
import com.springboot.project.model.User;
import com.springboot.project.request.AddCardItemReq;
import com.springboot.project.request.updateCartItemReq;
import com.springboot.project.service.CartService;
import com.springboot.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;
    @PutMapping("/cart/add")
    public ResponseEntity<CartItem> addItemToCart(
            @RequestBody AddCardItemReq req,
            @RequestParam("Authorization") String jwt) throws Exception {
        CartItem cartItem = cartService.addItemToCart(req, jwt);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);

    }
    @PutMapping("/cart-item/update")
    public ResponseEntity<CartItem> updateCartItemQuantity(
            @RequestBody updateCartItemReq req,
            @RequestParam("Authorization") String jwt) throws Exception {
        CartItem cartItem = cartService.updateCartItemQuantity(req.getCartItem(),req.getQuantity());
        return new ResponseEntity<>(cartItem, HttpStatus.OK);

    }
    @DeleteMapping("/cart-item/{id}/remove")
    public ResponseEntity<Cart> removeCartItem(
            @PathVariable Long id,
            @RequestParam("Authorization") String jwt) throws Exception {
        Cart cart = cartService.removeItemFromCart(id,jwt);
        return new ResponseEntity<>(cart, HttpStatus.OK);

    }
    @PutMapping("/cart/clear")
    public ResponseEntity<Cart> clearCart(
            @RequestParam("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartService.clearCart(user.getId());
        return new ResponseEntity<>(cart, HttpStatus.OK);

    }
    @GetMapping("/cart/")
    public ResponseEntity<Cart>findUserCart(
            @RequestParam("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartService.findCartByUserId(user.getId());
        return new ResponseEntity<>(cart, HttpStatus.OK);

    }
}


