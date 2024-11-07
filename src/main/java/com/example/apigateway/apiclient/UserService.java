package com.example.apigateway.apiclient;

import com.example.apigateway.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpHeaders;

@Service
public class UserService {
    @Autowired
    @Lazy
    UserServiceClient userServiceClient;
    public void userLogin(User user, HttpHeaders httpHeaders) {
        userServiceClient.userLogin(user, httpHeaders);
    }
}
