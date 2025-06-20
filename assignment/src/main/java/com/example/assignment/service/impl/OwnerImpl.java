package com.example.assignment.service.impl;

import com.example.assignment.config.JwtService;
import com.example.assignment.dto.AddRequest.AddOwnerRequest;
import com.example.assignment.dto.Message;
import com.example.assignment.dto.Response.OwnerResponse;
import com.example.assignment.dto.Response.ProductResponse;
import com.example.assignment.entity.Owner;
import com.example.assignment.entity.Role;
import com.example.assignment.entity.Status;
import com.example.assignment.repository.OwnerRepository;
import com.example.assignment.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final PasswordEncoder passwordEncoder;

    public OwnerImpl(OwnerRepository ownerRepository, PasswordEncoder passwordEncoder){
        this.ownerRepository = ownerRepository;
        this.passwordEncoder = passwordEncoder;

    }


    @Override
    public OwnerResponse addowner(AddOwnerRequest request) {
        Owner owner = new Owner();
        owner.setName(request.getName());
        owner.setEmail(request.getEmail());
        owner.setPassword(passwordEncoder.encode(request.getPassword()));
        owner.setRole(validateRole(request.getRole()));
        Owner save = ownerRepository.save(owner);
        return prepareOwnerResponse(save);
    }
    private Role validateRole(String role){
        try{
            return Role.valueOf(role.toUpperCase());
        }
        catch (IllegalArgumentException e){
            throw new RuntimeException("Please choose valid status");
        }
    }

    private OwnerResponse prepareOwnerResponse(Owner save) {
        return OwnerResponse.builder()
                .name(save.getName())
                .email(save.getEmail())
                .build();
    }
}
