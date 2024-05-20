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
    private String facltNm;
    private String lineIntro;
    private String intro;
    private String insrncAt;
    private String hvofBgnde;
    private String featureNm;
    private String induty;
    private String lctCl;
    private String doNm;
    private String sigunguNm;
    private String zipcode;
    private String addr1;
    private String addr2;
    private String mapX;
    private String mapY;
    private String tel;
    private String homepage;
    private String resveUrl;
    private String gnrlSiteCo;
    private String autoSiteCo;
    private String glampSiteCo;
    private String caravSiteCo;
    private String indvdlCaravSiteCo;
    private String glampInnerFclty;
    private String caravInnerFclty;
    private String operPdCl;
    private String operDeCl;
    private String posblFcltyCl;
    private String clturEventAt;
    private String clturEvent;
    private String exprnProgrmAt;
    private String exprnProgrm;
    private String themaEnvrnCl;
    private String eqpmnLendCl;
    private String animalCmgCl;
    private String tourEraCl;
    private String firstImageUrl;
    private String createdtime;
    private String modifiedtime;
}

