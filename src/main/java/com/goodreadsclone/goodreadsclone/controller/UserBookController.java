package com.goodreadsclone.goodreadsclone.controller;

import java.time.LocalDate;

import com.goodreadsclone.goodreadsclone.entities.Book;
import com.goodreadsclone.goodreadsclone.entities.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
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
    public ModelAndView addBookForUser(@RequestBody MultiValueMap<String,String> formData){
    
        Book book = new Book();


        String startDate =formData.getFirst("startDate");
        String endDate = formData.getFirst("endDate");
        String rating = formData.getFirst("rating");
        String readingStatus = formData.getFirst("readingStatus");
        String bookId = formData.getFirst("bookId").replace("/works/", "");
        book.setBookId(bookId);
        book.setStartDate(LocalDate.parse(startDate));
        book.setEndDate(LocalDate.parse(endDate));
        book.setRating(Integer.parseInt(rating));
        book.setReadingStatus(readingStatus);

        bookRepository.save(book);
        return new ModelAndView("redirect:/book/" + bookId);
    }
}
