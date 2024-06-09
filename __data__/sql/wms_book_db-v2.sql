DROP TABLE IF EXISTS tbl_order_item CASCADE;
DROP TABLE IF EXISTS tbl_order CASCADE;
DROP TABLE IF EXISTS tbl_user CASCADE;
DROP TABLE IF EXISTS tbl_inventory CASCADE;
DROP TABLE IF EXISTS tbl_book CASCADE;

CREATE TABLE tbl_user (
    user_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '사용자 ID',
    username VARCHAR(50) UNIQUE NOT NULL COMMENT '사용자 이름',
    password VARCHAR(255) NOT NULL COMMENT '비밀번호',
    role ENUM('admin', 'manager', 'employee') NOT NULL COMMENT '사용자 역할',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '생성 일시'
) COMMENT '사용자 테이블';

CREATE TABLE tbl_book (
    book_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '도서 ID',
    title VARCHAR(255) NOT NULL COMMENT '도서 제목',
    author VARCHAR(255) NOT NULL COMMENT '저자',
    description TEXT COMMENT '설명',
    price DECIMAL(10, 2) NOT NULL COMMENT '가격',
    category VARCHAR(100) NOT NULL COMMENT '카테고리',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '생성 일시'
) COMMENT '도서 테이블';

CREATE TABLE tbl_inventory (
    inventory_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '재고 ID', 
    book_id INT NOT NULL COMMENT '도서 ID',
    quantity INT NOT NULL COMMENT '수량',
    location VARCHAR(255) COMMENT '위치',
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '마지막 업데이트 일시',
    FOREIGN KEY (book_id) REFERENCES tbl_book(book_id)
) COMMENT '재고 테이블';

CREATE TABLE tbl_order (
    order_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '주문 ID',
    orderer_name VARCHAR(50) NOT NULL COMMENT '주문자 이름',
    orderer_address VARCHAR(100) NOT NULL COMMENT '배송지',
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '주문 날짜',
    status ENUM('주문확인중', '배송준비중', '발송완료', '배송중', '배송완료', '주문취소') DEFAULT '주문확인중' COMMENT '주문 상태'
) COMMENT '주문 테이블';

CREATE TABLE tbl_order_item (
    order_item_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '주문 항목 ID',
    order_id INT NOT NULL COMMENT '주문 ID',
    book_id INT NOT NULL COMMENT '도서 ID',
    quantity INT NOT NULL COMMENT '수량',
    FOREIGN KEY (order_id) REFERENCES tbl_order(order_id),
    FOREIGN KEY (book_id) REFERENCES tbl_book(book_id)
) COMMENT '주문 항목 테이블';


-- tbl_user 테이블 샘플 데이터
INSERT INTO tbl_user (username, password, role) VALUES
('관리자', 'password123', 'admin'),
('도서관리자1', 'password123', 'manager'),
('직원1', 'password123', 'employee'),
('직원2', 'password123', 'employee'),
('직원3', 'password123', 'employee'),
('직원4', 'password123', 'employee'),
('직원5', 'password123', 'employee'),
('직원6', 'password123', 'employee'),
('직원7', 'password123', 'employee'),
('직원8', 'password123', 'employee'),
('직원9', 'password123', 'employee'),
('직원10', 'password123', 'employee'),
('직원11', 'password123', 'employee'),
('직원12', 'password123', 'employee'),
('직원13', 'password123', 'employee'),
('직원14', 'password123', 'employee'),
('직원15', 'password123', 'employee'),
('직원16', 'password123', 'employee'),
('직원17', 'password123', 'employee'),
('직원18', 'password123', 'employee'),
('직원19', 'password123', 'employee'),
('직원20', 'password123', 'employee'),
('직원21', 'password123', 'employee'),
('직원22', 'password123', 'employee'),
('직원23', 'password123', 'employee'),
('직원24', 'password123', 'employee'),
('직원25', 'password123', 'employee'),
('직원26', 'password123', 'employee'),
('직원27', 'password123', 'employee'),
('직원28', 'password123', 'employee');

-- tbl_book 테이블 샘플 데이터
INSERT INTO tbl_book (title, author, description, price, category) VALUES
('데이터베이스 관리', '홍길동', '데이터베이스에 관한 기본 이론', 25000, '컴퓨터'),
('자바 프로그래밍', '김철수', '자바 언어의 기초부터 고급까지', 30000, '컴퓨터'),
('알고리즘 문제해결 전략', '이영희', '다양한 알고리즘 문제를 해결하는 방법', 27000, '컴퓨터'),
('머신러닝 입문', '박민수', '머신러닝의 기본 개념과 실습', 32000, '인공지능'),
('딥러닝 실전', '최영희', '딥러닝 모델의 구현과 응용', 35000, '인공지능'),
('파이썬 완벽 가이드', '정수진', '파이썬 프로그래밍의 모든 것', 28000, '컴퓨터'),
('네트워크 해킹과 보안', '김영철', '네트워크 해킹 기법과 보안 방법', 26000, '컴퓨터'),
('리눅스 커맨드라인 완벽정리', '박지훈', '리눅스 명령어 정리와 활용', 29000, '컴퓨터'),
('운영체제의 이해', '조수미', '운영체제의 기본 원리와 작동 방식', 27000, '컴퓨터'),
('컴퓨터 구조와 원리', '이현우', '컴퓨터 구조의 기초와 동작 원리', 28000, '컴퓨터'),
('클라우드 컴퓨팅 개론', '정민수', '클라우드 컴퓨팅의 기본 개념과 서비스', 30000, '컴퓨터'),
('데이터 과학 개론', '홍민지', '데이터 과학의 이론과 실습', 32000, '데이터'),
('빅데이터 분석', '김성현', '빅데이터 분석 기법과 활용', 34000, '데이터'),
('통계학 입문', '최정민', '통계학의 기본 개념과 분석 방법', 25000, '데이터'),
('확률과 통계', '이유진', '확률 이론과 통계 방법론', 27000, '데이터'),
('수학의 정석', '박종민', '수학 기초부터 고급까지의 이론과 문제', 24000, '수학'),
('공업수학', '김민수', '공업수학의 기초와 응용', 26000, '수학'),
('선형대수학', '이준호', '선형대수의 이론과 실습', 28000, '수학'),
('미적분학', '정은혜', '미적분학의 기본 개념과 응용', 30000, '수학'),
('물리학의 기본', '홍석현', '물리학의 기초 이론과 응용', 32000, '과학'),
('화학의 원리', '김지수', '화학의 기본 개념과 실험', 31000, '과학'),
('생물학 입문', '이수경', '생물학의 기초 개념과 연구 방법', 29000, '과학'),
('지구과학 개론', '최영민', '지구과학의 이론과 현상', 27000, '과학'),
('환경과 생태', '정소영', '환경과 생태계의 이해', 26000, '과학'),
('우주론 입문', '박선영', '우주의 기원과 구조', 28000, '과학'),
('천문학의 기초', '김하늘', '천문학의 이론과 관측', 30000, '과학'),
('지리학 개론', '이도훈', '지리학의 이론과 실제', 31000, '과학'),
('고대사 연구', '정재훈', '고대사의 연구와 이해', 25000, '역사'),
('중세사 개론', '최수진', '중세사 연구와 분석', 27000, '역사'),
('근현대사', '박진수', '근현대사의 이해와 연구', 30000, '역사');

-- tbl_inventory 테이블 샘플 데이터
INSERT INTO tbl_inventory (book_id, quantity, location) VALUES
((SELECT book_id FROM tbl_book WHERE title='데이터베이스 관리'), 100, '창고1'),
((SELECT book_id FROM tbl_book WHERE title='자바 프로그래밍'), 50, '창고1'),
((SELECT book_id FROM tbl_book WHERE title='알고리즘 문제해결 전략'), 150, '창고2'),
((SELECT book_id FROM tbl_book WHERE title='머신러닝 입문'), 200, '창고2'),
((SELECT book_id FROM tbl_book WHERE title='딥러닝 실전'), 80, '창고3'),
((SELECT book_id FROM tbl_book WHERE title='파이썬 완벽 가이드'), 60, '창고3'),
((SELECT book_id FROM tbl_book WHERE title='네트워크 해킹과 보안'), 90, '창고1'),
((SELECT book_id FROM tbl_book WHERE title='리눅스 커맨드라인 완벽정리'), 120, '창고1'),
((SELECT book_id FROM tbl_book WHERE title='운영체제의 이해'), 130, '창고2'),
((SELECT book_id FROM tbl_book WHERE title='컴퓨터 구조와 원리'), 70, '창고2'),
((SELECT book_id FROM tbl_book WHERE title='클라우드 컴퓨팅 개론'), 110, '창고3'),
((SELECT book_id FROM tbl_book WHERE title='데이터 과학 개론'), 140, '창고3'),
((SELECT book_id FROM tbl_book WHERE title='빅데이터 분석'), 160, '창고1'),
((SELECT book_id FROM tbl_book WHERE title='통계학 입문'), 180, '창고1'),
((SELECT book_id FROM tbl_book WHERE title='확률과 통계'), 190, '창고2'),
((SELECT book_id FROM tbl_book WHERE title='수학의 정석'), 170, '창고2'),
((SELECT book_id FROM tbl_book WHERE title='공업수학'), 200, '창고3'),
((SELECT book_id FROM tbl_book WHERE title='선형대수학'), 210, '창고3'),
((SELECT book_id FROM tbl_book WHERE title='미적분학'), 220, '창고1'),
((SELECT book_id FROM tbl_book WHERE title='물리학의 기본'), 230, '창고1'),
((SELECT book_id FROM tbl_book WHERE title='화학의 원리'), 240, '창고2'),
((SELECT book_id FROM tbl_book WHERE title='생물학 입문'), 250, '창고2'),
((SELECT book_id FROM tbl_book WHERE title='지구과학 개론'), 260, '창고3'),
((SELECT book_id FROM tbl_book WHERE title='환경과 생태'), 270, '창고3'),
((SELECT book_id FROM tbl_book WHERE title='우주론 입문'), 280, '창고1'),
((SELECT book_id FROM tbl_book WHERE title='천문학의 기초'), 290, '창고1'),
((SELECT book_id FROM tbl_book WHERE title='지리학 개론'), 300, '창고2'),
((SELECT book_id FROM tbl_book WHERE title='고대사 연구'), 310, '창고2'),
((SELECT book_id FROM tbl_book WHERE title='중세사 개론'), 320, '창고3'),
((SELECT book_id FROM tbl_book WHERE title='근현대사'), 330, '창고3');

-- tbl_order 테이블 샘플 데이터
INSERT INTO tbl_order (orderer_name, orderer_address, order_date, status) VALUES
('김철수', '서울시 강남구 역삼동 123-45', '2024-05-29 14:05:10', '배송준비중'),
('이영희', '부산시 해운대구 우동 678-90', '2024-05-28 14:05:10', '발송완료'),
('박민수', '대구시 수성구 범어동 111-22', '2024-05-27 14:05:10', '배송완료'),
('최지훈', '인천시 남동구 구월동 333-44', '2024-05-26 14:05:10', '주문취소'),
('장수진', '광주시 북구 용봉동 555-66', '2024-05-25 14:05:10', '주문확인중'),
('홍길동', '서울시 서초구 서초동 101-23', '2024-05-24 14:05:10', '배송준비중'),
('이민호', '부산시 남구 대연동 456-78', '2024-05-23 14:05:10', '발송완료'),
('김영희', '대전시 유성구 봉명동 999-88', '2024-05-22 14:05:10', '배송완료'),
('정수현', '울산시 남구 삼산동 222-33', '2024-05-21 14:05:10', '주문취소'),
('박지성', '수원시 팔달구 인계동 444-55', '2024-05-20 14:05:10', '주문확인중');


-- tbl_order_item 테이블 샘플 데이터
INSERT INTO tbl_order_item (order_id, book_id, quantity) VALUES
(1, 1, 2),
(1, 3, 4),
(2, 1, 4),
(2, 4, 4),
(3, 1, 3),
(3, 2, 2),
(4, 1, 1),
(4, 5, 1),
(5, 1, 4),
(5, 2, 4),
(6, 10, 2),
(7, 3, 1),
(7, 4, 2),
(8, 13, 4),
(8, 14, 2),
(9, 12, 3),
(9, 13, 3),
(9, 14, 3),
(10, 1, 3),
(10, 2, 4);
