
CREATE TABLE meet_member(
    meet_id	varchar(30)	NOT NULL COMMENT 'ID',
	user_id	varchar(30)	NOT NULL COMMENT '모임 유저 ID',
	user_auth	varchar(30)	NULL COMMENT '모임 유저 권한',
	meet_member varchar(30)	NULL COMMENT '모임 멤버 상황(탈퇴 , 정지...)',
	create_date	date	NULL,
	update_date	date	NULL
);


-- 사용자 테이블 기본키 생성
ALTER TABLE meet_member ADD CONSTRAINT PK_MEET_MEMBER PRIMARY KEY ( meet_id, user_id );

-- INDEX
CREATE INDEX MM_INDEX1 ON meet_member (meet_id);
CREATE INDEX MM_INDEX2 ON meet_member (user_id);
