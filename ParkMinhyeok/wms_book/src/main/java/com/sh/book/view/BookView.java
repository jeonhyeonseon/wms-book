package com.sh.book.view;

import com.sh.book.controller.BookController;
import com.sh.book.model.dto.BookDto;

import java.time.LocalDateTime;
import java.util.Scanner;

public class BookView {
    private BookController bookController = new BookController();
    Scanner sc = new Scanner(System.in);
    public void bookMenu() {
        String menu = """
                ===========================================
                도서 관리 메뉴
                1. 도서 등록
                2. 도서 조회
                3. 도서 수정
                4. 도서 삭제
                0. 뒤로가기
                ===========================================
                """;
        System.out.println(menu);
        String choice = sc.next();
        switch (choice) {
            case "1" :
                insertBookMenu();
                break;
            case "2" :
                findMenu();
                break;
            case "3" :
                updateBookMenu();
                break;
            case "4" :
                deleteBookMenu();
                break;
            case "0" :
                return;
            default:
                System.out.println("잘못된 입력입니다.");
        }
    }
    private void deleteBookMenu() {
        String menu = """
                ===========================================
                도서 삭제
                ===========================================
                """;
        System.out.println(menu);
        System.out.println("삭제할 도서 ID를 입력해주세요.");
        int bookId = sc.nextInt();
        bookController.deleteBook(bookId);
    }

    private void insertBookMenu() {
        String menu = """
                ===========================================
                도서 등록
                ===========================================
                """;
        System.out.println(menu);
        System.out.println("Title");
        String title = sc.next();
        System.out.println("Author");
        String author = sc.next();
        System.out.println("Description");
        String desc = sc.next();
        System.out.println("Price");
        int price = sc.nextInt();
        System.out.println("Category");
        String category = sc.next();
        LocalDateTime createdAt = LocalDateTime.now();
        BookDto book = new BookDto(null, title, author, desc, price, category, createdAt);
        bookController.insertBook(book);
    }
    private void updateBookMenu() {
        String menu = """
                ===========================================
                도서 수정
                ===========================================
                """;
        System.out.println(menu);
        System.out.println("수정할 책 ID");
        int bookId = sc.nextInt();
        System.out.println("Title");
        String title = sc.next();
        System.out.println("Author");
        String author = sc.next();
        System.out.println("Description");
        String desc = sc.next();
        System.out.println("Price");
        int price = sc.nextInt();
        System.out.println("Category");
        String category = sc.next();
        BookDto book = new BookDto(title, author, desc, price, category);
        bookController.updateBook(book, bookId);
    }
    private void findMenu() {
        String menu = """
                ===========================================
                도서 조회 메뉴
                1. 도서 전체 조회
                2. ID로 조회
                3. 도서 이름으로 조회
                4. 도서 저자로 조회
                5. 도서 카테고리로 조회
                0. 뒤로가기
                ===========================================
                """;
        System.out.println(menu);
        String choice = sc.next();
        switch (choice) {
            case "1" :
                bookController.findAll();
                break;
            case "2" :
                System.out.println("ID 입력 : ");
                int id = sc.nextInt();
                bookController.findByBookId(id);
                break;
            case "3" :
                System.out.println("도서 이름 입력 : ");
                String title = sc.next();
                bookController.findByBookTitle(title);
                break;
            case "4" :
                System.out.println("저자 이름 입력 : ");
                String author = sc.next();
                bookController.findByAuthor(author);
                break;
            case "5" :
                System.out.println("카테고리 입력 : ");
                String category = sc.next();
                bookController.findByCategory(category);
                break;
            case "0" :
                return;
            default:
                System.out.println("잘못된 입력입니다.");
        }
    }
}
