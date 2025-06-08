package com.example.assignment.controller;

import com.example.assignment.dto.AddRequest.AddProductRequest;
import com.example.assignment.dto.Message;
import com.example.assignment.dto.Response.OfferResponse;
import com.example.assignment.dto.Response.ProductResponse;
import com.example.assignment.repository.ProductRepository;
import com.example.assignment.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductService productService, ProductRepository productRepository){
        this.productService = productService;
        this.productRepository = productRepository;

    }

    @PostMapping(value="/products")
    public ProductResponse addProduct(@ModelAttribute AddProductRequest request){
        log.info("add product::{}",request);
        return productService.addProduct(request);
    }

    @PutMapping(value="/products/{productid}")
    public ProductResponse updateProduct(@RequestBody AddProductRequest request, Long productid){
        log.info("update product::{}[{}]", request,productid);
        return productService.updateProduct(request,productid);
    }

    @GetMapping(value = "/products/{productid}")
    public ProductResponse getProduct(@RequestBody AddProductRequest request, Long productid){
        log.info("get product::{}[{}]",request,productid);
        return productService.getProduct(request,productid);
    }

    @PatchMapping(value = "/products/{productid}/rent")
    public ProductResponse rentProduct (@RequestBody  AddProductRequest request, Long productid){
        log.info("rent product::{}[{}]",request,productid);
        return productService.rentProduct(request,productid);
    }

    @PatchMapping(value = "/products/{productid}/donate")
    public ProductResponse donateProduct (@RequestBody AddProductRequest request, Long productid){
        log.info("donate product::{}[{}]",request,productid);
        return productService.donateProduct(request,productid);
    }

    @GetMapping()
    public List<ProductResponse> findRentedProduct (){
        log.info("get all rented product");
        return productRepository.findRentedProduct();

    }

    public List<ProductResponse> findDonatedProduct(){
        log.info("get all donated product");
        return productService.findDonatedProduct();
    }

    public List<ProductResponse> getALlProduct(@RequestBody AddProductRequest request){
        log.info("get all product");
        return productService.getALlProduct();
    }

}
