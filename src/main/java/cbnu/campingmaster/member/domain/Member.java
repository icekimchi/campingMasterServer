package cbnu.campingmaster.member.domain;

import cbnu.campingmaster.member.dto.MemberDto;
import jakarta.persistence.*;
import lombok.*;

@Entity //엔티티 정의
@Getter
@Setter
public class Member {
    @Id //기본키를 의미. 반드시 기본키를 가져야함.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String memberId;

    @NonNull
    private String memberPw;

    @NonNull
    private String email;

    public static Member createMember(MemberDto memberDto) {
        Member member = new Member();
        member.memberId = memberDto.getMemberId();
        member.memberPw = memberDto.getMemberPw();
        member.email = memberDto.getEmail();
        return member;
    }
}
