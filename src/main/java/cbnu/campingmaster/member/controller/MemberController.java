package cbnu.campingmaster.member.controller;

import cbnu.campingmaster.member.domain.Member;
import cbnu.campingmaster.member.dto.MemberLoginDto;
import cbnu.campingmaster.member.dto.MemberRegisterDto;
import cbnu.campingmaster.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;

    //회원가입
    @PostMapping("/members/signup")    // name값을 requestparam에 담아온다
    public ResponseEntity<Map<String, Object>> save(@RequestBody MemberRegisterDto memberRegisterDto) {
        memberService.register(memberRegisterDto);
        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        response.put("message", "회원가입이 성공적으로 완료되었습니다.");

        return ResponseEntity.ok(response);
    }

    // 로그인
    @PostMapping("/members/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody MemberLoginDto memberLoginDto) {
        memberService.login(memberLoginDto);
        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        response.put("message", "로그인이 성공적으로 완료되었습니다.");

        return ResponseEntity.ok(response);
    }
}
