package com.example.assignment.dto.AddRequest;

import com.example.assignment.entity.Product;
import lombok.Data;

import java.util.List;

@Data
public class AddUserRequest {

    private String name;
    private String email;
    private List<Product> productlist;
}
