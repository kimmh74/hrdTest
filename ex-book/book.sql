show databases;
use hrdtest;
show tables ;

CREATE TABLE Book(
BookID INT PRIMARY KEY, 
Title VARCHAR(255) NOT NULL,
Author VARCHAR (255),
Publisher VARCHAR(255),
Price INT,
PubYear DATE,
CHECK (Price >= 0)
);

INSERT INTO book(BookID,Title,Author,Publisher,Price,PubYear) VALUES
(1, '데이터베이스 개론', '홍길동', '한빛미디어', 25000, '2016-03-01'),
(2, '파이썬 프로그래밍', '이몽룡', '위키북스', 30000, '2021-06-15'),
(3, '머신러닝 입문', '성춘향', '인사이트', 35000, '2019-11-10'),
(4, '인공지능의 이해', '강감찬', '한빛아카데미', 40000, '2022-01-05'),
(5, '자료구조와 알고리즘', '김유신', '교학사', 28000, '2018-09-20'),
(6, '웹 개발 입문', '신사임당', '제이펍', 22000, '2023-04-11'),
(7, '클라우드 컴퓨팅', '최무선', '비제이퍼블릭', 33000, '2021-12-01');

SELECT * FROM book;

CREATE TABLE MEMBER(
MemberID INT PRIMARY KEY,
Name VARCHAR(255) NOT NULL,
Phone VARCHAR(255),
Address VARCHAR(255)
);

INSERT INTO MEMBER(MemberID, Name, Phone, Address) VALUES
(1,'김철수','010-1234-5678','서울특별시 강남구'),
(2,'이영희','010-2345-6789', '부산광역시 해운대구'),
(3,'박민수','010-3456-7890', '대구광역시 수성구'),
(4,'홍길동','010-4567-8901', '인천광역시 연수구'),
(5,'정우성','010-5678-9012', '경기도 성남시 분당구'),
(6,'한가영','010-6789-0123','광주광역시 북구'),
(7,'오징어','010-7890-1234','대전광역시 서구');

SELECT * FROM member;

UPDATE book
SET Author = '뿡뿡이'
WHERE BookID='1';

SELECT BookID,Title,PubYear
FROM book
WHERE PubYear >= '2020-01-01'
ORDER BY PubYear ;

SELECT b.Title AS 도서명,m.`Name` AS 회원이름,r.RentDate AS 대출일자,r.ReturnDate AS 반납일자
FROM rental AS r
INNER JOIN Member AS m
ON r.MemberID = m.MemberID
INNER JOIN book AS b
ON r.BookID=b.BookID
WHERE NAME = '홍길동';

SELECT  r.RentDate AS 대출일자, r.ReturnDate AS 반납일자, b.Title AS 책제목 , b.Price AS 책가격
FROM rental AS r
INNER JOIN book AS b
ON r.BookID=b.BookID
WHERE r.ReturnDate IS NULL;

SELECT b.BookID as 책ID, b.Title AS 책제목, COUNT(RentDate) AS 대출횟수
FROM rental AS r
INNER JOIN book AS b 
ON r.BookID = b.BookID
GROUP BY b.BookID, b.Title
ORDER BY 책ID ;

SELECT  b.Title AS 도서명, b.Price AS 책가격
FROM rental r
INNER JOIN book b
ON r.BookID = b.BookID
INNER JOIN member m
ON r.MemberID= m.MemberID
ORDER BY Price DESC
LIMIT 1;

