package com.goodreadsclone.goodreadsclone.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "book_by_user_and_bookid")
public class Book {

    @Id
    private String bookId;
    private String readingStatus;
    private LocalDate startDate;
    private LocalDate endDate;
    private int rating;

}
