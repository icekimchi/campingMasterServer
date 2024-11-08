package cbnu.campingmaster.member.service;
import cbnu.campingmaster.member.domain.Member;
import cbnu.campingmaster.member.dto.MemberLoginDto;
import cbnu.campingmaster.member.dto.MemberRegisterDto;
import cbnu.campingmaster.member.exception.InvalidPasswordException;
import cbnu.campingmaster.member.exception.MemberEmailAlreadyExistsException;
import cbnu.campingmaster.member.exception.MemberIdAlreadyExistsException;
import cbnu.campingmaster.member.exception.MemberNotFoundException;
import cbnu.campingmaster.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.regex.Pattern.matches;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member register(MemberRegisterDto memberRegisterDto){
        if (memberRepository.existsByMemberId(memberRegisterDto.getMemberId())){
            throw new MemberIdAlreadyExistsException(memberRegisterDto.getMemberId());
        } else if (memberRepository.existsByEmail(memberRegisterDto.getEmail())) {
            throw new MemberEmailAlreadyExistsException(memberRegisterDto.getEmail());
        }

        Member member = Member.createMember(memberRegisterDto);
        memberRepository.save(member);
        return member;
    }

    @Transactional
    public Member login(MemberLoginDto memberLoginDto) {
        Member member = memberRepository.findByMemberId(memberLoginDto.getMemberId())
                .orElseThrow(() -> new MemberNotFoundException());

        if (!matches(memberLoginDto.getMemberPw(), member.getMemberPw()))
            throw new InvalidPasswordException();

        return member;
    }
}
