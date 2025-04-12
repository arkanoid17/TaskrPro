package com.arka.taskrpro.service;


import com.arka.taskrpro.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JwtService {

    private Key getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(Constants.secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String username,String tenantId){
        Map<String,Object> claims = new HashMap<>();
        claims.put("tenant-id",tenantId);
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ Constants.expirationTime))
                .signWith(SignatureAlgorithm.HS256,getKey())
                .compact();
    }

    public boolean validateToken(String token,String userName){
        String extractedUserName = extractUserName(token);
        return extractedUserName.equals(userName) && !isTokenExpired(token);
    }


    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims,T> claimResolver){
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getKey())
                .build().parseClaimsJws(token).getBody();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

}
