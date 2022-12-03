package me.bitsoul.noa.service;

import me.bitsoul.noa.constant.AuthConstant;
import me.bitsoul.noa.dto.UserDTO;
import me.bitsoul.noa.dto.jwt.JwtDTO;
import me.bitsoul.noa.exception.BusinessException;
import me.bitsoul.noa.util.JwtUtils;
import me.bitsoul.noa.util.MetaMaskUtils;
import me.bitsoul.noa.vo.resp.SignInResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author lxbang
 * @create 2022/12/1 5:59 下午
 */
@Component
public class AuthService {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtils jwtUtils;

    /**
     * 注册登录
     * @param wallerAddress
     * @param signed
     */
    public SignInResp signIn(String wallerAddress, String signed){
        // 验签
        validSignIn(signed,wallerAddress);
        // 用户信息（自动创建）
        UserDTO userDTO = userService.getUserWithCreate(wallerAddress);
        if (Objects.isNull(userDTO)){
            throw new BusinessException(500,"获取用户失败");
        }
        // 签发jwt
        String jwt = generateJwt(userDTO);
        // 返回数据
        SignInResp resp = new SignInResp();
        resp.setJwt(jwt);
        return resp;
    }

    /**
     * 签发jwt
     * @param userDTO
     * @return
     */
    private String generateJwt(UserDTO userDTO){
        Map<String,String> claimMap = new HashMap<>();
        claimMap.put(AuthConstant.JWT_FIELD_USER_ID,userDTO.getUserId().toString());
        claimMap.put(AuthConstant.JWT_FIELD_WALLET_ADDRESS,userDTO.getWalletAddress());
        JwtDTO jwtDTO = jwtUtils.generateJwt(claimMap, null);
        return jwtDTO.getJwt();
    }

    /**
     * 有效的登录/注册
     * @param signature
     * @param walletAddress
     * @return
     */
    public void validSignIn(String signature, String walletAddress){
        final String message = "singIn";
        boolean validate = false;
        try {
            validate = MetaMaskUtils.validate(signature, message, walletAddress);
        } catch (Exception ignored){}
        if (!validate){
            throw new BusinessException(400,"无效的签名");
        }
    }
}
