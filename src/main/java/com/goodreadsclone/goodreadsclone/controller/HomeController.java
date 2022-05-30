package com.goodreadsclone.goodreadsclone.controller;

import com.goodreadsclone.goodreadsclone.entities.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class HomeController {
    @Autowired
    BookRepository bookRepository;
    public String home(){
        //bookRepository.findAllById(id, pageable)
        return "home";
    }
    
}
