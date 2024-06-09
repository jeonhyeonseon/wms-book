package com.sh.book.model.dao;

import com.sh.book.model.dto.BookDto;
import org.apache.ibatis.session.SqlSession;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

import static com.sh.common.MyBatisTemplate.getSqlSession;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BookMapperTest {
    SqlSession sqlSession;
    BookMapper bookMapper;
    int insertedBookId; // 삽입된 책의 ID를 저장하기 위한 변수

    @BeforeEach
    void setUp() {
        this.sqlSession = getSqlSession();
        this.bookMapper = this.sqlSession.getMapper(BookMapper.class);
    }

    @AfterEach
    void tearDown() {
//        if (insertedBookId != 0) {
//            bookMapper.deleteBook(insertedBookId); // 삽입된 책 삭제
//        }
        this.sqlSession.commit();
        this.sqlSession.close();

    }

    @Test
    void findAll() {
        //given
        // 전체조회

        //when
        List<BookDto> list = bookMapper.findAll();
        //then
        assertThat(list)
                .isNotNull()
                .isNotEmpty();

    }

    @Test
    void findByBookId(){
        //given
        int bookId = 1;

        //when
        BookDto bookDto = bookMapper.findByBookId(bookId);

        //then
        assertThat(bookDto)
                .isNotNull();
    }

    @Test
    void findByTitle(){
        //given
        String title = "수학의 정석";

        //when
        List<BookDto> list = bookMapper.findByTitle(title);

        //then
        assertThat(list)
                .isNotNull()
                .allSatisfy((bookDto) -> {
                    assertThat(bookDto.getTitle()).isEqualTo(title);
                });
    }

    @Test
    void findByAuthor() {
        //given
        String author = "정재훈";
        //when
        List<BookDto> list = bookMapper.findByAuthor(author);
        //then
        assertThat(list)
                .isNotNull()
                .allSatisfy((bookDto) -> {
                    assertThat(bookDto.getAuthor()).isEqualTo(author);
                });

    }

    @Test
    void findByCategory() {
        //given
        String category = "역사";
        //when
        List<BookDto> list = bookMapper.findByCategory(category);
        //then
        assertThat(list)
                .isNotNull()
                .allSatisfy((bookDto) -> {
                    assertThat(bookDto.getCategory()).isEqualTo(category);
                });

    }

    @Test
    void findByPrice() {
        //given
        int price = 25000;
        //when
        List<BookDto> list = bookMapper.findByPrice(price);
        //then
        assertThat(list)
                .isNotNull()
                .allSatisfy((bookDto) -> {
                    assertThat(bookDto.getPrice()).isEqualTo(price);
                });

    }

    @Test
    void insertBook() {
        //given
        String title = "C언어본색";
        String author = "박정민";
        String description = "명강의가 일으키는 C언어의 기적";
        int price= 30000;
        String category = "컴퓨터";
        LocalDateTime beforeInsert = LocalDateTime.now();
        // bookId 는 auto , created_at now();
        BookDto bookDto = new BookDto(0,title,author,description,price,category,null);
        //when
        int result = bookMapper.insertBook(bookDto);
        //then
        assertThat(result).isEqualTo(1); // 한개의 레코드가 들어갔나?

        // 등록된 행의 pk
        int insertedBookId = bookDto.getBookId();
        assertThat(insertedBookId).isNotZero(); // auto로 넣으니깐 0이 아닌지 확인하기

        // 등록된 행을 조회해서 컬럼값 비교
        BookDto bookDto1 = bookMapper.findByBookId(insertedBookId); //데이터베이스에서 삽입된 책 정보를 조회하고, 삽입한 데이터와 일치하는지 확인
        assertThat(bookDto1.getBookId()).isEqualTo(insertedBookId);
        assertThat(bookDto1.getTitle()).isEqualTo(title);
        assertThat(bookDto1.getPrice()).isEqualTo(price);
        assertThat(bookDto1.getCategory()).isEqualTo(category);

        Timestamp createdAtTimestamp = bookDto1.getCreatedAt();
        LocalDateTime createdAt = createdAtTimestamp.toLocalDateTime();

        assertThat(createdAt).isAfter(beforeInsert);
    }

    @DisplayName("한건의 메뉴데이터를 삭제할 수 있다.")
    @Test
    void deleteBook() {
        //given
        String title = "C언어본색";
        String author = "박정민";
        String description = "명강의가 일으키는 C언어의 기적";
        int price= 30000;
        String category = "컴퓨터";
        // bookId 는 auto , created_at now();
        BookDto bookDto = new BookDto(0,title,author,description,price,category,null);
        bookMapper.insertBook(bookDto); // 위에 넣은 임의의 데이터 삽입
        int bookId = bookDto.getBookId();
        //when
        int result = bookMapper.deleteBook(bookId);
        //then
        assertThat(result).isEqualTo(1); // 한개의 레코드가 들어갔나?
        assertThat(bookMapper.findByBookId(bookId)).isNull(); // 방금 삭제 했으니까
    }

    @Test
    void updateBook(){

        //given
        String title = "C언어본색";
        String author = "박정민";
        String description = "명강의가 일으키는 C언어의 기적";
        int price= 30000;
        String category = "컴퓨터";
        BookDto bookDto = new BookDto(0,title,author,description,price,category,null);
        bookMapper.insertBook(bookDto); // 위에 넣은 임의의 데이터 삽입
        int bookId = bookDto.getBookId();

        //수정할 데이터
        String newTitle = "으뜸머신러닝";
        String newAuthor = "강영민";
        String newDescription = "머신러닝의 으뜸";
        int newPrice= 25000;
        String newCategory = "인공지능";
        bookDto.setTitle(newTitle);
        bookDto.setAuthor(newAuthor);
        bookDto.setDescription(newDescription);
        bookDto.setPrice(newPrice);
        bookDto.setCategory(newCategory);

        //when
        int result = bookMapper.updateBook(bookDto);

        //then
        assertThat(result).isEqualTo(1); // 레코드가 넣어졌나 확인

        BookDto bookDto1 = bookMapper.findByBookId(bookId); // book정보 찾기
        assertThat(bookDto1.getBookId()).isEqualTo(bookId);
        assertThat(bookDto1.getAuthor()).isEqualTo(newAuthor);
        assertThat(bookDto1.getDescription()).isEqualTo(newDescription);
        assertThat(bookDto1.getPrice()).isEqualTo(newPrice);
        assertThat(bookDto1.getCategory()).isEqualTo(newCategory);

    }


}