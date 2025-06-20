package com.example.assignment.service;

import com.example.assignment.dto.AddRequest.AddOwnerRequest;
import com.example.assignment.dto.AddRequest.LogInRequest;
import com.example.assignment.dto.Response.AuthenticationResponse;

public interface AuthenticationService {

    AuthenticationResponse register (AddOwnerRequest request);
    AuthenticationResponse authenticate (LogInRequest request);
}
