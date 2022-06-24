
CREATE TABLE meet_master(
 	meet_id	varchar(30)	NOT NULL,
	meet_type	varchar(10)	NULL COMMENT '모임 타입(전문,일반)',
	meet_cate	varchar(10)	NULL COMMENT '모임 카테고리(독서,스포츠,공부...)',
	meet_name	varchar(50)	NULL COMMENT '모임 이름',
	meet_status	varchar(50)	NULL COMMENT '모임 현황(승인,정지등)',
	create_date	date	NULL,
	update_date	date	NULL
)

ALTER TABLE meet_master ADD CONSTRAINT PK_MEET_MASTER PRIMARY KEY ( meet_id );


-- INDEX
CREATE INDEX MMA_INDEX1 ON meet_master (meet_id);

--시퀀스
create sequence meet_master_id start with 1 increment by 1;

--시퀀스 조회 방법
select nextval( meet_master_id );