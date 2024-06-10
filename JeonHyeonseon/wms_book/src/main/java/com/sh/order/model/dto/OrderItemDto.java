package com.sh.order.model.dto;

import com.sh.book.model.dto.BookDto;
import lombok.*;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
    private int orderItemId; // 주문항목 아이디
    private int orderId; // 주문 아이디
    private int bookId; // 도서 아이디
    private int quantity; // 수량

    private BookDto bookDto; // 책 제목을 입력하기 위해서 필요함
}
