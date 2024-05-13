package cbnu.campingmaster.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRegisterDto {
    private String memberId;
    private String memberPw;
    private String email;
}
