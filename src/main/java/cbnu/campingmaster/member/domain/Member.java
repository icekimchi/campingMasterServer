package cbnu.campingmaster.member.domain;

import cbnu.campingmaster.member.dto.MemberRegisterDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

@Entity //엔티티 정의
@Getter
@Setter
public class Member{
    @Id //기본키를 의미. 반드시 기본키를 가져야함.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String memberId;

    @NonNull
    private String memberPw;

    @NonNull
    private String email;

    public static Member createMember(MemberRegisterDto memberRegisterDto) {
        Member member = new Member();
        member.memberId = memberRegisterDto.getMemberId();
        member.memberPw = memberRegisterDto.getMemberPw();
        member.email = memberRegisterDto.getEmail();
        return member;
    }
}
