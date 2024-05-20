package cbnu.campingmaster.gocamping.domain;

import cbnu.campingmaster.gocamping.dto.GoCampingItemDto;
import cbnu.campingmaster.member.domain.Member;
import cbnu.campingmaster.member.dto.MemberRegisterDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

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

    public static Campsite createCampsite(GoCampingItemDto goCampingItemDto) {
        Campsite campsite = new Campsite();
        campsite.contentId = goCampingItemDto.getContentId();
        campsite.siteName = goCampingItemDto.getFacltNm();
        campsite.lineIntro = goCampingItemDto.getLineIntro();
        campsite.category = goCampingItemDto.getInduty();
        campsite.featureNm = goCampingItemDto.getFeatureNm();
        campsite.season = goCampingItemDto.getOperPdCl();
        campsite.address = goCampingItemDto.getAddr1();
        campsite.locationCategory = goCampingItemDto.getLctCl();
        campsite.tel = goCampingItemDto.getTel();
        campsite.homepageUrl = goCampingItemDto.getHomepage();
        campsite.nearbyFacilities = goCampingItemDto.getPosblFcltyCl();
        campsite.ReserveUrl = goCampingItemDto.getResveUrl();
        campsite.themaEnvrnCl = goCampingItemDto.getThemaEnvrnCl();
        if (goCampingItemDto.getAnimalCmgCl() != null && goCampingItemDto.getAnimalCmgCl().contains("불가능")) {
            campsite.petAllowed = false;
        } else {
            campsite.petAllowed = true;
        }
        campsite.ImgUrl = goCampingItemDto.getFirstImageUrl();

        return campsite;
    }
}
