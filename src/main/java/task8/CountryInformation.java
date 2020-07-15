package task8;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
//api.openweathermap.org/data/2.5/weather?q=Gomel&appid=259283d34b7b5560b296681e3aebf880

@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryInformation {
    @JsonProperty("country")
    public String country;
}
