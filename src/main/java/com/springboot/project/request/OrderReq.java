package com.springboot.project.request;

import com.springboot.project.model.Address;
import lombok.Data;

@Data
public class OrderReq {
    private Long restaurantId;
    private Address deliveryAddress;
}
