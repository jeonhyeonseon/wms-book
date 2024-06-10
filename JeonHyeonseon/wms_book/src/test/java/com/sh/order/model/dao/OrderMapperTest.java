package com.sh.order.model.dao;

import com.sh.order.model.dto.OrderDto;
import com.sh.order.model.dto.Status;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.List;

import static com.sh.common.MyBatisTemplate.getSqlSession;
import static com.sh.order.model.dto.Status.배송준비중;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderMapperTest {
    SqlSession sqlSession;
    OrderMapper orderMapper;

    @BeforeEach
    void setUp() {
        this.sqlSession = getSqlSession();
        this.orderMapper = this.sqlSession.getMapper(OrderMapper.class);
    }

    @AfterEach
    void tearDown() {
        this.sqlSession.close();
    }

    @DisplayName("1건의 도서 주문을 등록한다.(주문 아이템 없음)")
    @Test
    void insertOrder() {
        // given
        OrderDto orderDto = new OrderDto();
        orderDto.setOrdererName("김철수");
        orderDto.setOrdererAddress("서울시 강남구 역삼동 123-45");
        orderDto.setOrderDate(LocalDateTime.of(2024, 5, 29, 14, 5, 10));
        orderDto.setStatus(배송준비중);

        // when
        int result = orderMapper.insertOrder(orderDto);
        System.out.println(result);

        // then
        assertThat(orderDto).isNotNull();
        assertThat(orderDto.getOrdererName()).isEqualTo("김철수");
        assertThat(orderDto.getOrdererAddress()).isEqualTo("서울시 강남구 역삼동 123-45");
        assertThat(orderDto.getStatus()).isEqualTo(배송준비중);
    }

    @DisplayName("1건의 도서 아이템을 등록한다.")
    @Test
    void insertOrderItem() {
        // given

    }

    @DisplayName("주문상태로 주문 정보 조회")
    @Test
    void findOrderByStatus() {
        // given
        Status status = Status.배송준비중;
        // when
        List<OrderDto> orderList = orderMapper.findOrderByStatus(status);
        // then
        assertThat(orderList).isNotNull();
        assertThat(orderList).isNotEmpty();
        for (OrderDto orderDto : orderList) {
            assertThat(orderDto.getStatus()).isEqualTo(status);
        }
    }

    @DisplayName("주문번호로 주문 정보 조회")
    @Test
    void findOrderById() {
        // given
        int orderId = 1;
        // when
        OrderDto orderDto = orderMapper.findByOrderId(orderId);
        // then
        assertThat(orderDto).isNotNull();
        assertThat(orderDto.getOrderItemList()).isNotNull().isNotEmpty();
    }
}