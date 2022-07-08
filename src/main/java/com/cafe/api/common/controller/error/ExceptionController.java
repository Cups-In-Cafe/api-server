package com.cafe.api.common.controller.error;

import java.util.HashMap;
import java.util.Map;

import com.cafe.api.common.controller.CommonController;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;




@Slf4j
//@RestControllerAdvice
public class ExceptionController extends CommonController {

    @ExceptionHandler({ AppException.class })
    public Map<String,Object> handleApp( final AppException ex ) {
        Map<String,Object> result = new HashMap<>();
        result.put("status",ex.getErrorType());
        return result;
    };

    @ExceptionHandler({ Exception.class })
    public Map<String,Object> handleAll(final Exception ex ) {

        Map<String,Object> result = new HashMap<>();
        result.put("status",statusType.internal_error);
        if( ex != null ){
            result.put("error",ex.getMessage().toString());
        };

        try {
            //exceptionService.insertErr(errMap);
        } catch (Exception e) {
            log.error("ERROR" ,  e );
        };

        return result;
    };

}