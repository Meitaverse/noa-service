package me.bitsoul.noa.filter;

import lombok.extern.slf4j.Slf4j;
import me.bitsoul.noa.constant.AuthConstant;
import me.bitsoul.noa.util.JsonUtil;
import me.bitsoul.noa.util.JwtUtils;
import me.bitsoul.noa.vo.resp.BaseResp;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @author lxbang
 * @create 2022/12/2 5:22 下午
 */
@Component
@WebFilter(filterName = "authFilter", urlPatterns = {"/*"})
@Slf4j
public class AuthFilter implements Filter {

    public static final String TOKEN_NAME = "token";

    @Autowired
    private JwtUtils jwtUtils;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String servletPath = request.getServletPath();
        // 约定：路径带：v1，则进行token验证
        if (servletPath.contains("/v1/")) {
            String token = request.getHeader(TOKEN_NAME);
            if (StringUtils.isBlank(token)) {
                invalidTokenResp(servletResponse);
                return;
            }
            Map<String, String> claimMap = jwtUtils.parseJwt(token);
            String userIdStr = claimMap.get(AuthConstant.JWT_FIELD_USER_ID);
            if (StringUtils.isNotBlank(userIdStr)) {
                request.setAttribute(AuthConstant.JWT_FIELD_USER_ID, Long.parseLong(userIdStr));
            }
            request.setAttribute(AuthConstant.JWT_FIELD_WALLET_ADDRESS, claimMap.get(AuthConstant.JWT_FIELD_WALLET_ADDRESS));
        }
        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    /**
     * 响应：无效的请求
     *
     * @param response
     */
    private void invalidTokenResp(ServletResponse response) {
        BaseResp resp = new BaseResp();
        resp.setErrCode(AuthConstant.RESP_CODE_INVALID_TOKEN);
        resp.setMsg("无效的凭证");
        returnResp(response, JsonUtil.beanToJson(resp));
    }

    /**
     * 返回响应结果
     *
     * @param response
     * @param resultJson
     */
    private void returnResp(ServletResponse response, String resultJson) {
        try (
                OutputStream os = response.getOutputStream();
                PrintWriter writer = new PrintWriter(os);
        ) {
            writer.write(resultJson);
            writer.flush();
        } catch (IOException e) {
            log.error("filter返回响应结果失败！异常描述：{}", e.getMessage(), e);
        }
    }
}
