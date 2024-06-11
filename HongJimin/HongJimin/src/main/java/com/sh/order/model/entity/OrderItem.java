package com.sh.order.model.entity;

import com.sh.book.model.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderItem {
    private int orderItemId;
    private int orderId;
    private int bookId;
    private int quantity;

    private Book book;

    public OrderItem(int bookId, int quantity) {
        this.bookId = bookId;
        this.quantity = quantity;
    }
}
