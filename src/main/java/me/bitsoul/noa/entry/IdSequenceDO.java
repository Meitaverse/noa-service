package me.bitsoul.noa.entry;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author lxbang
 * @create 2022/12/2 2:39 下午
 */
@Document(collection = "IdSequence")
public class IdSequenceDO {

    public static final String FIELD_SCENE = "scene";
    public static final String FIELD_SERIAL_NO = "serial_no";

    @Indexed(unique = true)
    @Field(FIELD_SCENE)
    private String scene;
    @Field(FIELD_SERIAL_NO)
    private Long serialNo;

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public Long getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(Long serialNo) {
        this.serialNo = serialNo;
    }
}
