package com.sh.order.controller;

import com.sh.order.model.dto.OrderDto;
import com.sh.order.model.dto.Status;
import com.sh.order.model.service.OrderService;
import com.sh.order.view.ResultOrderView;

import java.util.List;

public class OrderController {
    private OrderService orderService = new OrderService();

    public void createOrder(OrderDto orderDto) {
        try {
            int result = orderService.createOrder(orderDto);
            ResultOrderView.displayOrderBook(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public OrderDto findByOrderId(int orderId) {
        return orderService.findByOrderId(orderId);
    }

    public void findOrderByStatus(Status status) {
        try {
            List<OrderDto> orderItemList = orderService.findOrderByStatus(status);
            ResultOrderView.displayOrderByStatus(orderItemList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
