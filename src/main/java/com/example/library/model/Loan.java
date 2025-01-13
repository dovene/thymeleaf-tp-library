package com.example.library.model;

import java.time.LocalDate;

public class Loan {
    private String memberId;        
    private String isbn;            
    private LocalDate loanDate;
    private LocalDate dueDate;
    private String bookConditionAtLoan;
    private String bookConditionAtReturn;
    private String status;         
    
    public Loan() {
    }

    public Loan(String memberId, String isbn, LocalDate loanDate, LocalDate dueDate, 
                String bookConditionAtLoan, String bookConditionAtReturn, String status) {
        this.memberId = memberId;
        this.isbn = isbn;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.bookConditionAtLoan = bookConditionAtLoan;
        this.bookConditionAtReturn = bookConditionAtReturn;
        this.status = status;
    }

    // Getters and setters...
    public String getMemberId() {
        return memberId;
    }
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }
    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getBookConditionAtLoan() {
        return bookConditionAtLoan;
    }
    public void setBookConditionAtLoan(String bookConditionAtLoan) {
        this.bookConditionAtLoan = bookConditionAtLoan;
    }

    public String getBookConditionAtReturn() {
        return bookConditionAtReturn;
    }
    public void setBookConditionAtReturn(String bookConditionAtReturn) {
        this.bookConditionAtReturn = bookConditionAtReturn;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
