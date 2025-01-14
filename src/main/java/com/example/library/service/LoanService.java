package com.example.library.service;

import com.example.library.model.Loan;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoanService {

    private static final List<Loan> loans = new ArrayList<>();

    static {
        loans.add(new Loan("M001", "ISBN001", LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 15), "Bon", "Bon", "En cours"));
        loans.add(new Loan("M002", "ISBN002", LocalDate.of(2023, 2, 1), LocalDate.of(2023, 2, 15), "Bon", "Bon", "En cours"));
    }

    public List<Loan> findAll() {
        return loans;
    }

    public Loan findLoan(String memberId, String isbn) {
        return loans.stream()
                .filter(l -> l.getMemberId().equals(memberId) && l.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);
    }

    public void save(Loan loan) {
        Loan existing = findLoan(loan.getMemberId(), loan.getIsbn());
        if (existing != null) {
            existing.setLoanDate(loan.getLoanDate());
            existing.setDueDate(loan.getDueDate());
            existing.setBookConditionAtLoan(loan.getBookConditionAtLoan());
            existing.setBookConditionAtReturn(loan.getBookConditionAtReturn());
            existing.setStatus(loan.getStatus());
        } else {
            loans.add(loan);
        }
    }

    public void deleteLoan(String memberId, String isbn) {
        loans.removeIf(l -> l.getMemberId().equals(memberId) && l.getIsbn().equals(isbn));
    }

    public long countLoansInProgress() {
        return loans.stream().filter(l -> "En cours".equals(l.getStatus())).count();
    }

    public long countLateLoans() {
        LocalDate now = LocalDate.now();
        return loans.stream()
                .filter(l -> "En retard".equals(l.getStatus()) && l.getDueDate().isBefore(now))
                .count();
    }
}
