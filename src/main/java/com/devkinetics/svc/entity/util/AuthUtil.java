package com.devkinetics.svc.entity.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
public class AuthUtil {

    @Value("${jwt.auth.secretKey}")
    private String JWT_AUTH_SECRET_KEY;

    // ----------------------------------------------------------------------
    // Create update delete section
    // ----------------------------------------------------------------------

    // ----------------------------------------------------------------------
    // Data retrieval section
    // ----------------------------------------------------------------------

//    /**
//     * @param tokenRequest
//     * @return String
//     */
//    public String generateAuthToken(TokenRequest tokenRequest) {
//
//        Date expiryDate = new Date(System.currentTimeMillis() + ConstantUtil.DEFAULT_AUTH_EXPIRATION);
//
//        return Jwts.builder()
//                .setId(tokenRequest.getId())
//                .setExpiration(expiryDate)
//                .compressWith(CompressionCodecs.GZIP)
//                .signWith(SignatureAlgorithm.HS256, JWT_AUTH_SECRET_KEY)
//                .compact();
//    }

//    /**
//     * @param authToken
//     * @return Long
//     */
//    public Long getCurrentUserIdFromAuthToken(String authToken) {
//
//        Long currentUserId = ConstantUtil.DEFAULT_CURRENT_USER_ID;
//
//        // Verify Auth Token and get the id
//        String id = this.verifyAuthToken(authToken);
//
//        if (id != null) {
//            currentUserId = Long.valueOf(id);
//        }
//
//        return currentUserId;
//    }

    /**
     * @param authorizationHeader
     * @return Long
     */
    public Long getCurrentUserIdFromAuthorizationHeader(String authorizationHeader) {

        Long currentUserId = ConstantUtil.DEFAULT_CURRENT_USER_ID;

        if (authorizationHeader == null) {
            return currentUserId;
        }

        String[] splittedAuthorization = authorizationHeader.split(ConstantUtil.AUTHORIZATION_HEADER_PREFIX);

        if (splittedAuthorization.length <= 1) {
            return currentUserId;
        }

        String token = splittedAuthorization[1];
        String id = new AuthUtil().verifyAuthToken(token);

        if (id != null) {
            currentUserId = Long.valueOf(id);
        }

        return currentUserId;
    }

    // ----------------------------------------------------------------------
    // Module specific section
    // ----------------------------------------------------------------------

    // ----------------------------------------------------------------------
    // Generic method section
    // ----------------------------------------------------------------------

    /**
     * @param token
     * @return String
     */
    public String verifyAuthToken(String token) {

        // token must not be null
        if (token == null) {
            return null;
        }

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(JWT_AUTH_SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();

            return claims.getId();
        } catch (Exception ex) {
            log.error("Error verifying auth token | {} | token : {}", ex.getMessage(), token);
            return null;
        }
    }

}
