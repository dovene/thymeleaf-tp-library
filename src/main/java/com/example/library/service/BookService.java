package com.example.library.service;

import com.example.library.model.Book;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private static final List<Book> books = new ArrayList<>();
    private static final String[] types = {"Fiction", "Sciences", "Jeunesse", "Histoire", "Non-Fiction"};
    private static final String[] statuses = {"Neuf", "Bon", "Usé", "A remplacer"};

    // Initialize some books in memory
    static {
        books.add(new Book("ISBN001", "The Great Gatsby", "F. Scott Fitzgerald", "Fiction",
                LocalDate.of(1925, 4, 10), "Bon", 3, 3, 10.0));
        books.add(new Book("ISBN002", "A Brief History of Time", "Stephen Hawking", "Sciences",
                LocalDate.of(1988, 3, 1), "Neuf", 2, 2, 15.0));
        books.add(new Book("ISBN003", "Harry Potter and the Sorcerer's Stone", "J.K. Rowling", "Jeunesse",
                LocalDate.of(1997, 6, 26), "Bon", 5, 5, 20.0));
    }

    public List<String> getTypes() {
        return List.of(types);
    }

    public List<String> getStatuses() {
        return List.of(statuses);
    }

    public List<Book> findAll() {
        return books;
    }

    public Book findByIsbn(String isbn) {
        return books.stream()
                .filter(b -> b.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);
    }

    public void save(Book book) {
        // If the book's ISBN already exists, update it
        Book existing = findByIsbn(book.getIsbn());
        if (existing != null) {
            existing.setTitle(book.getTitle());
            existing.setAuthor(book.getAuthor());
            existing.setType(book.getType());
            existing.setPublicationDate(book.getPublicationDate());
            existing.setStatus(book.getStatus());
            existing.setAvailableCopies(book.getAvailableCopies());
            existing.setTotalCopies(book.getTotalCopies());
            existing.setReplacementPrice(book.getReplacementPrice());
        } else {
            // Else add new book
            books.add(book);
        }
    }

    public void deleteByIsbn(String isbn) {
        books.removeIf(b -> b.getIsbn().equals(isbn));
    }

    public List<Book> search(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return books;
        }
        String lower = keyword.toLowerCase();
        return books.stream()
                .filter(b -> b.getIsbn().toLowerCase().contains(lower)
                        || b.getTitle().toLowerCase().contains(lower)
                        || b.getAuthor().toLowerCase().contains(lower)
                        || b.getType().toLowerCase().contains(lower)
                        || b.getStatus().toLowerCase().contains(lower))
                .collect(Collectors.toList());
    }
}
