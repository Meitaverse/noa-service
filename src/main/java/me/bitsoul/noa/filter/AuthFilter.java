package me.bitsoul.noa.filter;

import me.bitsoul.noa.constant.AuthConstant;
import me.bitsoul.noa.exception.BusinessException;
import me.bitsoul.noa.util.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * @author lxbang
 * @create 2022/12/2 5:22 下午
 */
@Component
@WebFilter(filterName = "authFilter", urlPatterns = { "/*" })
public class AuthFilter implements Filter {

    public static final String TOKEN_NAME = "token";

    @Autowired
    private JwtUtils jwtUtils;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String servletPath = request.getServletPath();
        // 约定：路径带：v1，则进行token验证
        if (servletPath.contains("/v1/")){
            String token = request.getHeader(TOKEN_NAME);
            if (StringUtils.isBlank(token)){
                throw new BusinessException(AuthConstant.RESP_CODE_INVALID_TOKEN,"无效的凭证");
            }
            Map<String, String> claimMap = jwtUtils.parseJwt(token);
            request.setAttribute(AuthConstant.JWT_FIELD_USER_ID,claimMap.get(AuthConstant.JWT_FIELD_USER_ID));
            request.setAttribute(AuthConstant.JWT_FIELD_WALLET_ADDRESS,claimMap.get(AuthConstant.JWT_FIELD_WALLET_ADDRESS));
        }
        filterChain.doFilter(request,servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
