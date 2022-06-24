package com.highgroup.highgroup.common.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseModel {
  private int resultCode;
  private String desc;

  @Override
  public String toString() {
    return "code : " + resultCode + ", desc : " + desc;
  }
}
