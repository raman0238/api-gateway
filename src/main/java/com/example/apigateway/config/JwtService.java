package com.example.apigateway.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private String secretKey = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhcnlhX3N0YXJrIiwiaWF0IjoxNzMxMDA1NDE2LCJleHAiOjE3MzEwMDU1MjR9.CiuBk-9ExdLpcV6mhFWijB1VASQaqCTlo-y2S7uKylM";

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
    public JwtService() throws NoSuchAlgorithmException {
//        KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
//        SecretKey sk  = keyGen.generateKey();
//        secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
    }


    public void isTokenValid(String token) {
        Jwts.parser()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
