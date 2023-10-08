package com.springboot.review_msa.repository;

import com.springboot.review_msa.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepositoryInterface extends JpaRepository<Member, String> {
    Member findByMid(String mid);
}
