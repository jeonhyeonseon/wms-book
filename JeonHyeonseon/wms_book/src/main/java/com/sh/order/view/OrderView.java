package com.sh.order.view;

import com.sh.order.controller.OrderController;
import com.sh.order.model.dto.OrderDto;
import com.sh.order.model.dto.OrderItemDto;
import com.sh.order.model.dto.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.sh.order.model.dto.Status.*;

public class OrderView {
    private OrderController orderController = new OrderController();

    private Scanner sc = new Scanner(System.in);

    public void orderMenu() {
        String menu = """
                    [주문 메뉴 선택]
                =====================
                     1. 주문 생성
                     2. 주문 조회
                     3. 주문 상세 조회
                     0. 돌아가기
                =====================
                입력 : """;
        System.out.print(menu);
        String choice = sc.next();
        switch (choice) {
            case "1" :
                inputOrderBook();
                break;
            case "2" :
                searchOrder();
                break;
            case "3" :
                findByOrderId();
            case "0" : return;
            default:
                System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
        }
    }

    private void searchOrder() {
        String menu = """
                    [주문 상태 조회]
                =====================
                     1. 주문확인중
                     2. 배송준비중
                     3. 발송완료
                     4. 배송중
                     5. 배송완료
                     6. 주문취소
                =====================
                입력 : """;
        System.out.print(menu);
        String choice = sc.next();
        switch (choice) {
            case "1" : orderController.findOrderByStatus(Status.주문확인중); break;
            case "2" : orderController.findOrderByStatus(Status.배송준비중); break;
            case "3" : orderController.findOrderByStatus(Status.발송완료); break;
            case "4" : orderController.findOrderByStatus(Status.배송중); break;
            case "5" : orderController.findOrderByStatus(Status.배송완료); break;
            case "6" : orderController.findOrderByStatus(Status.주문취소); break;
        }
    }

    private void inputOrderBook() {
        System.out.println("👉 주문할 도서를 입력해주세요");
        System.out.println("  [ 주문자 정보 입력 ]");
        System.out.println("---------------------");
        System.out.print("이름 : ");
        String ordererName = sc.next();
        sc.nextLine();
        System.out.print("주소 : ");
        String ordererAddress = sc.nextLine();

        List<OrderItemDto> orderItemList = new ArrayList<>();

        while (true) {
            // 도서 아이디 선택
            System.out.print("도서 아이디 : ");
            int bookId = sc.nextInt();

            // 수량 선택
            System.out.print("수량 : ");
            int quantity = sc.nextInt();

            // OrderItem객체 처리
            orderItemList.add(new OrderItemDto(0, 0, bookId, quantity, null));

            // 추가 주문 여부
            System.out.print("추가적으로 주문하시겠습니까? (y/n) : ");
            if(sc.next().toUpperCase().charAt(0) != 'y') {
                break;
            }
            // 확인용
            System.out.println(bookId + " " + quantity + " ");
        }
        // 확인용
        System.out.println(ordererName + " " +  ordererAddress + " ");
        // 주문요청 (OrderController 메시지 전달)
        OrderDto orderDto = new OrderDto(0, ordererName,ordererAddress, null, 주문확인중, orderItemList);
        orderController.createOrder(orderDto);
        System.out.println("주문번호 : " + orderDto.getOrderId());
    }

    private void findByOrderId() {
        System.out.println("   [ 주문 상세 조회 ] ");
        System.out.println("---------------------");
        System.out.print("주문 번호 : ");
        int orderId = sc.nextInt();
        sc.nextLine();

        OrderDto orderDto = orderController.findByOrderId(orderId);
        if (orderDto == null) {
            System.out.println("해당 상품의 주문번호가 존재하지 않습니다.");
        }
        ResultOrderView.displayOrderById(orderDto);
    }
}
