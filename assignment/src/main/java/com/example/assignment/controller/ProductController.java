package com.example.assignment.controller;

import com.example.assignment.dto.AddRequest.AddProductRequest;
import com.example.assignment.dto.Response.ProductResponse;
import com.example.assignment.entity.Product;
import com.example.assignment.entity.Status;
import com.example.assignment.repository.ProductRepository;
import com.example.assignment.service.ProductService;
import com.example.assignment.service.impl.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;
    private final ImageService imageService;

    @Autowired
    public ProductController(ProductService productService, ProductRepository productRepository, ImageService imageService){
        this.productService = productService;
        this.productRepository = productRepository;
        this.imageService = imageService;

    }

    @PostMapping(value="/products")
    public ProductResponse addProduct(@ModelAttribute AddProductRequest request, @RequestParam("file")MultipartFile file) throws Exception {
        String url = imageService.uploadImage(file);
        request.setImageurl(url);
        log.info("add product::{}",request);
        return productService.addProduct(request);
    }

    @PutMapping(value="/products/{productid}")
    public ProductResponse updateProduct(@RequestBody AddProductRequest request, @PathVariable Long productid){
        log.info("update product::{}[{}]", request,productid);
        return productService.updateProduct(request,productid);
    }

    @GetMapping(value = "/product/{productid}")
    public ProductResponse getProduct(@PathVariable Long productid){
        log.info("get product::[{}]",productid);
        return productService.getProduct(productid);
    }

    @PatchMapping(value = "/products/{productid}/rent")
    public ProductResponse rentProduct (@PathVariable Long productid){
        log.info("rent product::[{}]",productid);
        return productService.rentProduct(productid);
    }

    @PatchMapping(value = "/products/{productid}/donate")
    public ProductResponse donateProduct (@PathVariable Long productid){
        log.info("donate product::[{}]",productid);
        return productService.donateProduct(productid);
    }

    @GetMapping(value="/products/rented")
    public List<ProductResponse> findRentedProduct (){
        log.info("get all rented product");
//        List<ProductResponse> rentedProduct = productRepository.findRentedProduct();
//        return rentedProduct;

        return productService.findRentedProduct();
    }

    @GetMapping(value="/products/donated")
    public List<ProductResponse> findDonatedProduct(){
        log.info("get all donated product");
        return productService.findDonatedProduct();
    }

    @GetMapping(value = "/getProducts")
    public Page<Product> getALlProduct(@RequestParam String status, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "4") int size){
//        log.info("get all product");
        Status products = Status.valueOf(status.toUpperCase());
//        List<Product> products = productRepository.findByStatus(enumStas);
        return productService.getAllProduct(products,page,size);
    }

}
