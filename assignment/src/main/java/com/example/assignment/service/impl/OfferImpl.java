package com.example.assignment.service.impl;

import com.example.assignment.dto.AddRequest.AddOfferRequest;
import com.example.assignment.dto.AddRequest.AddProductRequest;
import com.example.assignment.dto.Response.OfferResponse;
import com.example.assignment.dto.Response.ProductResponse;
import com.example.assignment.entity.Offer;
import com.example.assignment.entity.Product;
import com.example.assignment.repository.OfferRepository;
import com.example.assignment.repository.ProductRepository;
import com.example.assignment.service.OfferService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferImpl implements OfferService {

    public final OfferRepository offerRepository;
    public final ProductRepository productRepository;

    public OfferImpl (OfferRepository offerRepository, ProductRepository productRepository){
        this.offerRepository = offerRepository;
        this.productRepository = productRepository;
    }

    @Override
    public OfferResponse addOffer(AddOfferRequest request) {
        Offer offer = new Offer();
        offer.setTitle(request.getTitle());
        offer.setDescription(request.getDescription());
        if(request.getProducts()!=null){
            List<Product> productList = new ArrayList<>();
            for (Long product : request.getProducts()){
                Product product1 = productRepository.findById(product).orElse(null);
                productList.add(product1);
            }
            offer.setProducts(productList);
        }
        Offer save = offerRepository.save(offer);
        return prepareOfferResponse(save);
    }

    @Override
    public OfferResponse updateOffer(AddOfferRequest request, Long offerid) {
        Offer offer = offerRepository.findById(offerid).orElseThrow(()->new RuntimeException("null"));

        if(request.getTitle()!=null) offer.setTitle(request.getTitle());
        if(request.getDescription()!=null) offer.setDescription(request.getDescription());
        Offer save = offerRepository.save(offer);
        return prepareOfferResponse(save);
    }

    @Override
    public OfferResponse getOffer(Long offerid) {

        Offer offer = offerRepository.findById(offerid).orElseThrow(()->new RuntimeException("null"));
        return prepareOfferResponse(offer);
    }

    @Override
    public List<OfferResponse> getAllOffer() {

        List<Offer> offerList = offerRepository.findAll();
        List<OfferResponse> offerResponsesList = new ArrayList<>();
        offerList.forEach(offer -> offerResponsesList.add(prepareOfferResponse(offer)));
        return offerResponsesList;
    }

    @Override
    public OfferResponse addProduct(Long offerid, Long productid) {

        Offer offer = offerRepository.findById(offerid).orElseThrow(()->new RuntimeException("null"));
        Product product = productRepository.findById(offerid).orElseThrow(()->new RuntimeException("null"));
        offer.addProductToOffer(product);
        Offer save = offerRepository.save(offer);
        return prepareOfferResponse(save);
    }

    @Override
    public OfferResponse deleteProduct(Long offerid, Long productid) {

        Offer offer = offerRepository.findById(offerid).orElseThrow(()->new RuntimeException("null"));
        Product product = productRepository.findById(offerid).orElseThrow(()->new RuntimeException("null"));
        offer.getProducts().remove(product);
        return null;
    }

    private OfferResponse prepareOfferResponse(Offer save) {
        return OfferResponse.builder()
                .id(save.getId())
                .title(save.getTitle())
                .description(save.getDescription())
                .products(prepareProductResponse(save))
                .build();
    }

    private List<ProductResponse> prepareProductResponse(Offer save) {
        return save.getProducts() != null ? save.getProducts().stream().map(this::prepareProductResponse).collect(Collectors.toList()) : new ArrayList<>();
    }

    private ProductResponse prepareProductResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .status(product.getStatus().name())
                .imageurl(product.getImageurl())
                .build();
    }
}
