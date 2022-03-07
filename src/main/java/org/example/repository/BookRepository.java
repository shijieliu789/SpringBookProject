package org.example.repository;

import org.example.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// <> takes in 'Name of the entity we want to retrieve (Book)' and type of Primary key
@Repository     // annotation indicates the DAO (Data access obj) component in persistent layer.
public interface BookRepository extends JpaRepository<Book,Long> {
}