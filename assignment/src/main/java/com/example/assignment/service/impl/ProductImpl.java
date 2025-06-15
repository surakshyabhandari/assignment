package com.example.assignment.service.impl;

import com.example.assignment.dto.AddRequest.AddProductRequest;
import com.example.assignment.dto.Response.OfferResponse;
import com.example.assignment.dto.Response.ProductResponse;
import com.example.assignment.entity.Offer;
import com.example.assignment.entity.Product;
import com.example.assignment.entity.Status;
import com.example.assignment.repository.OfferRepository;
import com.example.assignment.repository.ProductRepository;
import com.example.assignment.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductImpl implements ProductService {

    public final ProductRepository productRepository;
    public final OfferRepository offerRepository;


    @Autowired
    public ProductImpl(ProductRepository productRepository, OfferRepository offerRepository){
        this.productRepository = productRepository;
        this.offerRepository = offerRepository;

    }


    @Override
    public ProductResponse addProduct(AddProductRequest request) {

        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setStatus(validateStatus(request.getStatus()));
        product.setImageurl(request.getImageurl());
        //foreignKey
        product.setOwner(request.getOwner());
        //foreignKey
        if(request.getOffers()!=null){
            List<Offer> offerList = new ArrayList<>();
            for(Long offer : request.getOffers()){
                Offer offer1 = offerRepository.findById(offer).orElse(null);
                offerList.add(offer1);
            }
            product.setOffers(offerList);
        }
        Product save = productRepository.save(product);
        return prepareProductResponse(save);
    }

    @Override
    public ProductResponse updateProduct(AddProductRequest request, Long productid) {

        Product product = productRepository.findById(productid).orElseThrow(()->new RuntimeException("No Valid Id"));
        if(request.getName()!=null) product.setName(request.getName());
        if (request.getDescription()!=null) product.setDescription(request.getDescription());
        if(request.getStatus()!=null) product.setStatus(validateStatus(request.getStatus()));
        Product save = productRepository.save(product);
        return prepareProductResponse(save);
    }

    @Override
    public ProductResponse getProduct(Long productid) {

        Product product = productRepository.findById(productid).orElseThrow(()->new RuntimeException("No Valid Id"));
        return prepareProductResponse(product);
    }

    @Override
    public ProductResponse rentProduct(Long productid) {

        Product product = productRepository.findById(productid).orElseThrow(()->new RuntimeException("No Valid Id"));
        product.setStatus(Status.RENTED);
        return prepareProductResponse(product);
    }

    @Override
    public ProductResponse donateProduct(Long productid) {
        Product product = productRepository.findById(productid).orElseThrow(()->new RuntimeException("No Valid Id"));
        product.setStatus(Status.DONATED);

        return prepareProductResponse(product);
    }

    @Override
    public List<ProductResponse> findRentedProduct() {
        List<ProductResponse> productList = productRepository.findAll().stream().filter(product->"rented".equalsIgnoreCase(product.getStatus().name())).map(this::prepareProductResponse).collect(Collectors.toList());
        return productList;
//        for(Product product:productList){
//            List<ProductResponse> rentedProduct = new ArrayList<>();
//            String status = product.getStatus().toLowerCase();
//            if(status.equals("rented")){
////                rentedProduct.add(prepareProductResponse(product));
//                productList.forEach(product1 -> rentedProduct.add(prepareProductResponse(product)));
//                return prepareProductResponse(product);
//            }
//        }
    }

    @Override
    public List<ProductResponse> findDonatedProduct() {
        List<ProductResponse> productList = productRepository.findAll().stream().filter(product->"donated".equalsIgnoreCase(product.getStatus().name())).map(this::prepareProductResponse).collect(Collectors.toList());
        return productList;
    }


    @Override
    public Page<Product> getAllProduct(Status status, int page, int size) {
//        List<Product> productList = productRepository.findAll();
//        List<ProductResponse> productResponseList = new ArrayList<>();
//        productList.forEach(product->productResponseList.add(prepareProductResponse(product)));
//        return productResponseList;

        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findByStatus(status, pageable);
    }

    private Status validateStatus(String status) {
//        for(Status status1 : Status.values()){
//            if(status1.name().equalsIgnoreCase(status.name())){
//                return status1.name();
//            }
//
//        }
        try{
            return Status.valueOf(status.toUpperCase());
        }
        catch (IllegalArgumentException e){
            throw new RuntimeException("Please choose valid status");
        }

    }

    private ProductResponse prepareProductResponse(Product save) {
        return ProductResponse.builder()
                .id(save.getId())
                .name(save.getName())
                .description(save.getDescription())
                .status(save.getStatus().name())
                .owner(save.getOwner())
                .offers(prepareOfferResponse(save))
                .build();
    }

    private List<OfferResponse> prepareOfferResponse(Product save) {
        return save.getOffers() != null ? save.getOffers().stream().map(this::prepareOfferResponse).collect(Collectors.toList()) : new ArrayList<>();
    }

    private OfferResponse prepareOfferResponse(Offer offer){
        return OfferResponse.builder()
                .id(offer.getId())
                .title(offer.getTitle())
                .description(offer.getDescription())
                .build();
    }


}

