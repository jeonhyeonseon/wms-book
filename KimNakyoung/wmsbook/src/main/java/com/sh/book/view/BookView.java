package com.sh.book.view;

import com.sh.book.controller.BookController;
import com.sh.book.model.dto.BookDto;

import java.sql.Timestamp;
import java.util.Scanner;


// 1. (도서 등록) 도서 정보 등록 2. (도서 수정)도서 정보 수정 (도서 코드, 제목, 저자, 설명, 가격, 카테고리 등)
// 3. (도서조회) 도서 목록 조회 4. (도서검색) 도서 검색 (도서명, 저자, 카테고리, 가격 등 기준)"
public class BookView {
    private BookController bookController = new BookController();

    private Scanner sc = new Scanner(System.in);

    public void mainBookMenu() {
        String bookMenu = """
                🐤도서관리🐤
                원하는 기능을 선택해주세요.
                =====================
                1.도서 등록
                2.도서 정보 수정
                3.도서 삭제
                4.도서 조회 및 검색
                0.메인 메뉴로 돌아가기
                =====================
                입력:""";
        while (true) {
            System.out.print(bookMenu);
            String choice = sc.next();
            sc.nextLine();// 전에 next()로 번호를 받아서 개행 없애야함.
            switch (choice) {
                case "1":
                    bookController.insertBook(inputBook());
                    break;
                case "2":
                    bookController.updateBook(inputBookUpdqted());
                    break;
                case "3":
                    bookController.deleteBook(inputBookId("삭제"));
                    break;
                case "4":
                    //전체 목록을 조회
//                    도서 아이디를 입력하고, 해당 도서 1건을 조회
                    //도서명, 저자, 카테고리, 가격 등을 기준으로 검색할수 있습니다.
                    searchBookMenu();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("잘못 입력 하셨습니다.");
            }
        }
    }

    //도서 조회 및 검색뷰
    private void searchBookMenu() {
        String bookMenu = """
                🐤도서 조회 및 검색🐤
                원하는 기능을 선택해주세요.
                =====================
                1.전체 도서목록 조회
                2.도서아이디로 조회
                3.도서 상세 검색
                0.도서관리 메뉴로 돌아가기
                =====================
                입력:""";
        System.out.println("원하는 메뉴를 눌러주세요.");
        while (true) {
            System.out.print(bookMenu);
            String choice = sc.next();
            sc.nextLine();// 전에 next()로 번호를 받아서 개행 없애야함.
            switch (choice) {
                case "1":
                    //전체 목록을 조회
                    bookController.findAll();
                    break;
                case "2":
//                    도서 아이디를 입력하고, 해당 도서 1건을 조회
                    bookController.findByBookId(inputBookId("조회"));
                    break;
                case "3":
                    //도서명, 저자, 카테고리, 가격 등을 기준으로 검색할수 있습니다.
                    detailSearchBookView();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("잘못 입력 하셨습니다.");
            }

        }

    }

    //도서명, 저자, 카테고리, 가격 등을 기준으로 검색할수 있습니다.
    private void detailSearchBookView() {
        String bookMenu = """
                🐣도서 상세 검색🐣
                무엇으로 책을 검색하고 싶으세요?
                =====================
                1.도서명
                2.저자
                3.카테고리
                4.가격
                0.도서 조회 및 검색으로 돌아가기
                =====================
                입력:""";
        while (true) {
            System.out.print(bookMenu);
            String choice = sc.next();
            sc.nextLine();// 전에 next()로 번호를 받아서 개행 없애야함.
            switch (choice) {
                case "1":
                    //도서명
                    bookController.findByTitle(inputString("도서명"));
                    break;
                case "2":
                    //저자
                    bookController.findByAuthor(inputString("저자"));
                    break;
                case "3":
                    //카테고리
                    bookController.findByCategory(inputString("카테고리"));
                    break;
                case "4":
                    //가격
                    bookController.findByPrice(inputInt("가격"));
                    break;
                case "0":
                    return;
                default:
                    System.out.println("잘못 입력 하셨습니다.");
            }

        }
    }

    // 도서 아이디를 입력하고, 해당 도서 1건을 조회 (int용 입력)
    private int inputBookId(String type) {
        System.out.printf("> %s할 도서코드 : ", type);
        return sc.nextInt();
    }

    // 도서 상세 검색중 도서명/카테고리/저자/로 검색 (String용 입력)
    private String inputString(String type) {
        System.out.printf("> 조회할 %s : ", type);

        return sc.nextLine(); // 검색하는거에 공백 있음 그래서 nextLine()씀.
    }

    // 도서 상세 검색중 가격으로 검색 ( int용 입력)
    private int inputInt(String type) {
        System.out.printf("> 조회할 %s : ", type);
        return sc.nextInt();
    }

    //도서를 등록하기
    private BookDto inputBook() {
        System.out.println("> === 등록할 도서정보를 작성해주세요. ===");
        System.out.print("도서명 : ");
        String title = sc.nextLine();
        System.out.print("저자 : ");
        String author = sc.nextLine();
        System.out.print("설명 : ");
        String description = sc.nextLine();
        System.out.print("가격 : ");
        int price = sc.nextInt();
        sc.nextLine(); // 개행 버리기
        System.out.print("카테고리를 입력해주세요 : ");
        String category = sc.nextLine();
//        Timestamp createdAt = new Timestamp(System.currentTimeMillis()); // 현재 시간

        return new BookDto(0,title,author,description,price,category,null);

    }

    //도서를 수정하기
//    도서수정은 도서아이디와 등록일을 제외한 모든 정보를 일괄수정합니다.
    private BookDto inputBookUpdqted() {
        System.out.println("> === 수정할 도서정보를 작성해주세요");
        System.out.print("도서코드 : ");
        int bookId = sc.nextInt();
        sc.nextLine(); // 개행 버리기
        System.out.print("도서명 : ");
        String title = sc.nextLine();
        System.out.print("저자 : ");
        String author = sc.nextLine();
        System.out.print("설명 : ");
        String description = sc.nextLine();
        System.out.print("가격 : ");
        int price = sc.nextInt();
        sc.nextLine(); // 개행 버리기
        System.out.print("카테고리를 입력해주세요 : ");
        String category = sc.nextLine();
//        Timestamp createdAt = new Timestamp(System.currentTimeMillis()); // 현재 시간
        // createdAt은 수정 할 필요가 없으니까 null
        return new BookDto(bookId,title,author,description,price,category,null);
    }







}
