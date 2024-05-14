package dev.auth_service.common.service;

import dev.auth_service.common.constranst.Const.*;
import dev.auth_service.common.entity.User;
import dev.auth_service.common.repository.UserRepository;
import dev.common_service.service.RedisService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtService {
    @Value(JWT.SECRET_KEY)
    private String secretKey;

    @Value(JWT.EXPIRATION)
    private long jwtExpiration;

    @Value(JWT.REFRESH_TOKEN_EXPIRATION)
    private long refreshExpiration;

    private final UserRepository userRepository;
    private final RedisService redisService;

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
        extraClaims.put("fullName", user.getFullName());
        extraClaims.put("provider", user.getProvider());
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

    public User isTokenValid(String token){
        UUID userId;
        try{
            userId = UUID.fromString(extractID(token));
        }catch (MalformedJwtException | ExpiredJwtException e){
            log.error("Jwt exception", e);
            return null;
        }

        User user = (User) redisService.get(userId.toString());
        if(user == null){
            user = userRepository.findById(userId).orElse(null);
            if(user != null)
                redisService.save(user.getId().toString(), user);
        }

        if(user != null){
            Claims claims = extractAllClaims(token);
            String fullName = claims.get("fullName").toString();
            String provider = claims.get("provider").toString();

            if( !fullName.equals(user.getFullName()) ||
                !provider.equals(user.getProvider().toString())){
                return null;
            }
        }
        return user;
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