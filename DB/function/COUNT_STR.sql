/*
문자열 속의 구분자 갯수 체크
str : 문자
pkg : 구분자 -> 구분자는 무조건 한자리

ex) count_str('111&222&333','&') -> 2
*/
CREATE FUNCTION count_str( 
	X varchar(3000), 
	DELIM varchar(1) 
) 
RETURNS INT
BEGIN
	RETURN (LENGTH(X) - LENGTH(REPLACE(X, DELIM,''))) / LENGTH(DELIM);	
END