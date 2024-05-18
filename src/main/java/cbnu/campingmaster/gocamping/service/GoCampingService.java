package cbnu.campingmaster.gocamping.service;

import cbnu.campingmaster.gocamping.GoCampingApiManager;
import cbnu.campingmaster.gocamping.dto.GoCampingResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class GoCampingService {

    private final GoCampingApiManager goCampingApiManager;

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
        String jsonData = goCampingApiManager.fetch(locationUrl);
        return jsonData;
    }
}