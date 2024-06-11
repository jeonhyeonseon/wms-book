package com.sh.order.service;

import com.sh.order.model.dao.OrderMapper;
import com.sh.order.model.dto.OrderDto;
import com.sh.order.model.dto.OrderItemDto;
import org.apache.ibatis.session.SqlSession;

import static com.sh.common.MyBatisTemplate.getSqlSession;

public class OrderService {

    public int insertOrder(OrderDto orderDto) {
        SqlSession sqlSession = getSqlSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        try {
            // 트랜잭션 처리코드
            // 1건의 주문등록
            int result = orderMapper.insertOrder(orderDto);

            // n건의 주문 등록
            for(OrderItemDto orderItemDto : orderDto.getOrderItemList()) {
                orderItemDto.setOrderId(orderDto.getOrderId());
                result = orderMapper.insertOrderItem(orderItemDto);
            }
            sqlSession.commit();
            return result;
        } catch(Exception e){
            sqlSession.rollback();
            throw new RuntimeException();
       } finally {
            sqlSession.rollback();
        }
    }
}
