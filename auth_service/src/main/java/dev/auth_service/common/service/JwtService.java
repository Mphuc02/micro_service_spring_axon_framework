package dev.auth_service.common.service;

import dev.auth_service.common.constranst.Const.*;
import dev.auth_service.common.entity.User;
import dev.common_service.model.UserCommon;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

@Service
public class JwtService {
    @Value(JWT.SECRET_KEY)
    private String secretKey;

    @Value(JWT.EXPIRATION)
    private long jwtExpiration;

    @Value(JWT.REFRESH_TOKEN_EXPIRATION)
    private long refreshExpiration;

    public String extractID(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(
            User user
    ) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("PROVIDER", user.getProvider());
        return buildToken(extraClaims, user.getId(), jwtExpiration);
    }

    public String generateRefreshToken(
            UUID id
    ) {
        return buildToken(new HashMap<>(), id, refreshExpiration);
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            UUID id,
            long expiration
    ) {
        return Jwts
                .builder()
                .id(UUID.randomUUID().toString())
                .setClaims(extraClaims)
                .setSubject(id.toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, User user) {
        final String id = extractID(token);
        //Todo: Check fullName in token is correct
        return (id.equals(user.getId().toString())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}