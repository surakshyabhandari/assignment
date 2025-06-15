package com.example.assignment.controller;

import com.example.assignment.dto.AddRequest.AddOfferRequest;
import com.example.assignment.dto.AddRequest.AddProductRequest;
import com.example.assignment.dto.Response.OfferResponse;
import com.example.assignment.entity.Offer;
import com.example.assignment.repository.OfferRepository;
import com.example.assignment.service.OfferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class OfferController {

    private final OfferService offerService;
    private final OfferRepository offerRepository;

    public OfferController(OfferService offerService, OfferRepository offerRepository){
        this.offerService = offerService;
        this.offerRepository = offerRepository;

    }

    @PostMapping(value = "/offers")
    public OfferResponse addOffer(@ModelAttribute AddOfferRequest request){
        log.info("add offer::{}", request);
        return offerService.addOffer(request);
    }

    @PutMapping(value = "/offers/{offerid}")
    public OfferResponse updateOffer(@RequestBody AddOfferRequest request, @PathVariable Long offerid){
        log.info("update offer::{}[{}]",request,offerid);
        return offerService.updateOffer(request,offerid);
    }

    @GetMapping(value = "offer/{offerid}")
    public OfferResponse getOffer( @PathVariable Long offerid){
        log.info("get offer::[{}]",offerid);
        return offerService.getOffer(offerid);
    }

    @GetMapping(value = "/offers")
    public List<OfferResponse> getAllOffer(){
        log.info("get all offers");
        return offerService.getAllOffer();
    }

    @PatchMapping(value = "offers/{offerid}/products/{productid}")
    public OfferResponse addProduct(@PathVariable Long offerid, @PathVariable Long productid){
        log.info("add product in offer::[{}][{}]",offerid,productid);
        OfferResponse updateOffer = offerService.addProduct(offerid,productid);
        return updateOffer;
    }

    @DeleteMapping(value = "offers/{offerid}/products/{productid}")
    public OfferResponse deleteProduct(@PathVariable Long offerid, @PathVariable Long productid){
        log.info("delete product in offer::[{}][{}]",offerid,productid);
        return offerService.deleteProduct(offerid,productid);
    }



}
