package com.example.library.controller;

import com.example.library.model.Loan;
import com.example.library.service.BookService;
import com.example.library.service.LoanService;
import com.example.library.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@Controller
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @Autowired
    private BookService bookService;

    @Autowired
    private MemberService memberService;

    @GetMapping
    public String listLoans(Model model) {
        model.addAttribute("loans", loanService.findAll());
        return "loan/list";
    }

    @GetMapping("/add")
    public String createLoanForm(Model model) {
        model.addAttribute("statuses", bookService.getStatuses());
        model.addAttribute("books", bookService.findAll());
        model.addAttribute("members", memberService.findAll());
        model.addAttribute("loan", new Loan());
        return "loan/form";
    }

    @PostMapping("/save")
    public String saveLoan(@ModelAttribute("loan") Loan loan,
                           @RequestParam("loanDateStr") String loanDateStr,
                           @RequestParam("dueDateStr") String dueDateStr) {
        if (loanDateStr != null && !loanDateStr.isEmpty()) {
            loan.setLoanDate(LocalDate.parse(loanDateStr));
        }
        if (dueDateStr != null && !dueDateStr.isEmpty()) {
            loan.setDueDate(LocalDate.parse(dueDateStr));
        }
        loanService.save(loan);
        return "redirect:/loans";
    }

    @GetMapping("/edit/{memberId}/{isbn}")
    public String editLoanForm(@PathVariable("memberId") String memberId,
                               @PathVariable("isbn") String isbn, 
                               Model model) {
        Loan loan = loanService.findLoan(memberId, isbn);
        if (loan == null) {
            return "redirect:/loans";
        }
        model.addAttribute("books", bookService.findAll());
        model.addAttribute("members", memberService.findAll());
        model.addAttribute("statuses", bookService.getStatuses());
        model.addAttribute("loan", loan);
        return "loan/form";
    }

    @GetMapping("/delete/{memberId}/{isbn}")
    public String deleteLoan(@PathVariable("memberId") String memberId, @PathVariable("isbn") String isbn) {
        loanService.deleteLoan(memberId, isbn);
        return "redirect:/loans";
    }
}