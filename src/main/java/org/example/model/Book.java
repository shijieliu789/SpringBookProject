package org.example.model;

// persistence api has classes and methods to continuously store data into tables
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank; //ensures class can compile

// Model class to save retrieve, update, delete details of book

// This class defines what entities are there for Book
@Entity //entity indicator for DB
@Table(name = "books")      // creates table book when ran
public class Book {
    @Id     //table primary key auto generated (springboot)
    @GeneratedValue
    private Long id;

    @NotBlank   //field should not be null
    private String book_name;
    @NotBlank
    private String isbn;

    public Book(){
        super();
    } //constructor to super, convention of java

    public Book(Long id, String book_name, String isbn) {
        super();
        this.id = id;
        this.book_name = book_name;
        this.isbn=isbn;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getBook_name() {
        return book_name;
    }
    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}