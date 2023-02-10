package com.techeeresc.tab.domain.member.respository;

import com.techeeresc.tab.domain.member.entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    @EntityGraph(attributePaths = "authorities") //@EntityGraph 쿼리 수행시 Eager조회로 authorities정보 같이 가져옴
    Optional<Member> findOneWithAuthoritiesByEmail(String email);
}