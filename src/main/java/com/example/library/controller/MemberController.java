package com.example.library.controller;

import com.example.library.model.Member;
import com.example.library.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping
    public String listMembers(Model model) {
        model.addAttribute("members", memberService.findAll());
        return "member/list";
    }

    @GetMapping("/add")
    public String createMemberForm(Model model) {
        model.addAttribute("member", new Member());
        return "member/form";
    }

    @PostMapping("/save")
    public String saveMember(@ModelAttribute Member member,
                             @RequestParam("registrationDateStr") String registrationDateStr) {
        if (registrationDateStr != null && !registrationDateStr.isEmpty()) {
            member.setRegistrationDate(LocalDate.parse(registrationDateStr));
        }
        memberService.save(member);
        return "redirect:/members";
    }

    @GetMapping("/edit/{id}")
    public String editMemberForm(@PathVariable("id") String memberId, Model model) {
        Member member = memberService.findById(memberId);
        if (member == null) {
            return "redirect:/members";
        }
        model.addAttribute("member", member);
        return "member/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteMember(@PathVariable("id") String memberId) {
        memberService.deleteById(memberId);
        return "redirect:/members";
    }
}
