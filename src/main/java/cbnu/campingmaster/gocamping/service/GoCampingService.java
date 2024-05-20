package cbnu.campingmaster.gocamping.service;

import cbnu.campingmaster.gocamping.GoCampingApiManager;
import cbnu.campingmaster.gocamping.dto.GoCampingItemDto;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class GoCampingService {

    private final GoCampingApiManager goCampingApiManager;

    public JSONArray baseSearch() throws IOException {
        String baseUrl = goCampingApiManager.makeBaseUrl();
        String jsonData = goCampingApiManager.fetch(baseUrl);
        JSONObject jsonObject = new JSONObject(jsonData);
        JSONObject response = jsonObject.getJSONObject("response");
        JSONObject body = response.getJSONObject("body");
        JSONObject items = body.getJSONObject("items");
        JSONArray itemArray = items.getJSONArray("item");

        return itemArray;
    }

    public JSONArray  searchByKeyword(String keyword) throws IOException {
        String keywordUrl = goCampingApiManager.makeKeywordUrl(keyword);
        String jsonData = goCampingApiManager.fetch(keywordUrl);
        JSONObject jsonObject = new JSONObject(jsonData);
        JSONObject response = jsonObject.getJSONObject("response");
        JSONObject body = response.getJSONObject("body");
        JSONObject items = body.getJSONObject("items");
        JSONArray itemArray = items.getJSONArray("item");

        return itemArray;
    }

    public String searchByLocation(String mapX, String mapY, String radius) throws IOException{
        String locationUrl = goCampingApiManager.makeLocationUrl(mapX, mapY, radius);
        System.out.println("locationUrl = " + locationUrl);
        String jsonData = goCampingApiManager.fetch(locationUrl);
        return jsonData;
    }

    public void setDtoFields(GoCampingItemDto dto, JSONObject jsonObject) {
        dto.setContentId(jsonObject.optLong("contentId"));
        dto.setFacltNm(jsonObject.optString("facltNm"));
        dto.setLineIntro(jsonObject.optString("lineIntro"));
        dto.setIntro(jsonObject.optString("intro"));
        dto.setInsrncAt(jsonObject.optString("insrncAt"));
        dto.setHvofBgnde(jsonObject.optString("hvofBgnde"));
        dto.setFeatureNm(jsonObject.optString("featureNm"));
        dto.setInduty(jsonObject.optString("induty"));
        dto.setLctCl(jsonObject.optString("lctCl"));
        dto.setDoNm(jsonObject.optString("doNm"));
        dto.setSigunguNm(jsonObject.optString("sigunguNm"));
        dto.setZipcode(jsonObject.optString("zipcode"));
        dto.setAddr1(jsonObject.optString("addr1"));
        dto.setAddr2(jsonObject.optString("addr2"));
        dto.setMapX(jsonObject.optString("mapX"));
        dto.setMapY(jsonObject.optString("mapY"));
        dto.setTel(jsonObject.optString("tel"));
        dto.setHomepage(jsonObject.optString("homepage"));
        dto.setGnrlSiteCo(jsonObject.optString("gnrlSiteCo"));
        dto.setAutoSiteCo(jsonObject.optString("autoSiteCo"));
        dto.setGlampSiteCo(jsonObject.optString("glampSiteCo"));
        dto.setCaravSiteCo(jsonObject.optString("caravSiteCo"));
        dto.setIndvdlCaravSiteCo(jsonObject.optString("indvdlCaravSiteCo"));
        dto.setGlampInnerFclty(jsonObject.optString("glampInnerFclty"));
        dto.setCaravInnerFclty(jsonObject.optString("caravInnerFclty"));
        dto.setOperPdCl(jsonObject.optString("operPdCl"));
        dto.setResveUrl(jsonObject.getString("resveUrl"));
        dto.setOperDeCl(jsonObject.optString("operDeCl"));
        dto.setPosblFcltyCl(jsonObject.optString("posblFcltyCl"));
        dto.setClturEventAt(jsonObject.optString("clturEventAt"));
        dto.setClturEvent(jsonObject.optString("clturEvent"));
        dto.setExprnProgrmAt(jsonObject.optString("exprnProgrmAt"));
        dto.setExprnProgrm(jsonObject.optString("exprnProgrm"));
        dto.setThemaEnvrnCl(jsonObject.optString("themaEnvrnCl"));
        dto.setEqpmnLendCl(jsonObject.optString("eqpmnLendCl"));
        dto.setAnimalCmgCl(jsonObject.optString("animalCmgCl"));
        dto.setTourEraCl(jsonObject.optString("tourEraCl"));
        dto.setFirstImageUrl(jsonObject.optString("firstImageUrl"));
        dto.setCreatedtime(jsonObject.optString("createdtime"));
        dto.setModifiedtime(jsonObject.optString("modifiedtime"));
    }
}