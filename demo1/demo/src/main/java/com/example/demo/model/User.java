package com.example.demo.model;

import com.sun.tracing.dtrace.ArgsAttributes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {

    private  int id;
    private String username;
    private String password;

}
