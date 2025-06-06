package com.example.assignment.dto.AddRequest;

import com.example.assignment.entity.Product;
import lombok.Data;

import java.util.List;

@Data
public class AddOfferRequest {

    private String title;
    private String description;
    private List<Product> products;
}
