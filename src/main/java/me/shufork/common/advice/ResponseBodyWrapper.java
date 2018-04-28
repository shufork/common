package me.shufork.common.advice;


import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import me.shufork.common.enums.ErrorCodeEnums;
import me.shufork.common.dto.misc.PageResult;
import me.shufork.common.dto.misc.ReplyBody;
import me.shufork.common.util.JsonUtil;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.PostConstruct;
import java.util.List;

@RestControllerAdvice("me.shufork.biz.controller")
@Slf4j
public class ResponseBodyWrapper implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // already processed by ExceptionHandler
        return returnType.getContainingClass() != ControllerExceptionAdvice.class;
    }


    @SuppressWarnings("unchecked")
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        ReplyBody<Object> replyBody = new ReplyBody<>(ErrorCodeEnums.OK.getValue());

        if (body == null) {
            //
        } else if (body instanceof List) {
            replyBody.setDataList((List) body);
        } else if (body instanceof PageResult) {
            replyBody.setPageResult((PageResult) body);
        } else {
            replyBody.setData(body);
        }
        if(returnType.getMethod().getReturnType().isAssignableFrom(String.class)){
            log.warn("rest method({}.{}) return String type",
                    returnType.getMethod().getDeclaringClass().getSimpleName() ,returnType.getMethod().getName());
            return JsonUtil.toJsonOrNull(replyBody);
        }
        return replyBody;
    }

    @PostConstruct
    private void onInit(){
        log.info("{} init",getClass().getSimpleName());
    }
}
