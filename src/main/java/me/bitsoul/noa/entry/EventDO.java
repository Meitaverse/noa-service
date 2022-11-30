package me.bitsoul.noa.entry;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import static me.bitsoul.noa.constant.FieldConstant.FIELD_CREATE_AT;
import static me.bitsoul.noa.constant.FieldConstant.FIELD_UPDATED_AT;

@Document(collection = "Event")
public class EventDO {

    public static final String FIELD_ORGANIZER_ADDRESS = "organizer_address";
    public static final String FIELD_EVENT_ID = "event_id";
    public static final String FIELD_EVENT_NAME = "event_name";
    public static final String FIELD_EVENT_DESCRIPTION = "event_description";
    public static final String FIELD_EVENT_IMAGE = "event_image";
    public static final String FIELD_MINT_MAX = "mint_max";

    /**
     * ⻓度为42 组织者钱包地址
     */
    @Field(FIELD_ORGANIZER_ADDRESS)
    private String organizerAddress;
    /**
     * ⻓度为255 ⼤整数转换为字符串
     */
    @Field(FIELD_EVENT_ID)
    private String eventId;

    /**
     * 活动名称
     */
    @Field(FIELD_EVENT_NAME)
    private String eventName;

    /**
     * 活动描述
     */
    @Field(FIELD_EVENT_DESCRIPTION)
    private String eventDescription;

    /**
     * 活动海报图⽚的 url地址
     */
    @Field(FIELD_EVENT_IMAGE)
    private String eventImage;

    /**
     * 整形 活动发⾏勋章最⼤数量
     */
    @Field(FIELD_MINT_MAX)
    private Integer mintMax;

    /**
     * ⻓整型 UNIX时间戳, 创建时间
     */
    @Field(FIELD_CREATE_AT)
    private Long createAt;

    /**
     * ⻓整型 UNIX时间戳, 修改时间
     */
    @Field(FIELD_UPDATED_AT)
    private Long updatedAt;


    public String getOrganizerAddress() {
        return organizerAddress;
    }

    public void setOrganizerAddress(String organizerAddress) {
        this.organizerAddress = organizerAddress;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventImage() {
        return eventImage;
    }

    public void setEventImage(String eventImage) {
        this.eventImage = eventImage;
    }

    public Integer getMintMax() {
        return mintMax;
    }

    public void setMintMax(Integer mintMax) {
        this.mintMax = mintMax;
    }

    public Long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "EventDO{" +
                "organizerAddress='" + organizerAddress + '\'' +
                ", eventId='" + eventId + '\'' +
                ", eventName='" + eventName + '\'' +
                ", eventDescription='" + eventDescription + '\'' +
                ", eventImage='" + eventImage + '\'' +
                ", mintMax=" + mintMax +
                ", createAt=" + createAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
