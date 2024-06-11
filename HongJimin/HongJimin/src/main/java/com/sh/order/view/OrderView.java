package com.sh.order.view;

import com.sh.book.controller.BookController;
import com.sh.order.controller.OrderController;
import com.sh.order.model.entity.Order;
import com.sh.order.model.entity.OrderItem;
import com.sh.order.model.entity.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderView {
    Scanner sc = new Scanner(System.in);
    OrderController orderController = new OrderController();
    BookController bookController = new BookController();

    public void orderMenu() {

        List<OrderItem> orderItemList = new ArrayList<>();

        while (true) {
            //도서 리스트 출력
            bookController.findAllBook();

            String orderMenu = """
                ==============================
                   📑 도서 주문서를 작성합니다. 📑
                ==============================""";
            System.out.println(orderMenu);

            System.out.print("▶ 도서 아이디 입력 : ");
            int bookId = sc.nextInt();

            // 선택한 도서 아이디의 책 정보 출력
            bookController.findBookByBookId(bookId);

            // 수량 선택
            System.out.print("▶ 수량 입력 : ");
            int amount = sc.nextInt();

            OrderItem orderItem = new OrderItem(bookId, amount);
            orderItemList.add(orderItem);

            System.out.print("도서를 추가 주문 하시겠습니까? (y/n) : ");
            if(sc.next().toLowerCase().charAt(0) != 'y')
                break;
        }

        //주문자 정보 입력
        System.out.println("▶ 주문자 이름 입력 : ");
        String name = sc.next();
        sc.nextLine();
        System.out.println("▶ 배송지 입력 : ");
        String address = sc.nextLine();

        System.out.println("📖 도서 주문 등록합니다.📖");
        Order order = new Order(0, name, address, LocalDateTime.now(), Status.배송준비중, orderItemList);

        orderController.createOrder(order);
    }
}


