package com.example.assignment.service;

import com.example.assignment.dto.AddRequest.AddProductRequest;
import com.example.assignment.dto.Message;
import com.example.assignment.dto.Response.OfferResponse;
import com.example.assignment.dto.Response.ProductResponse;
import com.example.assignment.entity.Product;
import com.example.assignment.entity.Status;
import com.example.assignment.repository.ProductRepository;
import org.springframework.data.domain.Page;


import java.util.List;

public interface ProductService {

//    Page<Product> getPaigination(int page, int size);
    ProductResponse addProduct(AddProductRequest request);
    ProductResponse updateProduct(AddProductRequest request, Long productid);
    ProductResponse getProduct (Long productid);
    ProductResponse rentProduct (Long productid);
    ProductResponse donateProduct (Long productid);
    List<ProductResponse> findRentedProduct ();
    List<ProductResponse> findDonatedProduct();
    Page<Product> getAllProduct(Status status, int page, int size);

}
