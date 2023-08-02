package com.example.todo.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    public static final String secret = "BEGINRSAPRIVATEKEYMIICXgIBAAKBgQCtrKVnwse4anfX+JzM7imShXZUC+QBXQ11A5bOWwHFkXc4nTfEOr3fJjnRSU5A3IROFU/pVVNiXJNkl7qQZK5mYb8j3NgqX8zZJG7IwLJ/Pm2sRW5Qj32C/uJum64Q/iEIsCg/mJjDLh1lylEMEuzKgTdWtoeLfxDBL2AJ20qXzQIDAQABAoGBAKNXi0GpmjnCOPDxLFg5bvQVfhLSFCGMKQny1DVEtsfgZmbixv5R2R41T4+dCHJMdEsUFFJ6I7CRLTcg1SDU8IhcAWCBRSNeVuomCHlQG16ti8HxwhiwIcjvDz/zNC2sL5ZJ2eJnhbtXLdf6pxxO1pA5vLp1AX06IaETO977XvupAkEA+ZgtGZybyUkftEA3ekXc5eLoW+zgU0C1fATWcIZ8Iq5YV1BW+3oAzf8HgIbkQh4LM2qa6An3l+vWNXR4wICHkwJBALIhrcdJqKw36qiyenq+m78klp5SnurQifVt0Sy1GMWyOUqYz5jKt9sGo9Qn6GDuYe/XGXKWQW25PkEYXxxPPx8CQQCpICyvRidp5VrOURVGjUB5pZ+9am02/In9V2nXJcnH1kuWHqJSFQGmlEEJHl5dTu5YEMyWnupezzd/UUThbDZxAkAzTNO5QxNalbf04YG4e9Bq2eSur+iog2pXzkqhb3404UDypNOUkz0jzOO9o8ieschuxCnGAFPTf7fYE2bAxmnNAkEA0/3bdsvJclquypqP9CQeQnxGwQtWz6+yn07gj3U1V19mdeKCUZWklRarrcr67u9DdEx+JowyEY/ppzgeQtW01g==ENDRSAPRIVATEKEY";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() * 1000 * 60 * 30))
                .signWith(getSignKey(), SignatureAlgorithm.HS512).compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
