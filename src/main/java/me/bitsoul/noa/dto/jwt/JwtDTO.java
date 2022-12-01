package me.bitsoul.noa.dto.jwt;

/**
 * @author lxbang
 * @create 2022/12/1 4:07 下午
 */
public class JwtDTO {

    private String jwt;
    private Long validPeriodSecond;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public Long getValidPeriodSecond() {
        return validPeriodSecond;
    }

    public void setValidPeriodSecond(Long validPeriodSecond) {
        this.validPeriodSecond = validPeriodSecond;
    }
}
