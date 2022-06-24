CREATE OR REPLACE PROCEDURE meet_master_mak(
	p_user_id   VARCHAR(30),  -- 유저아이디
	p_meet_type VARCHAR(30),   -- 모임타입 ( 전문모임 , 일반모임) code_type 16
	p_meet_cate VARCHAR(30), -- 모임 카테고리 code_type 43
	p_meet_nm VARCHAR(50) -- 모임명
)
BEGIN
		DECLARE p_meet_id varchar(50);
		
		select nextval( meet_master_id ) into p_meet_id;
		
	 	INSERT INTO meet_master
		(
			meet_id , meet_type, meet_cate, meet_name, meet_status, create_date
		)
		values
		(
			p_meet_id, p_meet_type , p_meet_cate, p_meet_nm, 'ba', now()
	    );
		commit;
		
		
		INSERT INTO meet_member
			(
				meet_id , user_id, member_status , user_auth, create_date
			)
		VALUES
			(
				p_meet_id , 
				p_user_id, 
				'ap',
				'm1', 
				now()
			);
		COMMIT;
		
		select * from meet_master where meet_id=p_meet_id;
		
end