package com.cafe.api.config.advice;


import com.cafe.api.common.utils.statusType;
import com.cafe.api.common.model.ResponseModel;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Map;

@ControllerAdvice
@org.springframework.web.bind.annotation.RestControllerAdvice
public class RestControllerAdvice<T> implements ResponseBodyAdvice<T> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public T beforeBodyWrite(T body, MethodParameter returnType,
                             MediaType selectedContentType,
                             Class<? extends HttpMessageConverter<?>> selectedConverterType,
                             ServerHttpRequest request,
                             ServerHttpResponse response) {

        Map<String,Object> result = (Map<String, Object>) body;
        ResponseModel responseModel = new ResponseModel();
        responseModel.setStatus( result.containsKey("status") ? (statusType) result.get("status") : statusType.api_success );
        result.remove("status");
        responseModel.setData(result);
        return (T) responseModel;

    }
}