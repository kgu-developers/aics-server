package kgu.developers.common.auth.jwt;

import static io.jsonwebtoken.Header.JWT_TYPE;
import static io.jsonwebtoken.SignatureAlgorithm.HS256;
import static javax.xml.crypto.dsig.SignatureProperties.TYPE;

import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Service
@Builder
@RequiredArgsConstructor
public class TokenProvider {
	private final JwtProperties jwtProperties;

	public String generateToken(String userId, Duration expiredAt, String role) {
		Date now = new Date();
		Date expiry = new Date(now.getTime() + expiredAt.toMillis());
		return makeToken(now, expiry, userId, role);
	}

	private String makeToken(Date now, Date expiry, String userId, String role) {
		return Jwts.builder()
			.setHeaderParam(TYPE, JWT_TYPE)
			.setIssuer(jwtProperties.getIssuer())
			.setIssuedAt(now)
			.setExpiration(expiry)
			.setSubject(userId)
			.claim("userId", userId)
			.claim("role", role)
			.signWith(HS256, jwtProperties.getSecretKey())
			.compact();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser()
				.setSigningKey(jwtProperties.getSecretKey())
				.parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Authentication getAuthentication(String token) {
		Claims claims = getClaims(token);
		String role = claims.get("role", String.class);
		Set<SimpleGrantedAuthority> authorities = getRoles(role);

		return new UsernamePasswordAuthenticationToken(
			new org.springframework.security.core.userdetails.User(
				claims.getSubject(),
				"",
				authorities
			), token, authorities
		);
	}

	public Set<SimpleGrantedAuthority> getRoles(String role) {
		if (role.equals("ADMIN")) {
			return Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		if (role.equals("SUPER")) {
			return Collections.singleton(new SimpleGrantedAuthority("ROLE_SUPER"));
		}
		return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
	}

	public String getUserId(String token) {
		Claims claims = getClaims(token);
		return claims.get("userId", String.class);
	}

	private Claims getClaims(String token) {
		return Jwts.parser()
			.setSigningKey(jwtProperties.getSecretKey())
			.parseClaimsJws(token)
			.getBody();
	}
}
