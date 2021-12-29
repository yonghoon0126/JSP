create table board2(
	num 		number(4) primary key,		-- �۹�ȣ�̸� ������ ��ü�� �̿��Ͽ� �ڵ����� ������.(�⺻Ű�� ����)
	author		varchar2(20),				-- �� �ۼ���
	title		varchar2(50),				-- �� ����
	content		varchar2(100),				-- �� ����
	writeday	date		default SYSDATE,-- �� �ۼ���(default�� ����)
	readCnt		number(4)	default 0,		-- �� ��ȸ��
	repRoot		number(4),					-- �亯�� �ۼ��� ���(�������� ��ȣ ����)
	repStep		number(4),					-- �亯�� �ۼ��� ���(�亯���� ���� ����)	  = �׷��� ����
	repIndent 	number(4)					-- �亯�� �ۼ��� ���(�亯���� �鿩���� ����) = �׷��� �ܰ�
);

create sequence board2_seq;			-- ������ ��ü ����

insert into board2(num, author, title, content, repRoot, repStep, repIndent)
values(board2_seq.NEXTVAL , 'ȫ�浿', '�׽�Ʈ', '�׽�Ʈ�Դϴ�.', board2_seq.CURRVAL, 0, 0);


select * from board2;

