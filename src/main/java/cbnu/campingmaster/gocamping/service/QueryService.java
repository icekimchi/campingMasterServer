package cbnu.campingmaster.gocamping.service;

import cbnu.campingmaster.gocamping.dto.CampingSiteDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class QueryService {

    private final JdbcTemplate jdbcTemplate;

    public List<CampingSiteDto> executeQuery(String sqlQuery, Object[] params) {
        return jdbcTemplate.query(sqlQuery, params, new QueryCampsiteDtoRowMapper());
    }

    public String makeKeywordQuery(String keyword, String param){
        if (param=="pet_allowed")
            return "SELECT * FROM campsite WHERE pet_allowed=1";
        return "SELECT * FROM campsite WHERE" + keyword +  "LIKE '%" + keyword + "%'";
    }

    public String makeRegionQuery(String keyword, String region){
        return "SELECT * FROM campsite WHERE address LIKE '%" + region + "%'";
    }

    public QueryResult makeFilterQuery(Map<String, List<String>> filters) {
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM campsite WHERE 1=1");
        List<Object> params = new ArrayList<>();

        // 카테고리 필터 처리
        if (filters.containsKey("category") && !filters.get("category").isEmpty()) {
            queryBuilder.append(" AND category IN (");
            addFilterParameters(queryBuilder, filters.get("category"), params);
            queryBuilder.append(")");
        }

        // 자연환경 필터 처리
        if (filters.containsKey("nature") && !filters.get("nature").isEmpty()) {
            queryBuilder.append(" AND (");
            List<String> natureFilters = filters.get("nature");
            for (int i = 0; i < natureFilters.size(); i++) {
                if (i > 0) queryBuilder.append(" OR ");
                queryBuilder.append("nearby_facilities LIKE ?");
                params.add("%" + natureFilters.get(i) + "%");
            }
            queryBuilder.append(")");
        }

        // 테마여행 필터 처리
        if (filters.containsKey("theme") && !filters.get("theme").isEmpty()) {
            queryBuilder.append(" AND (");
            List<String> themeFilters = filters.get("theme");
            for (int i = 0; i < themeFilters.size(); i++) {
                if (i > 0) queryBuilder.append(" OR ");
                queryBuilder.append("thema_envrn_cl LIKE ?");
                params.add("%" + themeFilters.get(i) + "%");
            }
            queryBuilder.append(")");
        }

        return new QueryResult(queryBuilder.toString(), params.toArray());
    }

    private void addFilterParameters(StringBuilder queryBuilder, List<String> values, List<Object> params) {
        for (int i = 0; i < values.size(); i++) {
            if (i > 0) queryBuilder.append(",");
            queryBuilder.append("?");
            params.add(values.get(i));
        }
    }

    // 쿼리와 파라미터를 함께 반환하기 위한 내부 클래스
    @Getter
    @AllArgsConstructor
    public static class QueryResult {
        private final String query;
        private final Object[] params;
    }

    private static class QueryCampsiteDtoRowMapper implements RowMapper<CampingSiteDto> {
        @Override
        public CampingSiteDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            CampingSiteDto dto = new CampingSiteDto();
            dto.setContentId(rs.getLong("content_id"));
            dto.setAddress(rs.getString("address"));
            dto.setCategory(rs.getString("category"));
            dto.setFeatureNm(rs.getString("feature_nm"));
            dto.setHomepageUrl(rs.getString("homepage_url"));
            dto.setImgUrl(rs.getString("img_url"));
            dto.setLineIntro(rs.getString("line_intro"));
            dto.setMapX(rs.getString("mapX"));
            dto.setMapY(rs.getString("mapY"));
            dto.setLocationCategory(rs.getString("location_category"));
            dto.setNearbyFacilities(rs.getString("nearby_facilities"));
            dto.setReserveUrl(rs.getString("reserve_url"));
            dto.setSeason(rs.getString("season"));
            dto.setSiteName(rs.getString("site_name"));
            dto.setTel(rs.getString("tel"));
            dto.setThemaEnvrnCl(rs.getString("thema_envrn_cl"));
            dto.setPetAllowed(rs.getBoolean("pet_allowed"));
            return dto;
        }
    }
}