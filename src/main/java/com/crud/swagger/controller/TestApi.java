package com.crud.swagger.controller;


import com.crud.swagger.model.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;

@RestController
@RequestMapping("/test")
public class TestApi {

    @GetMapping("/message")
    public String display(ServletRequest req){
        return "Welcome to Swagger TestApi class";
    }

}
