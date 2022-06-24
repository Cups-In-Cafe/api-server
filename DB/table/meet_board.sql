CREATE TABLE meet_board(
	board_id	varchar(10)	NOT null COMMENT '게시판 ID', 	
	meet_id   	varchar(30)	NOT null COMMENT '모임 ID',
	board_type	varchar(10)	NULL COMMENT '게시판 타입(자유게시판,공지사항,가입인사등)',
	board_title	varchar(300)	NULL COMMENT '게시판 제목',
	board_content	varchar(4000)	NULL COMMENT '게시만 내용',
	create_by	varchar(30)	NULL COMMENT '생성자',
	create_date	date	NULL,
	update_by	varchar(30)	NULL COMMENT '수정자',
	update_date	date	NULL
);

-- 기본키
ALTER TABLE meet_board ADD CONSTRAINT PK_MEET_BOARD PRIMARY KEY ( board_id );


-- INDEX
CREATE INDEX MMA_INDEX1 ON meet_board (meet_id);
CREATE INDEX MMA_INDEX2 ON meet_board (meet_id , board_id );

-- 테이블 자동 증가(AUTO INCREMENT) 생성
ALTER TABLE meet_board MODIFY COLUMN board_id BIGINT NOT NULL AUTO_INCREMENT;
-- 테이블 자동 증가(Auto Increment)에 대한 시작 값
ALTER TABLE meet_board AUTO_INCREMENT = 1;