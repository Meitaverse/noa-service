package me.bitsoul.noa.exception;

import me.bitsoul.noa.constant.HeadConstants;

/**
 * @author lxbang
 * @create 2022/12/5 12:29 下午
 */
public class InvalidTokenException extends BusinessException {
    public InvalidTokenException() {
        super(HeadConstants.INVALID_TOKEN_CODE, HeadConstants.INVALID_TOKEN_MSG);
    }
}
