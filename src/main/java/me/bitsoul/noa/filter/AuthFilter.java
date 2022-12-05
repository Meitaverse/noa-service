package me.bitsoul.noa.filter;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import me.bitsoul.noa.constant.AuthConstant;
import me.bitsoul.noa.constant.SystemConstant;
import me.bitsoul.noa.exception.BusinessException;
import me.bitsoul.noa.util.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * @author lxbang
 * @create 2022/12/2 5:22 下午
 */
@Component
@WebFilter(filterName = "authFilter", urlPatterns = { "/*" })
@Slf4j
public class AuthFilter implements Filter {

    private static final String TOKEN_NAME = "token";
    /**
     * 请求白名单
     */
    private static final List<String> WHITE_LIST = Lists.newArrayList("/signin");

    @Autowired
    private JwtUtils jwtUtils;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            String servletPath = request.getServletPath();
            if (!WHITE_LIST.contains(servletPath)){
                String token = request.getHeader(TOKEN_NAME);
                Map<String, String> claimMap = jwtUtils.parseJwt(token);
                String userIdStr = claimMap.get(AuthConstant.JWT_FIELD_USER_ID);
                if (StringUtils.isNotBlank(userIdStr)){
                    request.setAttribute(AuthConstant.JWT_FIELD_USER_ID, Long.parseLong(userIdStr));
                }
                request.setAttribute(AuthConstant.JWT_FIELD_WALLET_ADDRESS,claimMap.get(AuthConstant.JWT_FIELD_WALLET_ADDRESS));
            }
            filterChain.doFilter(request,servletResponse);
        } catch (Exception e) {
            int code = 500;
            String msg = "服务器异常";
            if (e instanceof BusinessException){
                BusinessException be = (BusinessException) e;
                code = be.getCode();
                msg = be.getMessage();
            }
            returnResp(servletResponse,code,msg);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    /**
     * 返回响应结果
     * @param response
     * @param errCode
     * @param msg
     */
    private void returnResp(ServletResponse response, int errCode, String msg){
        JSONObject result = new JSONObject();
        result.put(SystemConstant.RESP_FIELD_CODE,errCode);
        result.put(SystemConstant.RESP_FIELD_MSG,msg);
        returnResp(response,result.toJSONString());
    }

    /**
     * 返回响应结果
     * @param response
     * @param resultJson
     */
    private void returnResp(ServletResponse response, String resultJson){
        try (
                OutputStream os = response.getOutputStream();
                PrintWriter writer = new PrintWriter(os);
        ) {
            writer.write(resultJson);
            writer.flush();
        } catch (IOException e) {
            log.error("filter返回响应结果失败！异常描述：{}",e.getMessage(),e);
        }
    }
}
