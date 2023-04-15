package com.kamil.servicExpert.security.jwt;

import java.util.Date;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import com.kamil.servicExpert.security.service.UserDetailsImpl;

import io.jsonwebtoken.*;

@Component
public class JwtUtils {
	
	private static final Logger logg = LoggerFactory.getLogger(JwtUtils.class);

	@Value("${servicExpert.app.jwtSecret}")
	private String jwtSecret;

	@Value("${servicExpert.app.jwtExpirationMs}")
	private int jwtExpirationMs;

	@Value("${servicExpert.app.jwtCookieName}")
	private String jwtCookie;

	public String getJwtFromCookies(HttpServletRequest httpServletRequest) {
		Cookie cookie = WebUtils.getCookie(httpServletRequest, jwtCookie);
		if (cookie != null) {
			return cookie.getValue();
		} else {
			return null;
		}
	}

	public ResponseCookie generateJwtCookie(UserDetailsImpl userDetails) {
		String jwt = generateTokenFromUsername(userDetails.getUsername());
		return ResponseCookie.from(jwtCookie, jwt).path("/api").maxAge(24 * 60 * 60).httpOnly(true)
				.build();
	}

	public ResponseCookie getCleanJwtCookie() {
		return ResponseCookie.from(jwtCookie, null).path("/api").build();
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException error) {
			logg.error("Catching error: Invalid JWT signature: {}", error.getMessage());
		} catch (MalformedJwtException error) {
			logg.error("Catching error: Invalid JWT token: {}", error.getMessage());
		} catch (ExpiredJwtException error) {
			logg.error("Catching error: JWT token is expired: {}", error.getMessage());
		} catch (UnsupportedJwtException error) {
			logg.error("Catching error: JWT token is unsupported: {}", error.getMessage());
		} catch (IllegalArgumentException error) {
			logg.error("Catching error: JWT claims string is empty: {}", error.getMessage());
		}

		return false;
	}

	public String generateTokenFromUsername(String username) {
		return Jwts.builder().setSubject(username).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}
	
}
