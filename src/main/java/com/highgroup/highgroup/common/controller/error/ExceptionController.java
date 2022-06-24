package com.highgroup.highgroup.common.controller.error;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.highgroup.highgroup.common.controller.CommonController;
import com.highgroup.highgroup.common.controller.DefaultController;
import com.highgroup.highgroup.common.model.BaseModel;
import com.highgroup.highgroup.common.model.BodyModel;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.highgroup.highgroup.common.controller.error.service.ExceptionService;

import lombok.extern.slf4j.Slf4j;




@Slf4j
@RestControllerAdvice
public class ExceptionController extends CommonController {

    @Resource(name = "exceptionService")
    private ExceptionService exceptionService;

    @ExceptionHandler({ Exception.class })
    public BaseModel handleAll(final Exception ex ) {
        HashMap<String,Object> errMap = new HashMap<String,Object>();
        errMap.put("errMsg", ex.getMessage() );

        BodyModel body = new BodyModel();
		body.setResultCode(1);
        body.setDesc("호출실패");
        body.setBody(errMap);
        log.info( ex.getMessage()  );
        log.info("error" , ex);
        
        errMap.put("errcon", ex.getMessage().toString().substring(0, 500) );
        
        try {
            exceptionService.insertErr(errMap);    
        } catch (Exception e) {
            log.error("ERROR" ,  e ); 
        };
        
        return body;
    };

}