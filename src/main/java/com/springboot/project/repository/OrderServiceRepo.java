package com.springboot.project.repository;

import com.springboot.project.model.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderServiceRepo extends JpaRepository<Order,Long> {
    public List<Order> findByCustomerId(Long userId);
    public List<Order> findByRestaurantId(Long restaurantId);

}
