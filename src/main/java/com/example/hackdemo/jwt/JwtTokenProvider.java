//package com.example.hackdemo.jwt;
//
//import com.example.hackdemo.model.User;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//@Component
//public class JwtTokenProvider {
//
//    private final String JWT_SECRET = "your_jwt_secret_key"; // 설정에서 관리할 것을 권장
//    private final long JWT_EXPIRATION_MS = 86400000L; // 1일
//
//    public String generateToken(User user) {
//        Date now = new Date();
//        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION_MS);
//
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("id", user.getId());
//        claims.put("email", user.getEmail());
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(user.getId().toString())
//                .setIssuedAt(now)
//                .setExpiration(expiryDate)
//                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
//                .compact();
//    }
//
//    public Claims extractClaims(String token) {
//        return Jwts.parser()
//                .setSigningKey(JWT_SECRET)
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    public boolean validateToken(String token) {
//        try {
//            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//}
