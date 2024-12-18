package dux.AppDux.util;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private final SecretKey _TOKEN = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
	public String generarToken(String usuario) {
		
		return Jwts.builder().setSubject(usuario).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
				.signWith(SignatureAlgorithm.HS256, _TOKEN).compact();

	}
	
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(_TOKEN).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(_TOKEN).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
    
}
