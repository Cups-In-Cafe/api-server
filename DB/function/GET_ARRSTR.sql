/*
문자열 속의 X번째 data 가져오기
str : 문자
pkg : 구분자 -> 구분자는 무조건 한자리
cnt : x번쨰 수
ex) GET_ARRSTR('111&222&333','&' , 2) -> 222
*/
CREATE FUNCTION get_arrstr(
	 str varchar(3000) , 
	 pkg varchar(1) ,  
	 cnt int
)
RETURNS varchar(30)
BEGIN

	DECLARE arr_cnt int;	
	DECLARE now_str varchar(3000);
    DECLARE return_str varchar(3000);
	SET arr_cnt = COUNT_STR(str,pkg)+1;
	SET now_str = CONCAT(str,pkg);

	FOR i IN 1..arr_cnt 
	DO
		if i = cnt then
			SET return_str = LEFT(now_str, instr(now_str,pkg)-1);
		end if;
		
		SET now_str = right(now_str,LENGTH( now_str)-LENGTH(LEFT(now_str, instr(now_str,pkg)-1))-1);
	END FOR;
	
	return return_str;
	
END