package com.highgroup.highgroup.config.jwt;

import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import java.util.function.Function;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Slf4j
@Component
public class JwtTokenUtil {

  @Value("${jwt.signingkey}")
  private String SIGNING_KEY;

  private int ACCESS_TOKEN_VALIDITY_SECONDS = 24 * 60 * 60;

  public String getUserIdFromToken(String token) {
    Claims claims = getClaimFromToken(token);
    return claims.get("user_id").toString();
  }
  // user_id정보 얻기
  
 
  private Date getExpirationDateFromToken(String token) {
    return getClaimFromToken(token, Claims::getExpiration);
  }

  private Claims getClaimFromToken(String token) {
    final Claims claims = getAllClaimsFromToken(token);

    return claims;
  }

  private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = getAllClaimsFromToken(token);
    return claimsResolver.apply(claims);
  }

  private Claims getAllClaimsFromToken(String token) {
    return Jwts.parser().setSigningKey(SIGNING_KEY).parseClaimsJws(token).getBody();
  }

  private Boolean isTokenExpired(String token) {
    final Date expiration = getExpirationDateFromToken(token);
    if (expiration == null) {
      return false;
    }
    return expiration.before(new Date());
  }

  public String generateTokenForAdmin(Map<String, Object> param){
    return doGenerateTokenForAdmin(param, "operator");
  }
  public String generateTokenForUser(Map<String, Object> param){
    return doGenerateTokenForAdmin(param, "android");
  }
  private String doGenerateTokenForAdmin(Map<String, Object> param, String subject) {
    Claims claims = Jwts.claims();
    claims.put("user_id",param.get("user_id"));
    claims.put("type", subject);
    claims.put("scopes", Arrays.asList(new SimpleGrantedAuthority("ROLE_SELLER")));

    return Jwts.builder().setSubject(subject).setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis()))
        .signWith(SignatureAlgorithm.HS512, SIGNING_KEY)
        .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS * 1000)).compact();
  }
  // admin, user 따로 만들어야 할 거 같음.

   /**
   * 토큰 유효성체크, 토큰값에 userid가 존재하는지 여부만 체크한다. 만료기한 x
   * 
   * @param token
   * @return
   */
  public Boolean validateToken(String token) {
    final String userId = getUserIdFromToken(token);
    // final boolean isToken = !isTokenExpired(token);
    return !userId.isEmpty();
  }

  public Boolean validateTokenForAdmin(String token) {
    final String userId = getUserIdFromToken(token);
    return (!userId.isEmpty() && !isTokenExpired(token));
  }



  public boolean validateToken2(String jwt) {
    return getClaims(jwt) != null;
  }

  private Jws<Claims> getClaims(String jwt) {
      try {
          return Jwts.parser().setSigningKey(SIGNING_KEY).parseClaimsJws(jwt);
      } catch (Exception ex) {
          log.error("Invalid JWT signature");
          throw ex;
      }
  }

  
}
