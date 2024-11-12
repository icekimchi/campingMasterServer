package cbnu.campingmaster.gocamping.domain;

import cbnu.campingmaster.gocamping.dto.CampingSiteDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.json.JSONObject;

@Entity //엔티티 정의
@Getter
@Setter
public class Campsite {
    @Id //기본키를 의미. 반드시 기본키를 가져야함.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long contentId;

    @NonNull
    private String insrncAt; //영업배상책임보험 가입여부

    private String siteName;
    @Column(columnDefinition = "LONGTEXT")
    private String featureNm;
    @Column(columnDefinition = "LONGTEXT")
    private String address;
    private String mapX;
    private String mapY;
    private String lineIntro;
    private String category;
    private String locationCategory;
    private String tel;
    private String homepageUrl;
    private String season;
    @Column(columnDefinition = "LONGTEXT")
    private String ReserveUrl;
    private String nearbyFacilities; //주변이용가능시설, 운동장,강/물놀이,농어촌체험시설
    private String themaEnvrnCl; //테마환경, 낚시,여름물놀이
    private boolean petAllowed; //애완동물출입,
    private String ImgUrl; //대표이미지

    // 엔티티를 DTO로 변환하는 메서드 추가
    public CampingSiteDto toDto() {
        CampingSiteDto dto = new CampingSiteDto();
        dto.setContentId(this.contentId);
        dto.setSiteName(this.siteName);
        dto.setFeatureNm(this.featureNm);
        dto.setAddress(this.address);
        dto.setMapX(this.mapX);
        dto.setMapY(this.mapY);
        dto.setLineIntro(this.lineIntro);
        dto.setCategory(this.category);
        dto.setLocationCategory(this.locationCategory);
        dto.setTel(this.tel);
        dto.setHomepageUrl(this.homepageUrl);
        dto.setSeason(this.season);
        dto.setReserveUrl(this.ReserveUrl);
        dto.setNearbyFacilities(this.nearbyFacilities);
        dto.setThemaEnvrnCl(this.themaEnvrnCl);
        dto.setPetAllowed(this.petAllowed);
        dto.setImgUrl(this.ImgUrl);
        return dto;
    }

    public static Campsite createCampsite(JSONObject jsonObject) {
        Campsite campsite = new Campsite();
        campsite.contentId = jsonObject.optLong("contentId");
        campsite.siteName = jsonObject.optString("facltNm");
        campsite.lineIntro = jsonObject.optString("lineIntro");
        campsite.category = jsonObject.optString("induty");
        campsite.featureNm = jsonObject.optString("featureNm");
        campsite.season = jsonObject.optString("operPdCl");
        campsite.address = jsonObject.optString("addr1");
        campsite.mapX = jsonObject.optString("mapX");
        campsite.mapY = jsonObject.optString("mapY");
        campsite.locationCategory = jsonObject.optString("lctCl");
        campsite.tel = jsonObject.optString("tel");
        campsite.homepageUrl = jsonObject.optString("homepage");
        campsite.nearbyFacilities = jsonObject.optString("posblFcltyCl");
        campsite.ReserveUrl = jsonObject.getString("resveUrl");
        campsite.themaEnvrnCl = jsonObject.optString("themaEnvrnCl");
        campsite.petAllowed = !jsonObject.optString("animalCmgCl").contains("불가능");
        campsite.ImgUrl = jsonObject.optString("firstImageUrl");
        return campsite;
    }
}
