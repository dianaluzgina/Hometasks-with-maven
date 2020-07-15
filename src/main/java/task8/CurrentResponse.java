package task8;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentResponse {
    @JsonProperty("coord")
    public Coordinates coordinates;
    @JsonProperty("weather")
    public List<CurrentWeather> weathers;
    @JsonProperty("sys")
    public CountryInformation countryInformation;
    @JsonProperty("name")
    public String cityName;
}
