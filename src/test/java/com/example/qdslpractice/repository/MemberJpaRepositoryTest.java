package com.example.qdslpractice.repository;

import com.example.qdslpractice.entity.Member;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberJpaRepositoryTest {
    @Autowired
    EntityManager em;

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    void basicTest() {
        Member member = new Member("member1", 10);
        memberJpaRepository.save(member);

        Member findMember = memberJpaRepository.findById(member.getId()).get();
        assertEquals(findMember, member);

        List<Member> result1 = memberJpaRepository.findAll();
        assertEquals(result1.size(), 1);
        assertEquals(result1.get(0), member);

        List<Member> result2 = memberJpaRepository.findByUsername("member1");
        assertEquals(result2.size(), 1);
        assertEquals(result2.get(0), member);
    }

    @Test
    void basicQuerydslTest() {
        Member member = new Member("member1", 10);
        memberJpaRepository.save(member);

        List<Member> result1 = memberJpaRepository.findAll_Querydsl();
        assertEquals(result1.size(), 1);
        assertEquals(result1.get(0), member);

        List<Member> result2 = memberJpaRepository.findByUsername_Querydsl("member1");
        assertEquals(result2.size(), 1);
        assertEquals(result2.get(0), member);
    }
}