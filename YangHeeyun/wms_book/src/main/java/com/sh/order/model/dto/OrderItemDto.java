package com.sh.order.model.dto;

import lombok.*;

@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 모든 매개인자 생성자
@Getter
@Setter
@ToString

public class OrderItemDto {
    private int orderItemId;
    private int orderId;
    private int bookId;
    private int quantity;
}
