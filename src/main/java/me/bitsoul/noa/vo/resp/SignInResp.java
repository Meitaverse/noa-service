package me.bitsoul.noa.vo.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lxbang
 * @create 2022/12/2 4:51 下午
 */
@ApiModel(value = "SignInResp",description = "注册/登录的响应结果")
@Data
public class SignInResp extends BaseResp {

    @ApiModelProperty(name = "请求凭证",required = true)
    @JsonProperty("jwt")
    private String jwt;

}
