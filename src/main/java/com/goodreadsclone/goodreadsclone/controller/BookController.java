package com.goodreadsclone.goodreadsclone.controller;

import java.util.Optional;

import com.goodreadsclone.goodreadsclone.entities.Book;
import com.goodreadsclone.goodreadsclone.entities.BookRepository;
import com.goodreadsclone.goodreadsclone.entities.UserBookIds;
import com.goodreadsclone.goodreadsclone.models.BookDetails;
import com.goodreadsclone.goodreadsclone.models.docs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import reactor.core.publisher.Mono;

@Controller
public class BookController {
    @Autowired
    BookRepository bookRepository;
    private final String BOOK_URL = "https://openlibrary.org/works/";
    private final WebClient bookClient;
    private String COVER_URL = "https://covers.openlibrary.org/b/id/";

    public BookController(WebClient.Builder webClientBuilder) {
        this.bookClient = webClientBuilder.baseUrl(BOOK_URL).build();
    }

    @GetMapping("/book/{id}")
    public String bookDetails(Model model, @PathVariable("id") String id,
            @AuthenticationPrincipal OAuth2User principal) {
        // docs bookDocs = new docs();
        BookDetails bookDetails = new BookDetails();
        // id = "OL26124262W";
        bookDetails.setKey(id);
        String bookId = bookDetails.getKey();
        String coverImageUrl = "https://www.publicdomainpictures.net/pictures/280000/velka/not-found-image-15383864787lu.jpg";
        Mono<BookDetails> bookDetailsMono = this.bookClient.get()
        .uri(UriBuilder -> UriBuilder.path(bookId + ".json").build()).retrieve()
        .bodyToMono(BookDetails.class);
        bookDetails = bookDetailsMono.block();
        if (bookDetails != null) {
            if (bookDetails.getCovers() != null && bookDetails.getCovers().size() > 0) {
                coverImageUrl = COVER_URL + bookDetails.getCovers().get(0) + "-L.jpg";
            }
            
                String userId = principal.getAttribute("login");
                bookDetails.setCoverUrl(coverImageUrl);
                System.out.println(userId + "======" + bookId );
                Optional<Book> userBook = bookRepository.findById(new UserBookIds(bookId,userId));
                if (userBook.isPresent()) {
                    model.addAttribute("userBooks", userBook.get());
                } else {
                    model.addAttribute("userBooks", new Book());
                }
                model.addAttribute("loginId", principal.getAttribute("login"));
                model.addAttribute("book", bookDetails);
                model.addAttribute("coverImage", coverImageUrl);
            
        }

        return "book-details";

    }
    @GetMapping("/dummy")
	public String getAccounts(Model model){
		model.addAttribute("dummy",bookRepository.findById
        (new UserBookIds("OL527464W", "mainalito")).get());
         return "dummy";
	}
}
