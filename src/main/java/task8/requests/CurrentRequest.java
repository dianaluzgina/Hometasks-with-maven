package task8.requests;

import io.restassured.http.Method;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CurrentRequest {

  private static final String BASE_URI = "https://api.openweathermap.org/data/2.5";
  private String parameters = "weather?";

  public CurrentRequest(String cityName) {
    parameters += String.format("q=%s", cityName);
  }

  public CurrentRequest(String lat, String lon) {
    parameters += String.format("lat=%s&lon=%s", lat, lon);
  }

  public CurrentRequest withAppid(String appid) {
    parameters += "&appid=" + appid;
    return this;
  }

  public Response doRequest() {
    return given()
        .log().all()
        .baseUri(BASE_URI)
        .request(Method.GET, parameters);
  }
}
