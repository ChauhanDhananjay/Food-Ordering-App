package com.springboot.project.repository;

import com.springboot.project.model.Cart;
import com.springboot.project.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface cartItemRepo extends JpaRepository<CartItem,Long> {
    public Cart findByCustomerId(Long userId);
}
