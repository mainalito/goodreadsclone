package com.goodreadsclone.goodreadsclone.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import com.goodreadsclone.goodreadsclone.entities.Book;
import com.goodreadsclone.goodreadsclone.entities.BookRepository;
import com.goodreadsclone.goodreadsclone.entities.UserBookIds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserBookController {

    @Autowired
      BookRepository bookRepository;
   
    @PostMapping("/addUserBook")
    public ModelAndView addBookForUser(@RequestBody MultiValueMap<String,String> formData, 
      @AuthenticationPrincipal OAuth2User principal, RedirectAttributes redirectAttributes){
        
        if(principal==null || principal.getAttribute("login")==null){
          return null;
        }
        Book book = new Book();
        String userId = principal.getAttribute("login");
        LocalDate startDate = LocalDate.parse(formData.getFirst("startDate"));
        LocalDate endDate = LocalDate.parse(formData.getFirst("endDate"));
        String rating = formData.getFirst("rating");
        String readingStatus = formData.getFirst("readingStatus");
        String bookId = formData.getFirst("bookId").replace("/works/", "");
        String title = formData.getFirst("bookName");
        String coverUrl = formData.getFirst("coverImage");
        
        //check if end-date is greater than the start-date
       if(startDate.isBefore(endDate)){

         book.setStartDate(startDate);
         book.setEndDate(endDate);
         book.setRating(Integer.parseInt(rating));
         book.setReadingStatus(readingStatus);
         book.setBookId(bookId);
         book.setUserId(userId);
         book.setBookName(title);
         book.setCoverUrl(coverUrl);
         System.out.println("CORRECT DATES");
        
         bookRepository.save(book);
       }
       else{
        System.out.println("WRONG DATES");
         redirectAttributes.addFlashAttribute("errorMessage", "Start Date should be before Completed Date");
       }
        return new ModelAndView("redirect:/book/" + bookId);
    }
}
