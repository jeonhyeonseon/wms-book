package com.sh.book.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BookDto {
    private Integer bookId;
    private String title;
    private String author;
    private String description;
    private int price;
    private String category;
    private LocalDateTime createdAt;

    public BookDto(String title, String author, String description, int price, String category) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.price = price;
        this.category = category;
    }
}
