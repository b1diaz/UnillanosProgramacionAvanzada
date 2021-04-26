package com.example.utils;


import com.example.entities.user;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class TokenUtils {
    public String getToken(user user)
    {
        String secret="thisissecret";
        String token=Jwts.builder()
                .setId("directory")
                .setSubject(user.getName())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+600000000))
                .claim("company", "unodos")
                .signWith(SignatureAlgorithm.HS256, secret.getBytes()).compact();
        return token;
    }
}

