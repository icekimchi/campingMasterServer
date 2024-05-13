package cbnu.campingmaster.member.repository;

import cbnu.campingmaster.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByMemberId(String memberId);
    boolean existsByEmail(String email);
    Optional<Member> findByMemberId(String memberId); // 아이디로 사용자를 찾는 메서드

}
