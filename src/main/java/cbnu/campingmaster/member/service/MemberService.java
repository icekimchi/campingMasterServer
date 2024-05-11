package cbnu.campingmaster.member.service;
import cbnu.campingmaster.member.domain.Member;
import cbnu.campingmaster.member.dto.MemberDto;
import cbnu.campingmaster.member.handler.MemberEmailAlreadyExistsException;
import cbnu.campingmaster.member.handler.MemberIdAlreadyExistsException;
import cbnu.campingmaster.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member register(MemberDto memberDto){
        if (memberRepository.existsByMemberId(memberDto.getMemberId())){
            throw new MemberIdAlreadyExistsException(memberDto.getMemberId() + "는 이미 존재하는 아이디입니다.");
        } else if (memberRepository.existsByEmail(memberDto.getEmail())) {
            throw new MemberEmailAlreadyExistsException(memberDto.getEmail() + "는 이미 존재하는 이메일입니다.");
        }

        Member member = Member.createMember(memberDto);
        memberRepository.save(member);
        return member;
    }
}
