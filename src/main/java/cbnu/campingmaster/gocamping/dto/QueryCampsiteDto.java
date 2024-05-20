package cbnu.campingmaster.gocamping.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QueryCampsiteDto {
    private long contentId;
    private String insrncAt; //영업배상책임보험 가입여부
    private String siteName;
    private String featureNm;
    private String address;
    private String lineIntro;
    private String category;
    private String locationCategory;
    private String tel;
    private String homepageUrl;
    private String season;
    private String ReserveUrl;
    private String nearbyFacilities; //주변이용가능시설, 운동장,강/물놀이,농어촌체험시설
    private String themaEnvrnCl; //테마환경, 낚시,여름물놀이
    private boolean petAllowed; //애완동물출입,
    private String ImgUrl; //대표이미지
}
