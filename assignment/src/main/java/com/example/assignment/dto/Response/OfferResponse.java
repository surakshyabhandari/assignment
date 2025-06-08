package com.example.assignment.dto.Response;

import com.example.assignment.entity.Product;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class OfferResponse {

    private long id;
    private String title;
    private String description;
    private List<ProductResponse> products;
}
