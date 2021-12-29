create table product(
	code number(5),
	name varchar2(100),
	price number(20),
	pictureurl varchar(50),
	description varchar(1000),
	primary key(code)
);

drop table product;
drop sequence product_seq;

create sequence product_seq start with 1 increment by 1;

insert into product values(product_seq.nextval, '������ ���� ����ִ� �����ͺ��̽�', 
27000, 'db.jpg', '�����ͺ��̽��� ���� ��� ���� ���� ����ְ� ������ ����...');

insert into product values(product_seq.nextval, '��ǥ���� ���� HTML5', 
25000, 'html5.jpg', 'HTML5 ���̵��. Ȩ������ ������ ���� �ʼ� ���� HTML �⺻ ����');

insert into product values(product_seq.nextval, 'Dynamic Programming book �ø���-01 ����Ŭ 11g + PL/SQL', 
25000, 'oracle.jpg', 'Dynamic �ǹ� ��Ī ���α׷��� Book�� ù��° å����, ����Ŭ 11g�� ���ο�...');

insert into product values(product_seq.nextval, 'Visual C++ MFC ������ ���α׷���', 
26000, 'mfc.jpg', 'Visual C++�� ó�� �����ϴ� ������ �����̿� ���� Visual C++...');

insert into product values(product_seq.nextval, 'jQuery and jQuery Mobile : �����ϱ� ���� Ǯ�', 
25000, 'jquery.jpg', '�ҽ� �ϳ��� ����ũž�� ����ϱ��� HTML5�� �Բ� ����Ѵ�. �ʺ��ڵ鵵...');

commit

select * from product;
