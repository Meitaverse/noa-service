package me.bitsoul.noa.vo.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Data
@ToString
public class AskForNoaResp extends BaseResp{
    @ApiModelProperty(name = "用户id", required = true)
    @NotEmpty
    @JsonProperty("claim_url")
    private String claimUrl;
}
