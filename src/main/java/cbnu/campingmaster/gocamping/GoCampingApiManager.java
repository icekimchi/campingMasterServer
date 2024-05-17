package cbnu.campingmaster.gocamping;

import cbnu.campingmaster.gocamping.dto.GoCampingResponseDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class GoCampingApiManager {

    private final ObjectMapper objectMapper;
    private final String BASE_URL = "http://apis.data.go.kr/B551011/GoCamping";
    private final String serviceKey = "StTaAP5Xx9Lq3lMaUr%2BZ7OjDFQAStNfGAwhZt0O9Sgi0JyBtkJN7aB0cB02mdGWw%2F0HIbVnETO9c%2BLr1C%2FTplA%3D%3D";
    private final String mobileOS = "ETC";
    private final String mobileApp = "campingmaster";

    public ResponseEntity<GoCampingResponseDto> fetch(String url) {
        System.out.println(url);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        System.out.println("response = " + response);

        try {
            GoCampingResponseDto responseDto = objectMapper.readValue(response.getBody(), GoCampingResponseDto.class);
            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse GoCamping API response", e);
        }
    }

    public String makeKeywordUrl(String keyword) {
        return UriComponentsBuilder.fromHttpUrl(BASE_URL + "/searchList")
                .queryParam("MobileOS", mobileOS)
                .queryParam("MobileApp", mobileApp)
                .queryParam("_type", "json")
                .queryParam("serviceKey", serviceKey)
                .queryParam("keyword", encodeParam(keyword))
                .toUriString();
    }

    private String encodeParam(String param) {
        try {
            return URLEncoder.encode(param, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Failed to encode URL parameter", e);
        }
    }
}
