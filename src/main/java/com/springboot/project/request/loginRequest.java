package com.springboot.project.request;

import lombok.Data;

@Data
public class loginRequest {
    private String email;
    private String password;
}
