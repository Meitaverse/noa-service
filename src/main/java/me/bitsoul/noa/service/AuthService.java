package me.bitsoul.noa.service;

import me.bitsoul.noa.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.web3j.crypto.Sign;

/**
 * @author lxbang
 * @create 2022/12/1 5:59 下午
 */
@Component
public class AuthService {

    @Autowired
    private JwtUtils jwtUtils;

    /**
     * 注册登录
     * @param wallerAddress
     * @param signed
     */
    public void signIn(String wallerAddress,String signed){

    }

}
