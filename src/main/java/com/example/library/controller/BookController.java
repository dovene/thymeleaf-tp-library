package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public String listBooks(@RequestParam(value = "search", required = false) String search, Model model) {
        if (search != null && !search.isBlank()) {
            model.addAttribute("books", bookService.search(search));
            model.addAttribute("search", search);
        } else {
            model.addAttribute("books", bookService.findAll());
            model.addAttribute("search", "");
        }
        return "book/list";
    }

    @GetMapping("/add")
    public String createBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("statuses", bookService.getStatuses());
        model.addAttribute("types", bookService.getTypes());
        return "book/form";
    }

    @PostMapping("/save")
    public String saveBook(@ModelAttribute("book") Book book,
                           @RequestParam("pubDate") String publicationDate) {
        if (publicationDate != null && !publicationDate.isEmpty()) {
            book.setPublicationDate(LocalDate.parse(publicationDate));
        }
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/edit/{isbn}")
    public String editBookForm(@PathVariable("isbn") String isbn, Model model) {
        Book book = bookService.findByIsbn(isbn);
        if (book == null) {
            return "redirect:/books";
        }
        model.addAttribute("book", book);
        model.addAttribute("statuses", bookService.getStatuses());
        model.addAttribute("types", bookService.getTypes());
        return "book/form";
    }

    @GetMapping("/delete/{isbn}")
    public String deleteBook(@PathVariable("isbn") String isbn) {
        bookService.deleteByIsbn(isbn);
        return "redirect:/books";
    }
}