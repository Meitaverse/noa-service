package me.bitsoul.noa.dto.resp;


import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 【统一】平台的返回结果
 *
 * @author lxbang
 * @create 2019/12/5 3:23 下午
 */
public class BaseResp {

    @JsonProperty("err_code")
    private Integer errCode;
    @JsonProperty("msg")
    private String msg;

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "errCode=" + errCode +
                ", msg='" + msg + '\'' +
                '}';
    }
}
