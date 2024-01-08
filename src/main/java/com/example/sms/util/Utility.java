package com.example.sms.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.Cookie;
import java.util.Date;

public class Utility {
    private static final String SECRET_KEY = "wuBPN0CxFjtirb0g2o9AU98fUXIbWh6f";
    private static final long EXPIRATION_TIME = 24 * 60 * 60 * 1000;

    public static String generateToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public static String extractUsername(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    public static Cookie getCookies(String authToken) {
        return new Cookie(Constants.AUTH_TOKEN, authToken);
    }

    public static String extractUsernameFromCookies(Cookie[] cookies) {
        String authToken = getAuthTokenFromCookies(cookies);
        return extractUsername(authToken);
    }

    public static String getAuthTokenFromCookies(Cookie[] cookies) {
        String authToken = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (Constants.AUTH_TOKEN.equals(cookie.getName())) {
                    authToken = cookie.getValue();
                    break;
                }
            }
        }
        return authToken;
    }
}

