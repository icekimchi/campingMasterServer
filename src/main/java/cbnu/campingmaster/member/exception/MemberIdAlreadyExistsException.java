package cbnu.campingmaster.member.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MemberIdAlreadyExistsException extends RuntimeException {
    public MemberIdAlreadyExistsException(String message) {
        super(message + "는 이미 존재하는 아이디입니다.");
    }
}
