package com.goodreadsclone.goodreadsclone.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class publisher {
    private String language;
    private String author_key;
    private String author_name;
}
