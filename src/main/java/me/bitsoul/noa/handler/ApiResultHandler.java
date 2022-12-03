package me.bitsoul.noa.handler;

import lombok.extern.slf4j.Slf4j;
import me.bitsoul.noa.exception.BusinessException;
import me.bitsoul.noa.vo.resp.BaseResp;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author lxbang
 * @create 2022/12/2 12:20 下午
 */
@RestControllerAdvice
@Slf4j
public class ApiResultHandler implements ResponseBodyAdvice<BaseResp> {

    public static final int SUCCESS_CODE = 0;

    /**
     * 参数异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResp methodArgumentNotValidException(MethodArgumentNotValidException e) {
        StringBuilder errorMsg = new StringBuilder();
        BindingResult bindingResult = e.getBindingResult();
        for (FieldError error:bindingResult.getFieldErrors()){
            errorMsg.append(error.getField()).append(error.getDefaultMessage()).append("！");
        }
        BaseResp baseResp = new BaseResp();
        baseResp.setErrCode(400);
        baseResp.setMsg("参数有误！" + errorMsg.toString());
        return baseResp;
    }

    /**
     * 业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public BaseResp businessException(BusinessException e){
        BaseResp baseResp = new BaseResp();
        baseResp.setErrCode(e.getCode());
        baseResp.setMsg(e.getMessage());
        return baseResp;
    }

    /**
     * 服务器异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public BaseResp businessException(Exception e){
        BaseResp baseResp = new BaseResp();
        baseResp.setErrCode(500);
        baseResp.setMsg("服务器异常！" + e.getMessage());
        log.error("服务器异常！异常描述：{}",e.getMessage(),e);
        return baseResp;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public BaseResp beforeBodyWrite(BaseResp body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        Integer errCode = body.getErrCode();
        if (errCode == null){
            body.setErrCode(SUCCESS_CODE);
        }
        String msg = body.getMsg();
        if (msg == null){
            body.setMsg("操作成功");
        }
        return body;
    }
}
