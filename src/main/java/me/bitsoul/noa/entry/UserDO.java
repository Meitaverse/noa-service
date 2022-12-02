package me.bitsoul.noa.entry;

import me.bitsoul.noa.enums.UserTypeEnum;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import static me.bitsoul.noa.constant.FieldConstant.*;

@Document(collection = "User")
public class UserDO {

    public static final String FIELD_WALLET_ADDRESS = "wallet_address";
    public static final String FIELD_USE_ID = "user_id";
    public static final String FIELD_USER_TYPE = "user_type";

    /**
     * ⻓度为42 ⽤⼾钱包地址
     */
    @Indexed(unique = true)
    @Field(FIELD_WALLET_ADDRESS)
    private String walletAddress;

    /**
     * 用户id
     */
    @Indexed(unique = true)
    @Field(FIELD_USE_ID)
    private Long userId;

    /**
     * ⻓度是1 0- 普通⽤⼾，1-组织者
     */
    @Field(FIELD_USER_TYPE)
    private UserTypeEnum userType;

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

    public String getWalletAddress() {
        return walletAddress;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public UserTypeEnum getUserType() {
        return userType;
    }

    public void setUserType(UserTypeEnum userType) {
        this.userType = userType;
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
        return "UserDO{" +
                "walletAddress='" + walletAddress + '\'' +
                ", userId='" + userId + '\'' +
                ", userType=" + userType +
                ", createAt=" + createAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
