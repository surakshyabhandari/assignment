package com.example.assignment.service;

import com.example.assignment.dto.AddRequest.AddProductRequest;
import com.example.assignment.dto.Message;
import com.example.assignment.dto.Response.OfferResponse;
import com.example.assignment.dto.Response.ProductResponse;
import com.example.assignment.repository.ProductRepository;

import java.util.List;

public interface ProductService {

    ProductResponse addProduct(AddProductRequest request);
    ProductResponse updateProduct(AddProductRequest request, Long productid);
    ProductResponse getProduct (AddProductRequest request, Long productid);
    ProductResponse rentProduct (AddProductRequest request, Long productid);
    ProductResponse donateProduct (AddProductRequest request, Long productid);
    List<ProductResponse> findRentedProduct ();
    List<ProductResponse> findDonatedProduct();
    List<ProductResponse> getALlProduct();

}
