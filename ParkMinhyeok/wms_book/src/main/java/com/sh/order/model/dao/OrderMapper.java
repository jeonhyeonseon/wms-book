package com.sh.order.model.dao;

import com.sh.order.model.dto.OrderDto;
import com.sh.order_item.model.dto.OrderItemDto;

public interface OrderMapper {
    int insertOrder(OrderDto order);

    int insertOrderItem(OrderItemDto orderItem);
}
