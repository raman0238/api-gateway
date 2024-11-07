package com.example.apigateway.apiclient;

import com.example.apigateway.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "UserServiceClient", url = "http://localhost:8080/user")
public interface UserServiceClient {
    @PostMapping(value = "/login",produces = "application/json", consumes = "application/json")
    public String userLogin(@RequestBody User user, @RequestHeader("Authorization") HttpHeaders authHeader);
}
