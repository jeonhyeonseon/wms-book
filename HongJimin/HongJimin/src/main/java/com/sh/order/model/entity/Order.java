package com.sh.order.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order {
    private int orderId;
    private String ordererName;
    private String ordererAddress;
    private LocalDateTime orderDate;
    private Status status;

    private List<OrderItem> orderItemList;


    public Order(int orderId, String ordererName, String ordererAddress, LocalDateTime orderDate, Status status) {
        this.orderId = orderId;
        this.ordererName = ordererName;
        this.ordererAddress = ordererAddress;
        this.orderDate = orderDate;
        this.status = status;
    }
}
