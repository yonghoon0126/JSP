drop table board2;
drop sequence board2_seq;
create table board2(
   num       NUMBER(4) PRIMARY KEY, --�۹�ȣ�̸� ������ ��ü�� ����Ͽ� �ڵ����� ������.(�⺻Ű)�� ����
   author    VARCHAR2(20),            -- �� �ۼ���
   title     VARCHAR2(50),            -- �� ����
   content   VARCHAR2(100),          -- �� ����
   writeday  DATE  DEFAULT SYSDATE, -- �� �ۼ���(default�� ����)
   readCnt   NUMBER(4) DEFAULT 0,   -- �� ��ȸ��
   repRoot   NUMBER(4),             -- �亯�� �ۼ��� ���(�������� ��ȣ ����)
   repStep   NUMBER(4),             -- �亯�� �ۼ��� ���(�亯���� ���� ����)    = �׷��� ����
   repIndent NUMBER(4)             -- �亯�� �ۼ��� ���(�亯���� �鿩���� ����) = �׷��� �ܰ�
 );
create sequence board2_seq; --������ ��ü ����

insert into board2( num, author, title, content, repRoot, repStep, repIndent )
values ( board2_seq.NEXTVAL , 'ȫ�浿', '�׽�Ʈ' , '�׽�Ʈ�Դϴ�.' , 
board2_seq.CURRVAL, 0 , 0 );

select * from board2;

 