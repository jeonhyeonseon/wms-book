package com.sh.order.model.service;

import com.sh.order.model.dao.OrderMapper;
import com.sh.order.model.dto.OrderDto;
import com.sh.order.model.dto.OrderItemDto;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.sh.common.MyBatisTemplate.getSqlSession;

public class OrderService {
    public int createOrder(OrderDto orderDto) {
        SqlSession sqlSession = getSqlSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        try {
            // 1건 주문등록
            int result = orderMapper.insertOrder(orderDto);
            // n건 주문아이템 등록
            for (OrderItemDto orderItemDto : orderDto.getOrderItemList()) {
                // 발급된 도서아이디를 OrderItemDto#bookId 대입
                orderItemDto.setOrderId(orderDto.getOrderId());
                result = orderMapper.insertOrderItem(orderItemDto);
            }
            sqlSession.commit(); // 성공하면 commit
            return result;
        } catch (Exception e) {
            sqlSession.rollback(); // 실패하면 rollback
            throw new RuntimeException("주문실패!");
        } finally {
            sqlSession.close();
        }
    }

    public OrderDto findByOrderId(int orderId) {
        SqlSession sqlSession = getSqlSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        try {
            return orderMapper.findByOrderId(orderId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
