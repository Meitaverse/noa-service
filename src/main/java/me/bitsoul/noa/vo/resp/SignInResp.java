package me.bitsoul.noa.vo.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author lxbang
 * @create 2022/12/2 4:51 下午
 */
@Data
public class SignInResp extends BaseResp {

    @JsonProperty("jwt")
    private String jwt;

}
