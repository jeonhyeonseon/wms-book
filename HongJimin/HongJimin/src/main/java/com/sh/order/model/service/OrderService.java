package com.sh.order.model.service;

import com.sh.common.error.ErrorCode;
import com.sh.order.model.dao.OrderMapper;
import com.sh.order.model.entity.Order;
import com.sh.order.model.entity.OrderItem;
import com.sh.order.model.exception.CreateOrderTransactionException;
import org.apache.ibatis.session.SqlSession;

import static com.sh.common.MyBatisTemplate.getSqlSession;

public class OrderService {
    public int createOrder(Order order) {
        SqlSession sqlSession = getSqlSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);

        try {
            int result = orderMapper.insertOrder(order);

            for (OrderItem orderItem : order.getOrderItemList()) {
                orderItem.setOrderId(order.getOrderId());
                result = orderMapper.insertOrderItem(orderItem);
            }
            sqlSession.commit();
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            throw new CreateOrderTransactionException(ErrorCode.CREATE_ORDER_ERROR, e);
        } finally {
            sqlSession.close();
        }
    }

    public Order findOrderItem(int orderId) {
        SqlSession sqlSession = getSqlSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);

        try {
            Order orderResult = orderMapper.findOrderItem(orderId);
            sqlSession.commit();
            return orderResult;
        } catch (Exception e) {
            sqlSession.rollback();
            throw new CreateOrderTransactionException(ErrorCode.CREATE_SELECT_ORDER_ERROR, e);
        } finally {
            sqlSession.close();
        }
    }
}
