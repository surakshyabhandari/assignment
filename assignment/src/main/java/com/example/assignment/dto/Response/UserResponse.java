package com.example.assignment.dto.Response;

import com.example.assignment.entity.Product;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class UserResponse {

    private long id;
    private String name;
    private String email;
    private List<Product> productlist;
}
