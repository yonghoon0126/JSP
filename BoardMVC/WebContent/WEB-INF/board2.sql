drop table board2;
drop sequence board2_seq;
create table board2(
   num       NUMBER(4) PRIMARY KEY, --글번호이며 시퀀스 객체를 사용하여 자동으로 증가됨.(기본키)로 설정
   author    VARCHAR2(20),            -- 글 작성자
   title     VARCHAR2(50),            -- 글 제목
   content   VARCHAR2(100),          -- 글 내용
   writeday  DATE  DEFAULT SYSDATE, -- 글 작성일(default로 지정)
   readCnt   NUMBER(4) DEFAULT 0,   -- 글 조회수
   repRoot   NUMBER(4),             -- 답변글 작성시 사용(원래글의 번호 참조)
   repStep   NUMBER(4),             -- 답변글 작성시 사용(답변글의 순서 지정)    = 그룹의 순서
   repIndent NUMBER(4)             -- 답변글 작성시 사용(답변글의 들여쓰기 지정) = 그룹의 단계
 );
create sequence board2_seq; --시퀀스 객체 생성

insert into board2( num, author, title, content, repRoot, repStep, repIndent )
values ( board2_seq.NEXTVAL , '홍길동', '테스트' , '테스트입니다.' , 
board2_seq.CURRVAL, 0 , 0 );

select * from board2;

 