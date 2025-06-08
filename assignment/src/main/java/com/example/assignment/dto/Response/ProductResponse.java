package com.example.assignment.dto.Response;

import com.example.assignment.entity.Offer;
import com.example.assignment.entity.Status;
import com.example.assignment.entity.Owner;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ProductResponse {

    private long id;
    private String name;
    private String description;
    private String status;
    private String imageurl;
    private Owner owner;
    private List<OfferResponse> offers;
}
