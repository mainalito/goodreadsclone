package com.goodreadsclone.goodreadsclone.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,UserBookIds>{
    //pageable
   List<Book>findAllByUserId(String id, org.springframework.data.domain.Pageable firstPageWithTwoElements);
   
}
