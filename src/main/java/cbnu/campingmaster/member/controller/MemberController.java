package cbnu.campingmaster.member.controller;

import cbnu.campingmaster.member.domain.Member;
import cbnu.campingmaster.member.dto.MemberSignInDto;
import cbnu.campingmaster.member.dto.MemberRegisterDto;
import cbnu.campingmaster.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;

    //회원가입
    @PostMapping("/members")    // name값을 requestparam에 담아온다
    public String save(@RequestBody MemberRegisterDto memberRegisterDto) {
        memberService.register(memberRegisterDto);
        return "회원가입이 성공적으로 완료되었습니다.";
    }

    // 로그인
    @PostMapping("/login")
    public String login(@RequestBody MemberSignInDto memberSignInDto) {
        Member member = memberService.login(memberSignInDto);
        return "로그인이 성공적으로 완료되었습니다. 회원 아이디: " + member.getMemberId();
    }
}
