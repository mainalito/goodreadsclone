package com.goodreadsclone.goodreadsclone.controller;

import java.util.List;

import com.goodreadsclone.goodreadsclone.entities.Book;
import com.goodreadsclone.goodreadsclone.entities.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    BookRepository bookRepository;
    @GetMapping("/")
    public String home(Model model,@AuthenticationPrincipal OAuth2User principal){
        if(principal == null  || principal.getAttribute("login") ==null){
            return "index";
        }
        List<Book> allBooks = bookRepository.findAll();
        Pageable bookPageable = PageRequest.of(0, 10, Sort.by("readingStatus").ascending());
        String userId = principal.getAttribute("login");
        List<Book> booksByUser  = bookRepository.findAllByUserId(userId, bookPageable);
    
        model.addAttribute("books", booksByUser);
        return "home";
    }
   
}
