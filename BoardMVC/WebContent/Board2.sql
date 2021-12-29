create table board2(
	num 		number(4) primary key,		-- 글번호이며 시퀀스 객체를 이용하여 자동으로 증가됨.(기본키로 설정)
	author		varchar2(20),				-- 글 작성자
	title		varchar2(50),				-- 글 제목
	content		varchar2(100),				-- 글 내용
	writeday	date		default SYSDATE,-- 글 작성일(default로 지정)
	readCnt		number(4)	default 0,		-- 글 조회수
	repRoot		number(4),					-- 답변글 작성시 사용(원래글의 번호 참조)
	repStep		number(4),					-- 답변글 작성시 사용(답변근의 순서 지정)	  = 그룹의 순서
	repIndent 	number(4)					-- 답변글 작성시 사용(답변글의 들여쓰기 지정) = 그룹의 단계
);

create sequence board2_seq;			-- 시퀀스 객체 생성

insert into board2(num, author, title, content, repRoot, repStep, repIndent)
values(board2_seq.NEXTVAL , '홍길동', '테스트', '테스트입니다.', board2_seq.CURRVAL, 0, 0);


select * from board2;

