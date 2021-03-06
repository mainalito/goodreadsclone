package com.goodreadsclone.goodreadsclone.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import com.goodreadsclone.goodreadsclone.entities.Book;
import com.goodreadsclone.goodreadsclone.models.book;
import com.goodreadsclone.goodreadsclone.models.docs;

import reactor.core.publisher.Mono;

@Controller

public class SearchController {

    private final WebClient webclient;

    String COVER_URL = "https://covers.openlibrary.org/b/id/";
    String SEARCH_URL = "https://openlibrary.org/search.json";
    String BOOK_URL = "https://openlibrary.org/works/";

    public SearchController(WebClient.Builder webClientBuilder) {
        // extend limit for the buffer
        this.webclient = webClientBuilder.exchangeStrategies(ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs()
                        .maxInMemorySize(16 * 1024 * 1024))
                .build())
                .baseUrl(SEARCH_URL).build();

    }

  

    @GetMapping("/search")
    public String fetchBook(@RequestParam (value = "q", defaultValue = "", required = false) String q, Model model) {
        Book bookUserBook = new Book();
        Mono<book> bookMono = this.webclient.get().uri(UriBuilder -> UriBuilder.queryParam("q", q.replace(" ", "+"))
                .build()).retrieve().bodyToMono(book.class);
        book book = bookMono.block();

        List<docs> docsResults =  book.getDocs().stream().limit(15).peek(bookResult -> {
            bookResult.setKey(bookResult.getKey().replace("/works/", ""));
            String cover_id = bookResult.getCover_i();
            if (StringUtils.hasText(cover_id)) {
                cover_id = COVER_URL + cover_id + "-M.jpg";
            } else {
                cover_id = "https://www.publicdomainpictures.net/pictures/280000/velka/not-found-image-15383864787lu.jpg";
            }
            bookResult.setUrl(BOOK_URL + bookResult.getKey() + ".json");
            bookResult.setCover_i(cover_id);
            
        }).collect(Collectors.toList()) ;

        
        model.addAttribute("books", docsResults);
        return "search";

    }

}
