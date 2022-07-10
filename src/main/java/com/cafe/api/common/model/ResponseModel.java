package com.cafe.api.common.model;

import com.cafe.api.common.utils.statusType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseModel {
    private int code;
    private String message;
    private Object data;

    public ResponseModel(){
        this.code = statusType.fail_to_access.errorCode;
        this.message = statusType.fail_to_access.message;
        this.data = null;
    }
    public void setStatus(statusType errorType){
        this.code = errorType.errorCode;
        this.message = errorType.message;
    }
}
