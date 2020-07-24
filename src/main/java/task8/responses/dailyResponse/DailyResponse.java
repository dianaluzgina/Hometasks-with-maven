package task8.responses.dailyResponse;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyResponse {

  @JsonProperty("daily")
  private List<DailyWeather> weathers;

  public List<DailyWeather> getWeathers() {
    return weathers;
  }

  public void setWeathers(List<DailyWeather> weathers) {
    this.weathers = weathers;
  }
}
