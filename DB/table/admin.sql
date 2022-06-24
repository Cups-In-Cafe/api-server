CREATE TABLE ADMIN_USER(
  admin_user_id varchar(45) NOT NULL COMMENT '관리자ID',
  admin_auth varchar(10) COMMENT '권한',
  admin_pwd varchar(65) COMMENT '패스워드',
  admin_name varchar(20) COMMENT '사용자 이름',
  admin_phone_num varchar(20) COMMENT '연락처',
  admin_email varchar(30) COMMENT '이메일',
  create_dt datetime COMMENT '생성일자',
  update_dt datetime COMMENT '변경일자',
  latest_dt datetime COMMENT '최근접속시간',
  delete_yn varchar(2) COMMENT '삭제여부',
  PRIMARY KEY(admin_user_id)
)

CREATE TABLE ADMIN_USER_LOG(
  log_id int NOT NULL COMMENT '로그ID',
  admin_user_id varchar(45) NOT NULL COMMENT '관리자ID',
  connect_ip varchar(20) COMMENT '접속IP',
  login_dt datetime COMMENT '접속시간',
  PRIMARY KEY(log_id),
  FOREIGN KEY (admin_user_id)
    REFERENCES admin_user(admin_user_id)
)