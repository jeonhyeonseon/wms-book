package com.sh.order.view;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import com.sh.order.model.dto.OrderDto;
import com.sh.order.model.dto.OrderItemDto;
import org.apache.ibatis.ognl.enhance.OrderedReturn;

import java.util.List;

public class ResultOrderView {

    public static void displayOrderBook(int result) {
        if (result > 0) {
            System.out.println("📦주문이 완료되었습니다📦");
        } else {
            System.out.println("🧐주문을 실패하였습니다. 다시 한 번 확인해주세요.🧐");
        }
    }

    public static void displayOrderByStatus(List<OrderDto> orderList) {
        if (orderList == null || orderList.isEmpty()) {
            System.out.println("해당 상태의 주문이 존재하지 않습니다.");
        } else {
            System.out.println("        [ 주문 정보 ] ");
            System.out.println("---------------------------");
            for (OrderDto orderDto : orderList) {
                System.out.println("주문 번호 : " + orderDto.getOrderId());
                System.out.println("주문자 : " + orderDto.getOrdererName());
                System.out.println("배송지 : " + orderDto.getOrdererAddress());
                System.out.println("주문일 : " + orderDto.getOrderDate());
                System.out.println("주문상태 : " + orderDto.getStatus());

                System.out.println(" [주문 목록] ");
                for (int i  = 0; i < orderDto.getOrderItemList().size(); i++) {
                    OrderItemDto orderItemDto = orderDto.getOrderItemList().get(i);
                    System.out.printf("%d %s (도서번호 %d번) %d권\n",
                            i+1, orderItemDto.getBookDto().getTitle(), orderItemDto.getBookId(), orderItemDto.getQuantity());
                }
                System.out.println("---------------------------");
            }
        }
    }

    public static void displayOrderById(OrderDto orderDto) {
        if (orderDto == null) {
            System.out.println("해당 주문 번호가 존재하지 않습니다.");
        } else {
            System.out.println("        [ 주문 정보 ] ");
            System.out.println("---------------------------");
            System.out.println("주문 번호 : " + orderDto.getOrderId());
            System.out.println("주문자 : " + orderDto.getOrdererName());
            System.out.println("배송지 : " + orderDto.getOrdererAddress());
            System.out.println("주문일 : " + orderDto.getOrderDate());
            System.out.println("---------------------------");
            System.out.println(" [주문 목록 ]");
            for (int i = 0; i < orderDto.getOrderItemList().size(); i++) {
                OrderItemDto orderItemDto = orderDto.getOrderItemList().get(i);
                System.out.printf("%d %s (도서번호 %d번) %d권\n",
                        i + 1, orderItemDto.getBookDto().getTitle(), orderItemDto.getBookId(), orderItemDto.getQuantity());
            }
        }
    }
}
