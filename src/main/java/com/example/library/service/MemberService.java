package com.example.library.service;

import com.example.library.model.Member;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {

    private static final List<Member> members = new ArrayList<>();

    // Initialize some members in memory
    static {
        members.add(new Member("M001", "John", "Doe", "john.doe@example.com", LocalDate.of(2023, 1, 1), "Actif"));
        members.add(new Member("M002", "Jane", "Smith", "jane.smith@example.com", LocalDate.of(2023, 2, 1), "Actif"));
    }

    public List<Member> findAll() {
        return members;
    }

    public Member findById(String memberId) {
        return members.stream()
                .filter(m -> m.getMemberId().equals(memberId))
                .findFirst()
                .orElse(null);
    }

    public void save(Member member) {
        Member existing = findById(member.getMemberId());
        if (existing != null) {
            existing.setFirstName(member.getFirstName());
            existing.setLastName(member.getLastName());
            existing.setEmail(member.getEmail());
            existing.setRegistrationDate(member.getRegistrationDate());
            existing.setStatus(member.getStatus());
        } else {
            members.add(member);
        }
    }

    public void deleteById(String memberId) {
        members.removeIf(m -> m.getMemberId().equals(memberId));
    }
}
