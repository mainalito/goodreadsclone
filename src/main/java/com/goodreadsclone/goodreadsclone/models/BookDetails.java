package com.goodreadsclone.goodreadsclone.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookDetails {
   private Description description;
    private List<String> subjects;
    private String title;
    private List<Integer> covers;
    private String key;
    private String coverUrl;
    
    
}
