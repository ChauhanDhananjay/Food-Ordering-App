package com.springboot.project.request;

import lombok.Data;

@Data
public class updateCartItemReq {
    private Long cartItem;
    private  int quantity;
}
