package com.example.assignment.dto.Response;

import com.example.assignment.entity.Product;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Data
@Getter
@Setter
public class OwnerResponse {

    private long id;
    private String name;
    private String email;
    private List<ProductResponse> productlist;
}
