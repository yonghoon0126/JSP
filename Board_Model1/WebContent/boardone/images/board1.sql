create table board1(
	num			number(7,0)		not null					enable,		-- �۹�ȣ
	writer		varchar2(12)	not null					enable,		-- �ۼ���
	email		varchar2(30)	not null					enable,		-- �̸���
	subject		varchar2(50)	not null					enable,		-- ����
	pass		varchar2(10)	not null					enable,		-- ���
	readcount	number(5,0)		default 0		not null	enable,		-- ��ȸ��
	ref			number(5,0)		default 0		not null	enable,		-- �۹�ȣ ������ȣ
	step		number(3,0)		default 0		not null	enable,		-- ��� ����
	depth		number(3,0)		default 0		not null	enable,		-- ������ �׷쳻���� ����
	regdate		timestamp(6)	default sysdate not null	enable,		-- �ۼ� ��¥
	content		varchar2(4000)	not null					enable,		-- ����
	ip			varchar2(20)	not null					enable,		-- ip
	constraint	board1_pk		primary key(num)			enable
);

-- ������ 0, ����� 1�� ����
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






