package com.cafe.api.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BodyModel extends BaseModel {
  private Object data;

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

}
