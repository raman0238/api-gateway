package com.example.apigateway.config;

import com.example.apigateway.apiclient.UserService;
import com.example.apigateway.apiclient.UserServiceClient;
import com.example.apigateway.model.User;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.support.AbstractConfigurable;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class GatewayFilter extends AbstractGatewayFilterFactory<GatewayFilter.Config> {

    @Autowired
    UserService userService;
    @Autowired
    RouteValidator routeValidator;
    @Autowired
    JwtService jwtService;

    @Autowired
    RestTemplate restTemplate;


    public GatewayFilter() {
        super(Config.class);
    }

    @Override
    public org.springframework.cloud.gateway.filter.GatewayFilter apply(Config config) {
        return (((exchange, chain) -> {

            if(routeValidator.isSecured.test(exchange.getRequest()) && exchange.getRequest().getHeaders().containsKey("Authorization")) {
                //validate if toke is valid
                List<String> authorization = exchange.getRequest().getHeaders().get("Authorization");
                String token = authorization.get(0).startsWith("Bearer") ? authorization.get(0).substring(7) : "";
//                jwtService.isTokenValid(token);
                String url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/user/validateToken")
                        .queryParam("token", token)  // Add the token as a query parameter
                        .toUriString();

                // Send the request (POST or GET depending on the server's design)
                ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, null, String.class);

            }
            return chain.filter(exchange);
        }));

    }
    public static class Config{
    }
}

