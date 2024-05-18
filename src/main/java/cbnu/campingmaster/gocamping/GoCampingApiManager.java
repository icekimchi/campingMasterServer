package cbnu.campingmaster.gocamping;

import cbnu.campingmaster.gocamping.dto.GoCampingResponseDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class GoCampingApiManager {

    private final ObjectMapper objectMapper;
    private final String BASE_URL = "http://apis.data.go.kr/B551011/GoCamping";
    private final String serviceKey = "t1NG5TWAnczu5ccF0RpGt3KABH21fjDCWTQoHNR3vpbmOsEBIRO4ayLNdzV3GGAuwTeEAfS2oDa9dd3FB27naQ==";
    private final String mobileOS = "ETC";
    private final String mobileApp = "campingmaster";

    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(3000); // 3초
        factory.setReadTimeout(3000);    // 3초
        return new RestTemplate(factory);
    }

    public String fetch(String searchUrl) throws IOException {
        URL url = new URL(searchUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
        return sb.toString();
    }

    public String makeKeywordUrl(String keyword) {
        return UriComponentsBuilder.fromHttpUrl(BASE_URL + "/searchList")
                .queryParam("MobileOS", mobileOS)
                .queryParam("MobileApp", mobileApp)
                .queryParam("_type", "json")
                .queryParam("serviceKey", serviceKey)
                .queryParam("keyword", keyword)
                .toUriString();
    }

    public String makeLocationUrl(String mapX, String mapY, String radius) {
        return UriComponentsBuilder.fromHttpUrl(BASE_URL + "/locationBasedList")
                .queryParam("MobileOS", mobileOS)
                .queryParam("MobileApp", mobileApp)
                .queryParam("_type", "json")
                .queryParam("serviceKey", serviceKey)
                .queryParam("mapX", mapX)
                .queryParam("mapY", mapY)
                .queryParam("radius", radius)
                .toUriString();
    }


}
