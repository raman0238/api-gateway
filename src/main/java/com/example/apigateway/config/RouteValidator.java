package com.example.apigateway.config;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {
    List<String> authenticateRoutes = Arrays.asList(
            "/user/login","/user/register"
    );

    public Predicate<ServerHttpRequest> isSecured = serverHttpRequest ->
            authenticateRoutes.stream().noneMatch(uri -> serverHttpRequest.getURI().getPath().contains(uri));
}
