package cbnu.campingmaster.gocamping.controller;

import cbnu.campingmaster.gocamping.domain.Campsite;
import cbnu.campingmaster.gocamping.dto.CampingSiteDto;
import cbnu.campingmaster.gocamping.dto.GoCampingItemDto;
import cbnu.campingmaster.gocamping.dto.LocationSearchDto;
import cbnu.campingmaster.gocamping.response.ApiResponse;
import cbnu.campingmaster.gocamping.response.ApiSuccessStatus;
import cbnu.campingmaster.gocamping.service.CampsiteService;
import cbnu.campingmaster.gocamping.service.GoCampingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GoCampingController {

    private final GoCampingService goCampingService;
    private final CampsiteService campsiteService;

    @GetMapping("/basedList")
    public ResponseEntity<ApiResponse<List<CampingSiteDto>>> baseSearch() throws IOException {
        JSONArray jsonArray = goCampingService.baseSearch();
        List<CampingSiteDto> campingSiteDtoList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Campsite campsite = campsiteService.saveCampsite(jsonObject);
            CampingSiteDto dto = campsite.toDto();
            campingSiteDtoList.add(dto);
        }
        return ApiResponse.success(ApiSuccessStatus.BASE_SEARCH_SUCCESS, campingSiteDtoList);
    }

    @GetMapping("/get-keyword")
    public ResponseEntity<?> keywordSearch(@RequestParam String keyword) throws IOException {
        JSONArray result = goCampingService.searchByKeyword(keyword);
        return ResponseEntity.ok(result.toString());
    }

    @GetMapping("/get-location")
    public ResponseEntity<List<CampingSiteDto>> locationSearch(@RequestParam String mapX,
                                                                            @RequestParam String mapY, @RequestParam String radius) throws IOException {
        JSONArray result = goCampingService.searchByLocation(mapX, mapY, radius);
        List<CampingSiteDto> campingSiteDtoList = new ArrayList<>();
        for (int i = 0; i < result.length(); i++) {
            JSONObject jsonObject = result.getJSONObject(i);
            Campsite campsite = Campsite.createCampsite(jsonObject);
            CampingSiteDto dto = campsite.toDto();
            campingSiteDtoList.add(dto);
        }
        return ResponseEntity.ok(campingSiteDtoList);
    }

    @GetMapping("/detail/{siteName}")
    public ResponseEntity<Campsite> getSiteDetail(@PathVariable String siteName) throws IOException{
        return ResponseEntity.ok(goCampingService.searchByName(siteName));
    }
}