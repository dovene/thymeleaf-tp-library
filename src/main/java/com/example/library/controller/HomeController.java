package com.example.library.controller;

import com.example.library.service.BookService;
import com.example.library.service.LoanService;
import com.example.library.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private BookService bookService;

    @Autowired
    private LoanService loanService;

    @GetMapping("/")
    public String home(Model model) {
        long memberCount = memberService.findAll().size();
        long totalBooksCount = bookService.findAll().stream().mapToInt(b -> b.getTotalCopies()).sum();
        long booksInProgressCount = loanService.countLoansInProgress();
        long booksLateCount = loanService.countLateLoans();

        model.addAttribute("memberCount", memberCount);
        model.addAttribute("totalBooksCount", totalBooksCount);
        model.addAttribute("booksInProgressCount", booksInProgressCount);
        model.addAttribute("booksLateCount", booksLateCount);
        return "home";
    }
}
