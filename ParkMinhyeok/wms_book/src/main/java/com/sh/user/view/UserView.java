package com.sh.user.view;

import com.sh.book.view.BookView;
import com.sh.inventory.view.InventoryView;
import com.sh.order.view.OrderView;
import com.sh.user.AuthManager;
import com.sh.user.controller.UserController;
import com.sh.user.model.dto.UserDto;

import java.util.Scanner;

public class UserView {
    Scanner sc = new Scanner(System.in);
    private UserController userController = new UserController();
    private BookView bookView = new BookView();
    private InventoryView inventoryView = new InventoryView();
    private OrderView orderView = new OrderView();

    public void loginMenu() {
        String menu =
                """
                        =================
                        1. 로그인
                        2. 회원가입
                        0. 종료
                        """;
        while (true) {
            if (AuthManager.isLogin()) {
                managementMenu();
                continue;
            }
            System.out.println(menu);
            String choice = sc.next();
            switch (choice) {
                case "1":
                    System.out.print("회원번호 : ");
                    int id = sc.nextInt();
                    System.out.print("비밀번호 : ");
                    String password = sc.next();
                    AuthManager.login(id, password);
                    if (AuthManager.isLogin()) {
                        System.out.println("로그인 성공");
                        managementMenu();
                    } else {
                        System.out.println("로그인 실패. 다시 시도해주세요.");
                    }
                    break;
                case "2":
                    System.out.println("회원가입");
//                        sighUp();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }

    private void managementMenu() {
        String menu = """
                ===========================================
                메뉴를 선택해주세요.
                1. 도서관리
                2. 재고관리
                3. 주문관리
                0. 로그아웃
                ===========================================
                                                           
                """;
        System.out.println(menu);
        String choice = sc.next();
        switch (choice) {
            case "1":
                bookView.bookMenu();
                break;
            case "2":
                inventoryView.inventoryMenu();
                break;
            case "3":
                orderView.orderMenu();
                break;
            case "0":
                System.out.println("로그아웃 합니다.");
                AuthManager.logout();
                break;
            default:
                System.out.println("잘못된 입력입니다.");
                break;
        }
    }
}
