package com.sh.book.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {
    private int bookId;
    private String title;
    private String author;
    private String description;
    private int price;
    private String category;
    private LocalDateTime createdAt;



}
