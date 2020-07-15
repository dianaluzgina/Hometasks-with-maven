package task8;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Coordinates {
    @JsonProperty("lon")
    public String longitude;
    @JsonProperty("lat")
    public String latitude;
}
