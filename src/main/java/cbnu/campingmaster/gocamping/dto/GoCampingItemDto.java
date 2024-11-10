package cbnu.campingmaster.gocamping.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GoCampingItemDto {
    private long contentId;
    private String facltNm; //캠핑장 이름
    private String lineIntro; //한줄소개
    private String intro; //소개글
    private String hvofBgnde; //휴장기간, 휴무기간 시작일
    private String featureNm; //특징
    private String induty; //업종 : 일반야영장, 카라반, 글램핑
    private String lctCl; //입지구분 : 호수,,
    private String doNm; //도 (전라남도)
    private String sigunguNm; //시군구 : 담양군
    private String zipcode; //우편번호
    private String addr1; //주소 : 전남 담양군 봉산면 탄금길 9-26
    private String mapX; //경도
    private String mapY; //위도
    private String tel; //전화
    private String homepage; //홈페이지
    private String resveUrl; //예약페이지
    private String gnrlSiteCo; //주요시설 일반야영장 개수
    private String autoSiteCo; //주요시설 자동차야영장 개수
    private String glampSiteCo; //글램핑 야영장 개수
    private String caravSiteCo; //카라반 개수
    private String indvdlCaravSiteCo; //개인 카라반 개수
    private String glampInnerFclty; // 글램핑 내부시설 : 침대, 에어컨, 내앚ㅇ고,,
    private String caravInnerFclty; //카라반 내부시설: 침대, TV..
    private String operPdCl; //운영기간
    private String operDeCl; // 운영일(평일+주말)
    private String posblFcltyCl; //주변이용가능시설 (운동장, 강, 물놀이, 농어촌체험시설..)
    private String clturEventAt; // 자체문화행사 여부(N, Y)
    private String clturEvent; //자체문화행사명
    private String exprnProgrmAt; //체험프로그램여부(Y, N)
    private String exprnProgrm; //체험프로그램 이름
    private String themaEnvrnCl; //테마환경 : 낚시, 여름물놀이..
    private String eqpmnLendCl; //캠핑장장비대여
    private String animalCmgCl; //애완동물출입
    private String tourEraCl; //여행시기
    private String firstImageUrl; //대표이미지
}

