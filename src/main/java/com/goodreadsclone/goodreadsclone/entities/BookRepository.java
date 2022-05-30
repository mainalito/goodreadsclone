package com.goodreadsclone.goodreadsclone.entities;

import java.util.List;

import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookRepository extends PagingAndSortingRepository<Book,String>{
    //pageable
   //List<Book>findAllById(String id, Pageable pageable);
}
