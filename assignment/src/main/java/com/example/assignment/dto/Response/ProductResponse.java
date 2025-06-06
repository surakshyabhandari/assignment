package com.example.assignment.dto.Response;

import com.example.assignment.entity.Offer;
import com.example.assignment.entity.Status;
import com.example.assignment.entity.Owner;
import lombok.Data;

import java.util.List;

@Data
public class ProductResponse {

    private long id;
    private String name;
    private String description;
    private Status status;
    private String imageurl;
    private Owner owner;
    private List<Offer> offers;
}
