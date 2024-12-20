package com.example.productsShopping.security;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.Signature;
import java.util.Date;

@Component
@RequiredArgsConstructor

public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private  String jwtsercet;

    @Value("86400000")
    private int jwtExpirationInMs;

    private Key getSigningKey(){return Keys.hmacShakeyFor(jwtsercet.getBytes());}

    public  String generateToken(Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime()) + jwtExpirationInMs);

        return  Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .SetExpiration(expiryDate)
                .sihnWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    public  String getUsernameFrom(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigninKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return  claims.getSubject();
    }

    public boolean validateToken(String authToken){
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(authToken);
            return true;
        } catch (SecurityException ex){

        }catch (MalformedJwtException ex){

        }catch (ExceptionJwtException ex){

        }catch (UnsupportedJwtException ex){

        }catch (IllegalArgumentException ex){

        }
        return false;
    }
}
