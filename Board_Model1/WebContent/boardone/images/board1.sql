create table board1(
	num			number(7,0)		not null					enable,		-- 글번호
	writer		varchar2(12)	not null					enable,		-- 작성자
	email		varchar2(30)	not null					enable,		-- 이메일
	subject		varchar2(50)	not null					enable,		-- 제목
	pass		varchar2(10)	not null					enable,		-- 비번
	readcount	number(5,0)		default 0		not null	enable,		-- 조회수
	ref			number(5,0)		default 0		not null	enable,		-- 글번호 참조번호
	step		number(3,0)		default 0		not null	enable,		-- 답글 순서
	depth		number(3,0)		default 0		not null	enable,		-- 원글의 그룹내에서 순서
	regdate		timestamp(6)	default sysdate not null	enable,		-- 작성 날짜
	content		varchar2(4000)	not null					enable,		-- 내용
	ip			varchar2(20)	not null					enable,		-- ip
	constraint	board1_pk		primary key(num)			enable
);

-- 원글은 0, 답글은 1씩 증가
create sequence board1_seq
		start with 1
		increment by 1
		nomaxvalue
		nocache
		nocycle;
		
commit

select * from board1;

drop table board1;

drop sequence board_seq;






