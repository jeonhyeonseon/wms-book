package com.sh.book.view;

import com.sh.book.model.dto.BookDto;

import java.util.List;

/**
 * 요청 결과 출력 메소드 모음 (static)
 */

public class BookResultView {

    // 여러건 검색결과
    public static void displayBookList(List<BookDto> list,String message) {
        if(list.isEmpty()) {
            System.out.println(">조회된 도서가 없습니다.");
        }
        else {
            System.out.println(message);
            System.out.println("-----------------------------------------------");
            for(BookDto bookDto : list) {
                System.out.println(bookDto.toString());
            }
            System.out.println("-----------------------------------------------");
        }
    }

    // 한건 검색결과

    public static void displayBook(BookDto bookDto) {
        if(bookDto == null) {
            System.out.println(">조회된 도서가 없습니다.");
        }
        else {
            System.out.println("-----------------------------------------------");
            System.out.println(bookDto.toString());
            System.out.println("-----------------------------------------------");
        }
    }

    public static void displayResult(String type, int result) {
        System.out.println(type + " " + (result > 0 ? "성공" : "실패!"));

    }

    public static void displayDeleteResult(String type, int result) {
        if (result > 0) {
            System.out.println(type + " 성공");
        } else if (result == -1) {
            System.out.println(type + " 실패! 해당 도서는 다른 기록에 참조되어 삭제할 수 없습니다.");
            System.out.println("31번 이상을 입력해주세요.");
        } else {
            System.out.println(type + " 실패!");
        }
    }
}
