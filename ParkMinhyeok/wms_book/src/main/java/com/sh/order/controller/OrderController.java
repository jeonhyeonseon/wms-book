package com.sh.order.controller;

import com.sh.order.model.dto.OrderDto;
import com.sh.order.model.service.OrderService;

public class OrderController {
    private OrderService orderService = new OrderService();
    public void createOrder(OrderDto order) {
        try {
            int result = orderService.createOrder(order);
            System.out.println(result == 1 ? "주문 성공" : "주문 실패");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
