package com.sh.order.model.dao;

import com.sh.order.model.dto.OrderDto;
import com.sh.order.model.dto.OrderItemDto;
import com.sh.order.model.dto.Status;

import java.util.List;

public interface OrderMapper {
    int insertOrder(OrderDto orderDto);
    int insertOrderItem(OrderItemDto orderItemDto);

    // 주문상태별 주문 정보 조회
    List<OrderDto> findOrderByStatus(Status status);

    // 주문번호로 주문 정보 조회
    OrderDto findByOrderId(int orderId);
}
