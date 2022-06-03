package com.goodreadsclone.goodreadsclone.controller;

import java.time.LocalDate;

import com.goodreadsclone.goodreadsclone.entities.Book;
import com.goodreadsclone.goodreadsclone.entities.BookRepository;
import com.goodreadsclone.goodreadsclone.entities.UserBookIds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserBookController {

    @Autowired
      BookRepository bookRepository;
   
    @PostMapping("/addUserBook")
    public ModelAndView addBookForUser(@RequestBody MultiValueMap<String,String> formData, 
      @AuthenticationPrincipal OAuth2User principal){
        
        if(principal==null || principal.getAttribute("login")==null){
          return null;
        }
        Book book = new Book();
        String userId = principal.getAttribute("login");
        String startDate =formData.getFirst("startDate");
        String endDate = formData.getFirst("endDate");
        String rating = formData.getFirst("rating");
        String readingStatus = formData.getFirst("readingStatus");
        String bookId = formData.getFirst("bookId").replace("/works/", "");
        String title = formData.getFirst("bookName");
        String coverUrl = formData.getFirst("coverImage");
        
        book.setStartDate(LocalDate.parse(startDate));
        book.setEndDate(LocalDate.parse(endDate));
        book.setRating(Integer.parseInt(rating));
        book.setReadingStatus(readingStatus);
        book.setBookId(bookId);
        book.setUserId(userId);
        book.setBookName(title);
        book.setCoverUrl(coverUrl);
        System.out.println(coverUrl);
   
        bookRepository.save(book);
        return new ModelAndView("redirect:/book/" + bookId);
    }
}
