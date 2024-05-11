package cbnu.campingmaster.member.controller;

import cbnu.campingmaster.member.domain.Member;
import cbnu.campingmaster.member.dto.MemberDto;
import cbnu.campingmaster.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;

    //회원가입
    @PostMapping("/members")    // name값을 requestparam에 담아온다
    public String save(@RequestBody MemberDto memberDto) {
        log.info("회원가입 요청 회원: {}", memberDto.getMemberId());
        memberService.register(memberDto);
        return "회원가입이 성공적으로 완료되었습니다.";
    }



}
