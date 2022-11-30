package me.bitsoul.noa.enums;

/**
 * @author liujinhua
 */
public interface ConverterEnum {
    /**
     * 获取Enum 对应的Integer/String
     *
     * @return Enum 转化为json 或者保存到数据库的实际Integer/String
     */
    Object getValue();
}
