package me.bitsoul.noa.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserTypeEnum implements ConverterEnum {
    /**
     * 普通⽤⼾
     */
    NORMAL(0),
    /**
     * 组织者
     */
    ORGANIZER(1);

    private final Integer val;

    UserTypeEnum(Integer val) {
        this.val = val;
    }

    @JsonCreator
    public static UserTypeEnum parse(Integer val) {
        for (UserTypeEnum item : UserTypeEnum.values()) {
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
