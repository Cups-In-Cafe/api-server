CREATE OR REPLACE PROCEDURE code_modify(
	p_mode	    VARCHAR(30),
	p_code_id   bigint(20),
	p_code      VARCHAR(30),
	p_code_nm   VARCHAR(50),
	p_ordr_no	int,
	p_use_yn    VARCHAR(5),
	p_code_type VARCHAR(50)
)
BEGIN
	
	if p_mode = 'U' then
		begin 
			UPDATE code_master SET 
				   CODE_NM=p_code_nm, 
				   USE_YN=p_use_yn, 
				   UPDATE_DATE=now(), 
				   ORDR_NO=p_ordr_no
			WHERE  CODE_ID=p_code_id;
		end;
	end if;
	
	if p_mode ='C' then
		begin
			INSERT INTO code_master
			(
				code_type, code, 
				code_nm  , use_yn, 
				create_date, ordr_no
			)
			VALUES(
				p_code_type	, p_code,
				p_code_nm 	, 'Y'	,
				now()	    , p_ordr_no
			);
		end;
	end if;	

	COMMIT;

end

