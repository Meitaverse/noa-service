package me.bitsoul.noa.dto;

import lombok.Data;
import lombok.ToString;
import me.bitsoul.noa.enums.NoATokenStatusEnum;

@Data
@ToString
public class NoATokenDTO {
    /**
     * 勋章申请者的钱包地址
     */
    private String toAddress;

    /**
     * 关联活动表的event_id
     */
    private String eventId;

    /**
     * 勋章领取URL
     */
    private String claimUrl;

    /**
     * 勋章领取状态，0-未领取，1-已领取，2-失效
     */
    private NoATokenStatusEnum status;

    /**
     * id
     */
    private String tokenId;

    /**
     * ⻓整型 UNIX时间戳, 创建时间
     */
    private Long createAt;

    /**
     * ⻓整型 UNIX时间戳, 修改时间
     */
    private Long updatedAt;


}
