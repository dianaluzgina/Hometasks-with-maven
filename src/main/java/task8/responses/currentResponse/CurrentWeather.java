package task8.responses.currentResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentWeather {

  @JsonProperty("main")
  private String spaceState;

  public String getSpaceState() {
    return spaceState;
  }

  public void setSpaceState(String spaceState) {
    this.spaceState = spaceState;
  }
}
