package com.app.spring_boot_and_angular.security;

import java.util.HashSet;
import java.util.Date;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;


@Component
public class JwtTokenProvider {
    private static HashSet<String> INVALIDATED_TOKENS = new HashSet<String>();
    
    private static final String SECRET_KEY = "51d9a47550ebfcb532a8d87e17627347";

    private static final long EXPIRATION_TIME = 864000000;
    
    public String generateToken(String username) {
        Date expiryDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
        
        return Jwts.builder()
            .setSubject(username)
            .setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
            .compact();
    }
    
    public boolean validateToken(String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        if (INVALIDATED_TOKENS.contains(token)) return false;

        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (SignatureException exception) {
            System.out.println("Invalid JWT signature");
        } catch (MalformedJwtException exception) {
            System.out.println("Invalid JWT token");
        } catch (ExpiredJwtException exception) {
            System.out.println("Expired JWT token");
        } catch (UnsupportedJwtException exception) {
            System.out.println("Unsupported JWT token");
        } catch (IllegalArgumentException exception) {
            System.out.println("JWT claims string is empty");
        }

        return false;
    }

    public void invalidateToken(String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        INVALIDATED_TOKENS.add(token);
    }
    
    public String getUsernameFromToken(String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
