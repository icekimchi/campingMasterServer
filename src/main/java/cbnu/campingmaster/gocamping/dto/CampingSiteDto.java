package cbnu.campingmaster.gocamping.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@RequiredArgsConstructor
public class CampingSiteDto {
    private long contentId;
    private String siteName; //사이트이름
    private String featureNm;
    private String address;
    private String lineIntro;
    private String category;
    private String locationCategory;
    private String tel; //전화번호
    private String mapX;
    private String mapY;
    private String homepageUrl; //홈페이지링크
    private String season; //
    private String ReserveUrl; //예약사이트
    private String nearbyFacilities; //주변이용가능시설, 운동장,강/물놀이,농어촌체험시설
    private String themaEnvrnCl; //테마환경, 낚시,여름물놀이
    private boolean petAllowed; //애완동물출입,
    private String ImgUrl; //대표이미지
}
