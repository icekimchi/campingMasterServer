package cbnu.campingmaster.gocamping.service;

import cbnu.campingmaster.gocamping.dto.GoCampingItemDto;
import cbnu.campingmaster.gocamping.dto.QueryCampsiteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryService {

    private final JdbcTemplate jdbcTemplate;

    public List<QueryCampsiteDto> executeQuery(String sqlQuery, Object[] params) {
        return jdbcTemplate.query(sqlQuery, params, new QueryCampsiteDtoRowMapper());
    }

    private static class QueryCampsiteDtoRowMapper implements RowMapper<QueryCampsiteDto> {
        @Override
        public QueryCampsiteDto mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
            QueryCampsiteDto dto = new QueryCampsiteDto();
            // Set fields from result set to dto
            dto.setContentId(rs.getLong("content_id"));
            dto.setAddress(rs.getString("address"));
            dto.setCategory(rs.getString("category"));
            dto.setFeatureNm(rs.getString("feature_nm"));
            dto.setHomepageUrl(rs.getString("homepage_url"));
            dto.setImgUrl(rs.getString("img_url"));
            dto.setInsrncAt(rs.getString("insrnc_at"));
            dto.setLineIntro(rs.getString("line_intro"));
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