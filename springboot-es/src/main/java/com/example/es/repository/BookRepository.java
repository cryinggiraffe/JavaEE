package com.example.es.repository;

import com.example.es.beans.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface BookRepository extends ElasticsearchRepository<Book,Integer>{

    List<Book> findByBookNameLike(String bookName);
}
