package com.goodreadsclone.goodreadsclone.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class docs {
    private String key;
    private String title;
    private List<String> author_name;
    private String cover_i;
    private String number_of_pages_median;
    private String url;

}
