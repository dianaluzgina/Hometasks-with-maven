package task8.responses.currentResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentResponse {

  @JsonProperty("coord")
  private Coordinates coordinates;
  @JsonProperty("weather")
  private List<CurrentWeather> weathers;
  @JsonProperty("sys")
  private CountryInformation countryInformation;
  @JsonProperty("name")
  private String cityName;

  public Coordinates getCoordinates() {
    return coordinates;
  }

  public List<CurrentWeather> getWeathers() {
    return weathers;
  }

  public CountryInformation getCountryInformation() {
    return countryInformation;
  }

  public String getCityName() {
    return cityName;
  }

  public void setCoordinates(Coordinates coordinates) {
    this.coordinates = coordinates;
  }

  public void setWeathers(List<CurrentWeather> weathers) {
    this.weathers = weathers;
  }

  public void setCountryInformation(CountryInformation countryInformation) {
    this.countryInformation = countryInformation;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }
}
