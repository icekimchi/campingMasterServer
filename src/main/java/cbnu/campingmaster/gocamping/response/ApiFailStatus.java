package cbnu.campingmaster.gocamping.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApiFailStatus {

    //400 BAD_REQUEST
    SIGN_IN_FAIL("로그인 실패"),
    INVALID_TOKEN_PREFIX("토큰 접두사 오류"),
    DUPLICATED_MEMBER_FIELD("중복된 이메일 혹은 이름입니다"),
    DUPLICATED_CHATROOM("중복된 채팅방 이름입니다"),

    //403 FORBIDDEN

    //404 NOT_FOUND
    KEYWORD_NOT_FOUND("존재하지 않는 키워드입니다"),
    MEMBER_NOT_FOUND("사용자를 찾을 수 없습니다");

    //500 INTERNAL_SERVER_ERROR
    private final String message;
}
