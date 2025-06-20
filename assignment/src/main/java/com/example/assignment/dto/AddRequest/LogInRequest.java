package com.example.assignment.dto.AddRequest;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class LogInRequest {

    private String name;
    private String password;

}
