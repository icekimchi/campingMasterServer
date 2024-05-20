package cbnu.campingmaster.gocamping.service;

import cbnu.campingmaster.gocamping.dto.GoCampingItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryService {

    private final JdbcTemplate jdbcTemplate;

    public List<GoCampingItemDto> executeQuery(String sqlQuery) {
        return jdbcTemplate.query(sqlQuery, (rs, rowNum) -> {
            GoCampingItemDto dto = new GoCampingItemDto();
            // Set fields from result set to dto
            dto.setContentId(rs.getLong("contentId"));
            dto.setFacltNm(rs.getString("facltNm"));
            dto.setLineIntro(rs.getString("lineIntro"));
            dto.setIntro(rs.getString("intro"));
            dto.setInsrncAt(rs.getString("insrncAt"));
            dto.setHvofBgnde(rs.getString("hvofBgnde"));
            dto.setFeatureNm(rs.getString("featureNm"));
            dto.setInduty(rs.getString("induty"));
            dto.setLctCl(rs.getString("lctCl"));
            dto.setDoNm(rs.getString("doNm"));
            dto.setSigunguNm(rs.getString("sigunguNm"));
            dto.setZipcode(rs.getString("zipcode"));
            dto.setAddr1(rs.getString("addr1"));
            dto.setAddr2(rs.getString("addr2"));
            dto.setMapX(rs.getString("mapX"));
            dto.setMapY(rs.getString("mapY"));
            dto.setTel(rs.getString("tel"));
            dto.setHomepage(rs.getString("homepage"));
            dto.setGnrlSiteCo(rs.getString("gnrlSiteCo"));
            dto.setAutoSiteCo(rs.getString("autoSiteCo"));
            dto.setGlampSiteCo(rs.getString("glampSiteCo"));
            dto.setCaravSiteCo(rs.getString("caravSiteCo"));
            dto.setIndvdlCaravSiteCo(rs.getString("indvdlCaravSiteCo"));
            dto.setGlampInnerFclty(rs.getString("glampInnerFclty"));
            dto.setCaravInnerFclty(rs.getString("caravInnerFclty"));
            dto.setOperPdCl(rs.getString("operPdCl"));
            dto.setOperDeCl(rs.getString("operDeCl"));
            dto.setPosblFcltyCl(rs.getString("posblFcltyCl"));
            dto.setClturEventAt(rs.getString("clturEventAt"));
            dto.setClturEvent(rs.getString("clturEvent"));
            dto.setExprnProgrmAt(rs.getString("exprnProgrmAt"));
            dto.setExprnProgrm(rs.getString("exprnProgrm"));
            dto.setThemaEnvrnCl(rs.getString("themaEnvrnCl"));
            dto.setEqpmnLendCl(rs.getString("eqpmnLendCl"));
            dto.setAnimalCmgCl(rs.getString("animalCmgCl"));
            dto.setTourEraCl(rs.getString("tourEraCl"));
            dto.setFirstImageUrl(rs.getString("firstImageUrl"));
            dto.setCreatedtime(rs.getString("createdtime"));
            dto.setModifiedtime(rs.getString("modifiedtime"));
            return dto;
        });
    }


}