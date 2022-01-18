drop table guestbook;     
drop sequence guestbook_seq;

create table guestbook(
 	gb_id         number primary key,      --������ �Խù��� �����ϴ� ���� ��ȣ  
	gb_name       varchar2(30) not null,   --�ۼ����� �̸�  
	gb_email      varchar2(50) ,           --�ۼ��� �̸���
	gb_passwd     varchar2(6) not null,    --����, ������ ���� ��й�ȣ
	gb_tel        varchar2(20) not null,    --��ȭ��ȣ
 	gb_date       date not null,           --�ۼ���
	gb_contents   varchar2(500)           --�Խù� ����
);

create sequence guestbook_seq ;
-- Ʈ���Ŵ� Ư�� �̺�Ʈ �߻� ��,  ���α׷��Ӱ� ������ �۾��� �����ϵ��� ����� ����
-- ������ �ڵ� ���� ��ũ��Ʈ��� ���� �ȴ�.
-- ���̺� �����ϱ� ���� ���ο� ������ ���� ���� ������ gb_id�� �ִ´�.
-- ���α׷������� ����Ŭ �������� sql���� ���� �Ǿ� ���� ��� �ٲٰ� �Ǵ� ���
-- ���α׷��� �պ��� �ϴ� ������ �ذ��ϱ� ���� Ʈ���� ����Ѵ�.
-- GUESTBOOK���̺� INSERT�ϱ� ���� ���ο� ������ ���� NEXTVAL�� ���� ���� ���� GB_ID�� �ִ´�.
-- ---------------------------------������ CLI���� �����ؾ� Ʈ���Ű� ����� �ȴ�.
create or replace trigger bi_guestbook   -- �н����__������ ����±���__p38 ���� �ϼ���.
	before insert on guestbook
	for each row
begin 
	select GUESTBOOK_seq.nextval into :NEW.GB_ID from dual;
end;
/
-- --------------------------------------------------

--select GUESTBOOK_seq.currval from dual;
alter trigger bi_guestbook enable;

