package me.bitsoul.noa.entry;

import me.bitsoul.noa.enums.NoATokenStatusEnum;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import static me.bitsoul.noa.constant.FieldConstant.FIELD_CREATE_AT;
import static me.bitsoul.noa.constant.FieldConstant.FIELD_UPDATED_AT;

@Document(collection = "NoAToken")
public class NoATokenDO {
    public static final String FIELD_TO_ADDRESS = "to_address";
    public static final String FIELD_EVENT_ID = "event_id";
    public static final String FIELD_CLAIM_URL = "claim_url";
    public static final String FIELD_STATUS = "state";
    public static final String FIELD_TOKEN_ID = "token_id";

    /**
     * 勋章申请者的钱包地址
     */
    @Indexed
    @Field(FIELD_TO_ADDRESS)
    private String toAddress;

    /**
     * 关联活动表的event_id
     */
    @Indexed
    @Field(FIELD_EVENT_ID)
    private String eventId;

    /**
     * 勋章领取URL
     */
    @Field(FIELD_CLAIM_URL)
    private String claimUrl;

    /**
     * 勋章领取状态，0-未领取，1-已领取，2-失效
     */
    @Field(FIELD_STATUS)
    private NoATokenStatusEnum status;

    /**
     * id
     */
    @Indexed
    @Field(FIELD_TOKEN_ID)
    private String tokenId;

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


    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getClaimUrl() {
        return claimUrl;
    }

    public void setClaimUrl(String claimUrl) {
        this.claimUrl = claimUrl;
    }

    public NoATokenStatusEnum getStatus() {
        return status;
    }

    public void setStatus(NoATokenStatusEnum status) {
        this.status = status;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
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
        return "NoATokenDO{" +
                "toAddress='" + toAddress + '\'' +
                ", eventId='" + eventId + '\'' +
                ", claimUrl='" + claimUrl + '\'' +
                ", status=" + status +
                ", tokenId='" + tokenId + '\'' +
                ", createAt=" + createAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
