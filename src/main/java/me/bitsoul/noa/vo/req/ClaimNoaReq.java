package me.bitsoul.noa.vo.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Data
@ToString
public class ClaimNoaReq {

    @ApiModelProperty(name = "活动id", required = true)
    @NotEmpty
    @JsonProperty("event_id")
    private String eventId;
}
