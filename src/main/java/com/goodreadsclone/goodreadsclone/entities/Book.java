package com.goodreadsclone.goodreadsclone.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(UserBookIds.class)
@Entity
@Table(name = "book_by_user_and_bookid")
public class Book implements Serializable{

    @Id
    private String userId;
    @Id
    private String bookId;

    private String bookName;
   
    private String readingStatus;
    private LocalDate startDate;
    private LocalDate endDate;
    private int rating;

   
    private String coverUrl;
    

}
