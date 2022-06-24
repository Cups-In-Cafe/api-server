package com.highgroup.highgroup.common.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UserModel {
  private String user_id;
  private String user_pw;
  private String user_nickname;
  private String user_sex;
  private String user_place;
  private String user_birthday;
  private boolean user_drink;
  private boolean user_smoking;
  private String user_blood;
  private String create_date;
  private String update_date;
  private String delete_yn;

  // test용
  public UserModel(String user_id, String user_pw, String user_nickname, String user_sex, String user_place,
      String user_birthday, boolean user_drink, boolean user_smoking, String user_blood) {
    this.user_id = user_id;
    this.user_pw = user_pw;
    this.user_nickname = user_nickname;
    this.user_sex = user_sex;
    this.user_place = user_place;
    this.user_birthday = user_birthday;
    this.user_drink = user_drink;
    this.user_smoking = user_smoking;
    this.user_blood = user_blood;

  }
}
