package com.arka.taskrpro.service.impl;


import com.arka.taskrpro.Constants;
import com.arka.taskrpro.exceptions.TokenException;
import com.arka.taskrpro.models.entity.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private Key getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(Constants.secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(Long id, String username, String tenantId, Role role){

        Map<String,Object> claims = new HashMap<>();
        claims.put("tenant-id",tenantId);
        claims.put("user_id",id);
        claims.put("user_email",username);
        claims.put("role",role);

        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ Constants.expirationTime))
                .signWith(SignatureAlgorithm.HS256,getKey())
                .compact();
    }

    public boolean validateToken(String token, UserDetails userDetails){
        boolean expired = isTokenExpired(token);

        if(expired){
            throw new TokenException("Token has expired");
        }

        String extractedUserName = extractUserName(token);
        return extractedUserName.equals(userDetails.getUsername());
    }

    public Role extractRole(String token){
        Claims claims = extractAllClaims(token);
        String roleStr =  claims.get("role", String.class);

        try {
            return Role.valueOf(roleStr); // Convert string to enum
        } catch (IllegalArgumentException e) {
            throw new TokenException("Invalid role in token: " + roleStr);
        }
    }

    public long extractUserId(String token){
        Claims claims = extractAllClaims(token);
        return claims.get("user_id", Long.class);
    }

    public String extractTenantId(String token){
        Claims claims = extractAllClaims(token);
        return claims.get("tenant-id", String.class);
    }

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimResolver){
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
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
