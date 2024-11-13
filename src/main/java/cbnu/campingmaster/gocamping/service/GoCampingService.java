package cbnu.campingmaster.gocamping.service;

import cbnu.campingmaster.gocamping.GoCampingApiManager;
import cbnu.campingmaster.gocamping.domain.Campsite;
import cbnu.campingmaster.gocamping.dto.CampingSiteDto;
import cbnu.campingmaster.gocamping.dto.GoCampingItemDto;
import cbnu.campingmaster.gocamping.repository.CampsiteRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class GoCampingService {

    private final GoCampingApiManager goCampingApiManager;
    private final CampsiteRepository campsiteRepository;

    public JSONArray baseSearch() throws IOException {
        String baseUrl = goCampingApiManager.makeBaseUrl();
        return getObjects(baseUrl);
    }

    private JSONArray getObjects(String baseUrl) throws IOException {
        String jsonData = goCampingApiManager.fetch(baseUrl);

        if (jsonData == null || jsonData.isEmpty()) {
            throw new IOException("Empty response from API");
        }
        JSONObject items = getJsonObject(jsonData);
        if (items == null) {
            return new JSONArray();
        }
        JSONArray itemArray = items.optJSONArray("item");
        if (itemArray == null) {
            return new JSONArray();
        }

        return itemArray;
    }

    public JSONArray searchByKeyword(String keyword) throws IOException {
        String keywordUrl = goCampingApiManager.makeKeywordUrl(keyword);
        return getObjects(keywordUrl);
    }

    public JSONArray searchByLocation(String mapX, String mapY, String radius) throws IOException {
        String locationUrl = goCampingApiManager.makeLocationUrl(mapX, mapY, radius);
        System.out.println("locationUrl = " + locationUrl);
        return getObjects(locationUrl);
    }

    public Campsite searchByName(String siteName) throws IOException {
        return campsiteRepository.findBySiteName(siteName);
    }

    private static JSONObject getJsonObject(String jsonData) throws IOException {
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(jsonData);
        } catch (JSONException e) {
            throw new IOException("Invalid JSON format: " + e.getMessage());
        }

        // JSON 구조에서 필요한 데이터 추출
        JSONObject response = jsonObject.optJSONObject("response");
        if (response == null) {
            throw new IOException("Missing 'response' key in API response");
        }

        JSONObject body = response.optJSONObject("body");
        if (body == null) {
            throw new IOException("Missing 'body' key in API response");
        }

        JSONObject items = body.optJSONObject("items");
        return items;
    }

    public GoCampingItemDto makeGoCampSiteDto(JSONObject jsonObject) {
        GoCampingItemDto goCampingItemDto = new GoCampingItemDto();

        // JSON 데이터를 GoCampingItemDto로 매핑
        goCampingItemDto.setContentId(jsonObject.optLong("contentId"));
        goCampingItemDto.setFacltNm(jsonObject.optString("facltNm"));
        goCampingItemDto.setLineIntro(jsonObject.optString("lineIntro"));
        goCampingItemDto.setIntro(jsonObject.optString("intro"));
        goCampingItemDto.setFeatureNm(jsonObject.optString("featureNm"));
        goCampingItemDto.setInduty(jsonObject.optString("induty"));
        goCampingItemDto.setLctCl(jsonObject.optString("lctCl"));
        goCampingItemDto.setDoNm(jsonObject.optString("doNm"));
        goCampingItemDto.setSigunguNm(jsonObject.optString("sigunguNm"));
        goCampingItemDto.setZipcode(jsonObject.optString("zipcode"));
        goCampingItemDto.setAddr1(jsonObject.optString("addr1"));
        goCampingItemDto.setMapX(jsonObject.optString("mapX"));
        goCampingItemDto.setMapY(jsonObject.optString("mapY"));
        goCampingItemDto.setTel(jsonObject.optString("tel"));
        goCampingItemDto.setHomepage(jsonObject.optString("homepage"));
        goCampingItemDto.setResveUrl(jsonObject.optString("resveUrl"));
        goCampingItemDto.setGlampInnerFclty(jsonObject.optString("glampInnerFclty"));
        goCampingItemDto.setCaravInnerFclty(jsonObject.optString("caravInnerFclty"));
        goCampingItemDto.setOperPdCl(jsonObject.optString("operPdCl"));
        goCampingItemDto.setOperDeCl(jsonObject.optString("operDeCl"));
        goCampingItemDto.setThemaEnvrnCl(jsonObject.optString("themaEnvrnCl"));
        goCampingItemDto.setAnimalCmgCl(jsonObject.optString("animalCmgCl"));
        goCampingItemDto.setFirstImageUrl(jsonObject.optString("firstImageUrl"));

        return goCampingItemDto;
    }
}