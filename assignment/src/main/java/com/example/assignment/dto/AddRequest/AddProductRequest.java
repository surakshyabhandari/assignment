package com.example.assignment.dto.AddRequest;

import com.example.assignment.dto.Response.OfferResponse;
import com.example.assignment.entity.Offer;
import com.example.assignment.entity.Status;
import com.example.assignment.entity.Owner;
import lombok.Data;

import java.util.List;

@Data
public class AddProductRequest {

    private String name;
    private String description;
    private String status;
    private String imageurl;
    private Owner owner;
    private List<Long> offers;
}
