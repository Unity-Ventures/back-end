package lk.mydentist.api.util;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lk.mydentist.api.dto.EmployeeDto;
import lk.mydentist.api.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JWTTokenGenerator {
    private static final Logger logger = LoggerFactory.getLogger(JWTTokenGenerator.class);

    @Value("${unityventures.app.jwtSecret}")
    private String jwtSecret;

    @Value("${unityventures.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    private final EmployeeService employeeService;

    @Autowired
    public JWTTokenGenerator(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public String generateToken(EmployeeDto dto) {
    return Jwts.builder()
            .setId(String.valueOf(dto.getEmployeeId()))
            .setSubject(dto.getUserName())
            .setIssuedAt(new Date())
            .setExpiration(new Date(new Date().getTime()+jwtExpirationMs))
            .signWith(key(), SignatureAlgorithm.HS256)
            .compact();
    }

    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public boolean validateToken(String token){
        try {
            String substring = token.substring("Bearer".length());
            Jwts.parserBuilder().setSigningKey(key()).build().parse(substring);
            return true;
        }catch (Exception e){
            logger.error("Invalid JWT token: {}", e.getMessage());
        }
        return false;
    }

    public EmployeeDto getEmployeeFromToken(String token){
        String substring = token.substring("Bearer".length());
        String id = Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(substring).getBody().getId();
        return employeeService.getEmployeeById(id);
    }
}
