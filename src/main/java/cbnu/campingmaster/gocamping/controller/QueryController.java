package cbnu.campingmaster.gocamping.controller;

import cbnu.campingmaster.gocamping.dto.GoCampingItemDto;
import cbnu.campingmaster.gocamping.service.GoCampingService;
import cbnu.campingmaster.gocamping.service.QueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class QueryController {

    private final QueryService queryService;
    private final GoCampingService goCampingService;

    @PostMapping("/executeQuery")
    public ResponseEntity<List<GoCampingItemDto>> executeQuery(@RequestBody String sqlQuery) {
        List<GoCampingItemDto> results = queryService.executeQuery(sqlQuery);
        return ResponseEntity.ok(results);
    }

}