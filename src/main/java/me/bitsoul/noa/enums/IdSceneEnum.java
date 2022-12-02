package me.bitsoul.noa.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 唯一id的场景
 * @author lxbang
 * @create 2022/12/2 4:02 下午
 */
public enum IdSceneEnum implements ConverterEnum {

    /**
     * 用户id
     */
    USER_ID("userId");

    IdSceneEnum(String val) {
        this.val = val;
    }

    private String val;

    public String getVal() {
        return val;
    }

    @JsonCreator
    public static IdSceneEnum parse(String val){
        if (val != null){
            for (IdSceneEnum value : values()) {
                if (value.getVal().equals(val)){
                    return value;
                }
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
