CREATE TABLE meet_reply(
	reply_id	varchar(10)	NOT null COMMENT '답글 ID',
	board_id   	varchar(30)	NOT null COMMENT '게시글 ID',
	reply_content	varchar(2000)	NULL COMMENT '답글 내용',
	create_by	varchar(30)	NULL COMMENT '생성자',
	create_date	date	NULL,
	update_by	varchar(30)	NULL COMMENT '수정자',
	update_date	date	NULL
);

-- 기본키
ALTER TABLE meet_reply ADD CONSTRAINT PK_MEET_REPLY PRIMARY KEY ( reply_id );


-- INDEX
CREATE INDEX MMA_INDEX1 ON meet_reply (board_id);
CREATE INDEX MMA_INDEX2 ON meet_reply (board_id , reply_id );

-- 테이블 자동 증가(AUTO INCREMENT) 생성
ALTER TABLE meet_reply MODIFY COLUMN reply_id BIGINT NOT NULL AUTO_INCREMENT;
-- 테이블 자동 증가(Auto Increment)에 대한 시작 값
ALTER TABLE meet_reply AUTO_INCREMENT = 1;