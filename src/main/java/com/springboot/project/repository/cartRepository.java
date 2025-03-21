package com.springboot.project.repository;

import com.springboot.project.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface cartRepository extends JpaRepository<Cart,Long> {
    public Cart findByCustomerId(Long userId);
}
