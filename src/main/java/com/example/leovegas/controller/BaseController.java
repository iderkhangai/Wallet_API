package com.example.leovegas.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Wallet APIs")
public class BaseController {

    @ApiOperation(value = "I am main page", response = String.class)
    @GetMapping("/")
    public ResponseEntity<String> getAvailability() {
        return ResponseEntity.ok("Service is up.... <br> You are at the Main page" +
                "<br><br>" +
                "API Swagger documentation is here - <a href='/swagger-ui.html#/'> Click me</a>");
    }
}
