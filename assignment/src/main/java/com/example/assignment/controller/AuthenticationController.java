package com.example.assignment.controller;

import com.example.assignment.dto.AddRequest.AddOwnerRequest;
import com.example.assignment.dto.AddRequest.LogInRequest;
import com.example.assignment.dto.Response.AuthenticationResponse;
import com.example.assignment.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<AuthenticationResponse> register (@ModelAttribute AddOwnerRequest request){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<AuthenticationResponse> authenticate (@RequestBody LogInRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }


}
