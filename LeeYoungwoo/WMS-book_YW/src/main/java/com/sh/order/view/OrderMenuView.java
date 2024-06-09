package com.sh.order.view;

import com.sh.order.controller.OrderController;
import com.sh.order.model.dto.OrderDto;
import com.sh.user.view.MainMenuView;

import java.time.LocalDateTime;
import java.util.Scanner;

public class OrderMenuView {
    private Scanner sc = new Scanner(System.in);
    private OrderController orderController = new OrderController();
    public void orderMenu(){
        String orderMenu = """
                ===================================================
                📄 주문관리 메뉴 📄
                ===================================================
                1. 주문 등록
                2. 주문 상태 관리
                0. 돌아가기
                ===================================================
                입력 : """;
        while (true) {
            System.out.print(orderMenu);
            String choice = sc.next();
            switch (choice) {
                case "1" : orderController.createOrder(order()); break;
                case "2" : break;
                case "0" : return;
                default :
                    System.out.println("잘못 입력하셨습니다...");
            }
        }
    }

    private OrderDto order() {
        System.out.println("📨 도서 주문서 📨");
        System.out.println("-----------------------------------------------");
        System.out.print("주문자 입력 : ");
        String ordererName = sc.next();
        sc.nextLine();
        System.out.print("배송지 입력 : ");
        String ordererAddress = sc.nextLine();
        LocalDateTime orderDate = LocalDateTime.now();
        System.out.println("주문일 : " + orderDate);
        System.out.println();
        System.out.println("📨 주문 목록 📨");
        System.out.println("-----------------------------------------------");
        System.out.println("도서명 입력 : ");
        String title = sc.nextLine();
        System.out.println("수량 입력 : ");
        int quantity = sc.nextInt();
        return new OrderDto();
    }
}
