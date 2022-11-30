package me.bitsoul.noa.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum NoATokenStatusEnum implements ConverterEnum {
    /**
     * 未领取
     */
    UNRECEIVED(0),
    /**
     * 已领取
     */
    RECEIVED(1),
    /**
     * 已失效
     */
    INVALID(2);

    private final Integer val;

    NoATokenStatusEnum(Integer val) {
        this.val = val;
    }

    @JsonCreator
    public static NoATokenStatusEnum parse(Integer val) {
        for (NoATokenStatusEnum item : NoATokenStatusEnum.values()) {
            if (item.getValue().equals(val)) {
                return item;
            }
        }
        return null;
    }

    @JsonValue
    @Override
    public Object getValue() {
        return val;
    }
}
