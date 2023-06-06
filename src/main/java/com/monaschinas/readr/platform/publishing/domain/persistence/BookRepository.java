package com.monaschinas.readr.platform.publishing.domain.persistence;

import com.monaschinas.readr.platform.publishing.domain.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByTitle(String title);
}