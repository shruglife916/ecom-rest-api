package com.cogent.ecom.rest.api.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
@Component
public class JwtTokenProvider {
    private String jwtSecret = "b14ecf80c8cb190239ef9a86dc2ff0eb333834d5bcad7c323d6e9173c9bdcf9e";
    private Long jwtExpirationDate = 604800L;

    // generate jwt token
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);
        String token = Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(expireDate)
                .signWith(key())
                .compact();
        return token;
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    // get username from jwt token
    public String getUsername(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    // validate jwt token
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith((SecretKey) key())
                    .build()
                    .parse(token);
            return true;
        } catch (MalformedJwtException exception) {
            throw new RuntimeException("invalid JWT token");
        } catch (ExpiredJwtException exception) {
            throw new RuntimeException("expired JWT token");
        } catch (UnsupportedJwtException exception) {
            throw new RuntimeException("unsupported JWT token");
        } catch (IllegalArgumentException exception) {
            throw new RuntimeException("JWT claims string is null or empty");
        }
    }
}
