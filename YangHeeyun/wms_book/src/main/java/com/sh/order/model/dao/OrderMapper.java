package com.sh.order.model.dao;

import com.sh.order.model.dto.OrderDto;
import com.sh.order.model.dto.OrderItemDto;

public interface OrderMapper {
    // 한 건의 주문등록
    int insertOrder(OrderDto orderDto);

    // 여러 건의 주문등록
    int insertOrderItem(OrderItemDto orderItemDto);
}

