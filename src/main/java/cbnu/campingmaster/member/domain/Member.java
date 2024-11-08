package cbnu.campingmaster.member.domain;

import cbnu.campingmaster.member.dto.MemberRegisterDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
public class Member{

    @Id
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
