package com.example.assignment.service.impl;

import com.example.assignment.dto.AddRequest.AddOwnerRequest;
import com.example.assignment.dto.AddRequest.LogInRequest;
import com.example.assignment.dto.Response.AuthenticationResponse;
import com.example.assignment.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AuthenticateImpl implements AuthenticationService {
    @Override
    public AuthenticationResponse register(AddOwnerRequest request) {

        return null;
    }

    @Override
    public AuthenticationResponse authenticate(LogInRequest request) {
        return null;
    }
}
