package me.bitsoul.noa.entry;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author lxbang
 * @create 2022/12/2 2:39 下午
 */
@Data
@Document(collection = "IdSequence")
public class IdSequenceDO {

    public static final String FIELD_SCENE = "scene";
    public static final String FIELD_SERIAL_NO = "serial_no";
    public static final String FIELD_VERSION = "version";

    @Indexed(unique = true)
    @Field(FIELD_SCENE)
    private String scene;
    @Field(FIELD_SERIAL_NO)
    private Long serialNo;
    @Field(FIELD_VERSION)
    private Long version;

}
