package com.example.assignment.service.impl;

import com.example.assignment.dto.AddRequest.AddOwnerRequest;
import com.example.assignment.dto.Message;
import com.example.assignment.dto.Response.OwnerResponse;
import com.example.assignment.dto.Response.ProductResponse;
import com.example.assignment.entity.Owner;
import com.example.assignment.repository.OwnerRepository;
import com.example.assignment.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    public OwnerImpl(OwnerRepository ownerRepository){
        this.ownerRepository = ownerRepository;
    }


    @Override
    public OwnerResponse addowner(AddOwnerRequest request) {
        Owner owner = new Owner();
        owner.setName(request.getName());
        owner.setEmail(request.getEmail());
        Owner save = ownerRepository.save(owner);
        return prepareOwnerResponse(save);
    }

    private OwnerResponse prepareOwnerResponse(Owner save) {
        return OwnerResponse.builder()
                .name(save.getName())
                .email(save.getEmail())
                .build();
    }
}
