package kr.co.yhs.security.repository;

import kr.co.yhs.security.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    public Optional<Member> findByUserName(String username);
}
