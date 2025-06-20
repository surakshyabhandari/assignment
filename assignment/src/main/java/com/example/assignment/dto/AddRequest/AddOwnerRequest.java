package com.example.assignment.dto.AddRequest;

import com.example.assignment.dto.Response.ProductResponse;
import com.example.assignment.entity.Product;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Setter
@Getter
public class AddOwnerRequest {

    private String name;
    private String email;
    private String password;
    private String role;
    private List<Long> productlist;
}
