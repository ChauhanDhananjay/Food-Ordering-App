package com.springboot.project.request;

import com.springboot.project.model.Address;
import com.springboot.project.model.ContactInformation;
import lombok.Data;

import java.util.List;

@Data
public class createRestaurantRequest {
    private Long id;
    private String name;
    private String description;
    private String cuisineType;
    private Address address;
    private ContactInformation contactInformation;
    private String openingHours;
    private List<String> images;
}
