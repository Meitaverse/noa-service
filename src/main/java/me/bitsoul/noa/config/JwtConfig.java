package me.bitsoul.noa.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.UnsupportedEncodingException;

/**
 * @author lxbang
 * @create 2022/12/1 3:57 下午
 */
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {

    private String secret;
    private Long expirationSecond;

    @Bean
    public JWTVerifier jwtVerifier() throws UnsupportedEncodingException {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.require(algorithm).build();
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Long getExpirationSecond() {
        return expirationSecond;
    }

    public void setExpirationSecond(Long expirationSecond) {
        this.expirationSecond = expirationSecond;
    }
}
