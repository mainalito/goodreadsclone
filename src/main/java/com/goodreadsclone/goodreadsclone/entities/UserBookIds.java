package com.goodreadsclone.goodreadsclone.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserBookIds implements Serializable {
    
    private String bookId;
    private String userId;
	
}
