package cbnu.campingmaster.gocamping.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ApiSuccessStatus {

    BASE_SEARCH_SUCCESS(HttpStatus.OK,"캠핑장 데이터 로드 성공"),
    SIGNUP_SUCCESS(HttpStatus.CREATED,"회원가입 성공"),
    SIGNIN_SUCCESS(HttpStatus.ACCEPTED, "로그인 성공"),
    KEYWORD_SEARCH_SUCCESS(HttpStatus.OK, "키워드 조회 성공"),
    LOCATION_SEARCH_SUCCESS(HttpStatus.OK, "위치 기반 캠핑장 조회 성공");

    private final HttpStatus httpStatus;
    private final String message;
}
