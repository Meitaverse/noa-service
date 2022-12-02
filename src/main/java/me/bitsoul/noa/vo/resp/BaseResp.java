package me.bitsoul.noa.vo.resp;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 【统一】平台的返回结果
 *
 * @author lxbang
 * @create 2019/12/5 3:23 下午
 */
@Data
public class BaseResp {

    @JsonProperty("err_code")
    private Integer errCode;
    @JsonProperty("msg")
    private String msg;

}
