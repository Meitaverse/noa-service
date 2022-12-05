package me.bitsoul.noa.exception;

import me.bitsoul.noa.constant.AuthConstant;

/**
 * @author lxbang
 * @create 2022/12/5 9:46 上午
 */
public class InvalidTokenException extends BusinessException {

    public InvalidTokenException() {
        super(AuthConstant.RESP_CODE_INVALID_TOKEN, "无效的凭证");
    }
}
