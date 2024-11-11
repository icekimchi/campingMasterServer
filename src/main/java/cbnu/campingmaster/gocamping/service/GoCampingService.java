package cbnu.campingmaster.gocamping.service;

import cbnu.campingmaster.gocamping.GoCampingApiManager;
import cbnu.campingmaster.gocamping.dto.CampingSiteDto;
import cbnu.campingmaster.gocamping.dto.GoCampingItemDto;
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

    public JSONArray baseSearch() throws IOException {
        String baseUrl = goCampingApiManager.makeBaseUrl();
        return getObjects(baseUrl);
    }

    private JSONArray getObjects(String baseUrl) throws IOException {
        String jsonData = goCampingApiManager.fetch(baseUrl);

        // 응답이 유효한지 확인
        if (jsonData == null || jsonData.isEmpty()) {
            throw new IOException("Empty response from API");
        }
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
        } catch (JSONException e) {
            System.out.println("Invalid JSON format: " + e.getMessage());
        }

        JSONObject jsonObject = new JSONObject(jsonData);
        JSONObject response = jsonObject.getJSONObject("response");
        JSONObject body = response.getJSONObject("body");
        JSONObject items = body.getJSONObject("items");
        JSONArray itemArray = items.getJSONArray("item");

        return itemArray;
    }

    public JSONArray  searchByKeyword(String keyword) throws IOException {
        String keywordUrl = goCampingApiManager.makeKeywordUrl(keyword);
        return getObjects(keywordUrl);
    }

    public String searchByLocation(String mapX, String mapY, String radius) throws IOException {
        String locationUrl = goCampingApiManager.makeLocationUrl(mapX, mapY, radius);
        System.out.println("locationUrl = " + locationUrl);
        String jsonData = goCampingApiManager.fetch(locationUrl);
        return jsonData;
    }
}