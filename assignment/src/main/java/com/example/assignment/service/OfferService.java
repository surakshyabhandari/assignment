package com.example.assignment.service;

import com.example.assignment.dto.AddRequest.AddOfferRequest;
import com.example.assignment.dto.AddRequest.AddOwnerRequest;
import com.example.assignment.dto.AddRequest.AddProductRequest;
import com.example.assignment.dto.Response.OfferResponse;

import java.util.List;

public interface OfferService {

    OfferResponse addOffer(AddOfferRequest request);
    OfferResponse updateOffer(AddOfferRequest request, Long offerid);
    OfferResponse getOffer (Long offerid);
    List<OfferResponse> getAllOffer();
    OfferResponse addProduct(Long offerid, Long productid);
    OfferResponse deleteProduct(Long offerid, Long productid);

}
