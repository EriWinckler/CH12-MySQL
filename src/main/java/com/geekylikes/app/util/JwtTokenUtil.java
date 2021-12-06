package com.geekylikes.app.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {
    private static final long serialVersionUID = 123456L;

    @Value("${jwt.secret}")
    private String secret;

    public String getUserNameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

//    public Date getExpirationDateFromTOken(String token) {
//        return getAllClaimsFromToken(token, Claims::getExpiration);
//    }

//    public Boolean isTokenExpired(String token) {
//        final Date expiration = getExpirationDateFromToken(token);
//        return expiration.before(new Date());
//    }

    public <T> T getClaimFromToken(String token,
                                   Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJwt(token).getBody();
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

//    define the claims - define the token using HS512 algorithm secret kel
//    compact the token to a url-safe string

    public static final long JWT_TOKEN_VALIDATY = 7 * 24 * 60 * 60;

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setSubject(subject).setIssuedAt( new Date(System.currentTimeMillis()))
//                .setExpiration(new Date (System.currentTimeMillis() + JWT_TOKEN_VALIDATY * 1000))
                .signWith(SignatureAlgorithm.ES512, secret).compact();
    }

    private Boolean ValidateToken(String token, UserDetails userDetails) {
        final String username = getUserNameFromToken(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        return username.equals(userDetails.getUsername());
    }
}
