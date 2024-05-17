package cbnu.campingmaster.gocamping.service;

import cbnu.campingmaster.gocamping.GoCampingApiManager;
import cbnu.campingmaster.gocamping.dto.GoCampingResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class GoCampingService {

    private final GoCampingApiManager goCampingApiManager;

    public GoCampingResponseDto searchByKeyword(String keyword) throws IOException, ParseException {
        String keywordUrl = goCampingApiManager.makeKeywordUrl(keyword);
        return goCampingApiManager.fetch(keywordUrl).getBody();
    }
}