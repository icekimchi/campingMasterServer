package cbnu.campingmaster.gocamping.controller;

import cbnu.campingmaster.gocamping.dto.CampingSiteDto;
import cbnu.campingmaster.gocamping.service.QueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

            List<CampingSiteDto> results = queryService.executeQuery(sqlQuery, new Object[]{param});
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            log.error("Error executing query", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while executing the query");
        }
    }

    @PostMapping("/filter")
    public List<CampingSiteDto> filterCampingSites(@RequestBody Map<String, List<String>> filters) {
        QueryService.QueryResult queryResult = queryService.makeFilterQuery(filters);
        return queryService.executeQuery(queryResult.getQuery(), queryResult.getParams());
    }
}