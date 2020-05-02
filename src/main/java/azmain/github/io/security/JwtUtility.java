package azmain.github.io.security;

import azmain.github.io.repository.schema.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtility {

    /** Generating Token */
    public String generateTokenFromUserDetails(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", userDetails.getAuthorities().stream().map(x->((GrantedAuthority) x).getAuthority()).toArray());
        return createToken(claims, userDetails.getUsername());
    }

    public String generateTokenFromUser(UserEntity userEntity){
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", userEntity.getRoles().stream().map(x->x.getRoleName()).toArray());
        claims.put("username", userEntity.getUserName());
        claims.put("email", userEntity.getEmail());
        claims.put("name", userEntity.getName());
        return createToken(claims, userEntity.getUserName());
    }

    private String createToken(Map<String,Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))
                .signWith(SignatureAlgorithm.HS256, "secretKey")
                .compact();
    }

    /** Validating Token & Extracting Claims */
    public String getUserNameFromToken(String token){
        return extractClaims(token, Claims::getSubject);
    }

    private Date getExpiration(String token){
        return extractClaims(token, Claims::getExpiration);
    }

    private Boolean isExpiredToken(String token){
        return getExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        final String userName = getUserNameFromToken(token);
        return (userName.equals(userDetails.getUsername()) && !isExpiredToken(token));
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimResolver){
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey("secretKey").parseClaimsJws(token).getBody();
    }
}
