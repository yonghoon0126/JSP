drop table guestbook;     
drop sequence guestbook_seq;

create table guestbook(
 	gb_id         number primary key,      --각각의 게시물을 구분하는 고유 번호  
	gb_name       varchar2(30) not null,   --작성자의 이름  
	gb_email      varchar2(50) ,           --작성의 이메일
	gb_passwd     varchar2(6) not null,    --수정, 삭제를 위한 비밀번호
	gb_tel        varchar2(20) not null,    --전화번호
 	gb_date       date not null,           --작성일
	gb_contents   varchar2(500)           --게시물 내용
);

create sequence guestbook_seq ;
-- 트리거는 특정 이벤트 발생 시,  프로그래머가 지정한 작업을 수행하도록 만들어 놓은
-- 일종의 자동 수행 스크립트라고 보면 된다.
-- 테이블에 삽입하기 전에 새로운 시퀀스 값을 구한 다음에 gb_id에 넣는다.
-- 프로그램내에서 오라클 종속적인 sql문이 들어가게 되어 향후 디비를 바꾸게 되는 경우
-- 프로그램을 손봐야 하는 문제를 해결하기 위해 트리거 사용한다.
-- GUESTBOOK테이블에 INSERT하기 전에 새로운 시퀀스 값을 NEXTVAL을 통해 구한 다음 GB_ID에 넣는다.
-- ---------------------------------실행은 CLI에서 수행해야 트리거가 제대로 된다.
create or replace trigger bi_guestbook   -- 학습모듈__데이터 입출력구현__p38 참고 하세요.
	before insert on guestbook
	for each row
begin 
	select GUESTBOOK_seq.nextval into :NEW.GB_ID from dual;
end;
/
-- --------------------------------------------------

--select GUESTBOOK_seq.currval from dual;
alter trigger bi_guestbook enable;

