package org.example.controller;

import org.example.exception.BookNotFoundException;
import org.example.exception.AuthorNotFoundException;
import org.example.model.Book;
import org.example.model.Author;
import org.example.model.Authorship;
import org.example.model.AuthorshipId;
import org.example.repository.BookRepository;
import org.example.repository.AuthorRepository;
import org.example.repository.AuthorshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller     //not a rest controller which outputs json. So we use Controller as we render jsp files
public class BookController {

    @Autowired      // wires repository accessing the book in DB, can use all Jpa functions
    BookRepository bookRepository;
    @Autowired
    AuthorshipRepository authorshipRepository;
    @Autowired
    AuthorRepository authorRepository;

    // Get a Single Book
    @GetMapping("/books/{id}")
    public String getBookById(@PathVariable(value="id") Long bookId, Model model)
            throws BookNotFoundException, AuthorNotFoundException{
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
        model.addAttribute("book", book);

        List<Long> author_ids = authorshipRepository.findAuthorByBookId(book.getId());
        List<Author> authors = new ArrayList<>();
        for(Long author_id: author_ids){
            Author author =authorRepository.findById(author_id)
                    .orElseThrow(() -> new AuthorNotFoundException(author_id));
            authors.add(author);
        }
        model.addAttribute("authors", authors);
        return "editform";      //instead of returning json, we render the jsp files.
    }       // will be returned as a HTTPResponse


//     See All Books on Homepage
    @RequestMapping({"/", "/list"})
    public String viewHomePage(Model model){
        List<Book> listBooks = bookRepository.findAll();
        model.addAttribute("listBooks", listBooks);     // list of books from jsp repo.
        return "welcome";       // this returns the name of the jsp page in charge of handling the data presentation
        // jsp page will refer to the set of retrieved book using variable listBooks
    }

    // Delete a Book
    @RequestMapping("/delete/{id}")
    public String deleteBook(@PathVariable(value = "id") Long bookId, Model model) throws BookNotFoundException{
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
        bookRepository.delete(book);
        return viewHomePage(model);     // invokes method viewHomePage showing the books.
    }

    // Create a Book
    @RequestMapping("/new")
    public String createBook(){
        return "bookform";
    }

    // Save Created Book
    @PostMapping("/books")
    public String saveCreatedBook(@ModelAttribute("book") Book book, Model model){
        //  @ModelAttribute allows access to the map entries of the Model variable.
        bookRepository.save(book);
        return viewHomePage(model);
    }

//    // Update a Book
//    // Get Book By ID and open the editform
////    @GetMapping("/books/{id}")
////    public String getBookById(@PathVariable(value="id") Long bookId, Model model) throws BookNotFoundException{
////        Book book = bookRepository.findById(bookId)
////                .orElseThrow(() -> new BookNotFoundException(bookId));
////        model.addAttribute("book", book);
////        return "editform";
////    }
    // Save Updated Details
    @RequestMapping(value="/books/save", method=RequestMethod.POST)
    public String updateBook(@ModelAttribute("book") Book book, Model model){
        bookRepository.save(book);
        return viewHomePage(model);
    }

}
