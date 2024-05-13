package cbnu.campingmaster.member.service;
import cbnu.campingmaster.member.domain.Member;
import cbnu.campingmaster.member.dto.MemberSignInDto;
import cbnu.campingmaster.member.dto.MemberRegisterDto;
import cbnu.campingmaster.member.exception.InvalidPasswordException;
import cbnu.campingmaster.member.exception.MemberEmailAlreadyExistsException;
import cbnu.campingmaster.member.exception.MemberIdAlreadyExistsException;
import cbnu.campingmaster.member.exception.MemberNotFoundException;
import cbnu.campingmaster.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Member register(MemberRegisterDto memberRegisterDto){
        if (memberRepository.existsByMemberId(memberRegisterDto.getMemberId())){
            throw new MemberIdAlreadyExistsException(memberRegisterDto.getMemberId() + "는 이미 존재하는 아이디입니다.");
        } else if (memberRepository.existsByEmail(memberRegisterDto.getEmail())) {
            throw new MemberEmailAlreadyExistsException(memberRegisterDto.getEmail() + "는 이미 존재하는 이메일입니다.");
        }

        String encodedPassword = passwordEncoder.encode(memberRegisterDto.getMemberPw());
        Member member = Member.createMember(memberRegisterDto);
        member.setMemberPw(encodedPassword);
        memberRepository.save(member);
        return member;
    }

    public Member login(MemberSignInDto memberSignInDto) {
        Member member = memberRepository.findByMemberId(memberSignInDto.getMemberId())
                .orElseThrow(() -> new MemberNotFoundException("존재하지 않는 아이디입니다."));

        if (!passwordEncoder.matches(memberSignInDto.getMemberPw(), member.getMemberPw()))
            throw new InvalidPasswordException("비밀번호가 일치하지 않습니다.");

        return member;
    }
}
