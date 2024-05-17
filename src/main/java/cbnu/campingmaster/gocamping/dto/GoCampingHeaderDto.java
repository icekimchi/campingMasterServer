package cbnu.campingmaster.gocamping.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GoCampingHeaderDto {
    @JsonProperty("resultCode")
    private String resultCode;

    @JsonProperty("resultMsg")
    private String resultMsg;
}
