package me.bitsoul.noa.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import me.bitsoul.noa.config.JwtConfig;
import me.bitsoul.noa.constant.AuthConstant;
import me.bitsoul.noa.dto.jwt.JwtDTO;
import me.bitsoul.noa.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author lxbang
 * @create 2022/12/1 4:02 下午
 */
@Component
public class JwtUtils {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private static final String ISSUER = "noa-service";

    @Autowired
    private JwtConfig jwtConfig;
    @Autowired
    private JWTVerifier jwtVerifier;

    /**
     * 签发jwt
     * @param claimMap
     * @param customValidSecond 自定义的有效时长（单位：秒）；传null，则取默认值
     * @return
     */
    public JwtDTO generateJwt(Map<String,String> claimMap, Long customValidSecond) {
        try {
            // 设置：密钥
            Algorithm algorithm = Algorithm.HMAC256(jwtConfig.getSecret());
            // 设置：jwt携带的数据
            JWTCreator.Builder builder = JWT.create().withIssuer(ISSUER);
            Set<String> keySet = claimMap.keySet();
            for (String key:keySet){
                String value = claimMap.get(key);
                builder.withClaim(key,value);
            }
            // 设置：过期时间
            long validPeriodSecond = Objects.nonNull(customValidSecond) ? customValidSecond : jwtConfig.getExpirationSecond();
            builder.withExpiresAt(new Date((System.currentTimeMillis() + (validPeriodSecond * 1000))));
            String jwt = builder.sign(algorithm);
            //
            JwtDTO result = new JwtDTO();
            result.setJwt(jwt);
            result.setValidPeriodSecond(validPeriodSecond);
            return result;
        } catch (Exception e) {
            log.error("生成jwt失败。{}",e.getMessage(),e);
            throw new BusinessException(500,"生成jwt失败");
        }
    }

    /**
     * 解析jwt
     * @param jwt
     * @return
     */
    public Map<String,String> parseJwt(String jwt){
        try {
            Map<String,String> result = new HashMap<>();
            DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
            Map<String, Claim> claimMap = decodedJWT.getClaims();
            for (String key : claimMap.keySet()){
                result.put(key,claimMap.get(key).asString());
            }
            return result;
        } catch (JWTVerificationException e) {
            throw new BusinessException(AuthConstant.RESP_CODE_INVALID_TOKEN,"无效的凭证");
        }
    }

}
