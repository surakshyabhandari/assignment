package com.example.assignment.service;

import com.example.assignment.dto.AddRequest.AddOwnerRequest;
import com.example.assignment.dto.Message;
import com.example.assignment.dto.Response.OwnerResponse;
import com.example.assignment.dto.Response.ProductResponse;

import java.util.List;

public interface OwnerService {

    OwnerResponse addowner(AddOwnerRequest request);

//    List<ProductResponse> getAllProduct();

}
