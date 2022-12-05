package me.bitsoul.noa.vo.resp;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import me.bitsoul.noa.constant.SystemConstant;

/**
 * 【统一】平台的返回结果
 *
 * @author lxbang
 * @create 2019/12/5 3:23 下午
 */
@Data
public class BaseResp {

    @JsonProperty(SystemConstant.RESP_FIELD_CODE)
    private Integer errCode;
    @JsonProperty(SystemConstant.RESP_FIELD_MSG)
    private String msg;

}
