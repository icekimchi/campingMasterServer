package cbnu.campingmaster.gocamping.controller;

import cbnu.campingmaster.gocamping.dto.GoCampingItemDto;
import cbnu.campingmaster.gocamping.dto.QueryCampsiteDto;
import cbnu.campingmaster.gocamping.service.GoCampingService;
import cbnu.campingmaster.gocamping.service.QueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class QueryController {

    private final QueryService queryService;

    @PostMapping("/executeQuery")
    public ResponseEntity<?> executeQuery(@RequestBody Map<String, Object> request) {
        try {
            String sqlQuery = (String) request.get("sqlQuery");
            Object param = request.get("param");

            // Validate sqlQuery and param
            if (sqlQuery == null || param == null) {
                return ResponseEntity.badRequest().body("Invalid request payload");
            }

            List<QueryCampsiteDto> results = queryService.executeQuery(sqlQuery, new Object[]{param});
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            log.error("Error executing query", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while executing the query");
        }
    }
}