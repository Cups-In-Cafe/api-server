package com.cafe.api.common.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseModel {
  private int resultCode;
  private String status;

  @Override
  public String toString() {
    return "code : " + resultCode + ", desc : " + status;
  }
}
