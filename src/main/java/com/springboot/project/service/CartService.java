package com.springboot.project.service;

import com.springboot.project.model.Cart;
import com.springboot.project.model.CartItem;
import com.springboot.project.request.AddCardItemReq;

public interface CartService {
    public CartItem addItemToCart(AddCardItemReq req, String jwt) throws Exception;

    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception;

    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception;

    public Long calculateCartTotal(Cart cart) throws Exception;
    public Cart findCartById(Long id) throws Exception;
    public Cart findCartByUserId(Long userId) throws  Exception;
    public Cart clearCart(Long id) throws Exception;
}