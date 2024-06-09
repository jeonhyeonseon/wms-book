package com.sh.order.model.dao;

import com.sh.order.model.dto.OrderDto;

import java.util.List;

public interface OrderMapper {
    int insertOrder(OrderDto orderDto);
    int insertOrderItem(OrderDto orderDto);

    OrderDto findByOrderId(int orderId);
}
