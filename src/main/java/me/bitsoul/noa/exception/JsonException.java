package me.bitsoul.noa.exception;

import me.bitsoul.noa.constant.HeadConstants;

public class JsonException extends BusinessException {
    public JsonException(String message) {
        super(HeadConstants.JSON_ERROR_CODE, HeadConstants.JSON_ERROR_CODE + " " + message);
    }
}
