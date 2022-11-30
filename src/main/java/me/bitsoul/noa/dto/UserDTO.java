package me.bitsoul.noa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import me.bitsoul.noa.enums.UserTypeEnum;

public class UserDTO {

    /**
     * ⽤⼾钱包地址
     */
    @JsonProperty("wallet_address")
    private String walletAddress;

    /**
     * ⽤⼾验签之后的令牌
     */
    @JsonProperty("jwt")
    private String jwt;

    /**
     * 0- 普通⽤⼾，1-组织者
     */
    @JsonProperty("user_type")
    private UserTypeEnum userType;

    /**
     * 创建时间
     */
    private Long createAt;

    /**
     * 修改时间
     */
    private Long updatedAt;

    public String getWalletAddress() {
        return walletAddress;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
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
        return "UserDTO{" +
                "walletAddress='" + walletAddress + '\'' +
                ", jwt='" + jwt + '\'' +
                ", userType=" + userType +
                ", createAt=" + createAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
