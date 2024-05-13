package cbnu.campingmaster.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomMemberDto {
    private Long id;

    private String memberId;

    private String memberPw;

    private String email;

    private RoleList role;
}
