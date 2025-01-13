package com.example.library.model;

import java.time.LocalDate;

public class Book {
    private String isbn;          
    private String title;
    private String author;
    private String type;            
    private LocalDate publicationDate;
    private String status;
    private int availableCopies;
    private int totalCopies;
    private double replacementPrice;

    public Book() {
    }


    public Book(String isbn, String title, String author, String type, LocalDate publicationDate, 
                String status, int availableCopies, int totalCopies, double replacementPrice) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.type = type;
        this.publicationDate = publicationDate;
        this.status = status;
        this.availableCopies = availableCopies;
        this.totalCopies = totalCopies;
        this.replacementPrice = replacementPrice;
    }

    // Getters and setters...
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }
    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }
    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public int getTotalCopies() {
        return totalCopies;
    }
    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public double getReplacementPrice() {
        return replacementPrice;
    }
    public void setReplacementPrice(double replacementPrice) {
        this.replacementPrice = replacementPrice;
    }
}
