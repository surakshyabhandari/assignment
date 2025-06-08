package com.example.assignment.controller;

import com.example.assignment.dto.AddRequest.AddOwnerRequest;
import com.example.assignment.dto.Response.OwnerResponse;
import com.example.assignment.service.OwnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OwnerController {

    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService){
        this.ownerService = ownerService;
    }

    @PostMapping(value = "/owner")
    public OwnerResponse addOwner(AddOwnerRequest request){
        log.info("add owner :: {}");
        return ownerService.addowner(request);
    }



}
