package com.springboot.project.request;

import lombok.Data;

import java.util.List;

@Data
public class AddCardItemReq {
    private Long foodId;
    private int quantity;
    private List<String> ingredients;
}
