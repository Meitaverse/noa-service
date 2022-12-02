package me.bitsoul.noa.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author lxbang
 * @create 2022/12/2 4:54 下午
 */
@Data
public class SignInVO {

    @NotBlank
    @JsonProperty("wallet_address")
    private String walletAddress;
    @NotBlank
    @JsonProperty("signed")
    private String signed;

}
