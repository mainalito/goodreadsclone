package com.goodreadsclone.goodreadsclone.controller;

import com.goodreadsclone.goodreadsclone.models.book;
import com.goodreadsclone.goodreadsclone.models.docs;
import com.goodreadsclone.goodreadsclone.models.publisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

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
    

    // @GetMapping("/")
    // public String index() {
    //     return "index";
    // }
 

    @GetMapping("/search")
    public String fetchBook(@RequestParam (value = "q", defaultValue = "Think and grow rich", required = false) String q, Model model) {
  
        Mono<book> bookMono = this.webclient.get().uri(UriBuilder -> UriBuilder.queryParam("q", q.replace(" ", "+"))
                .build()).retrieve().bodyToMono(book.class);
        book book = bookMono.block();

        List<docs> docsResults =  book.getDocs().stream().limit(10).peek(bookResult -> {
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
        
        System.out.println(book.getNumFound());
        return "search";

    }

}
