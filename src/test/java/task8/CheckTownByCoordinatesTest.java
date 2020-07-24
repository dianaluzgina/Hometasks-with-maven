package task8;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import task8.requests.CurrentRequest;
import task8.responses.currentResponse.CurrentResponse;

public class CheckTownByCoordinatesTest {

  private String lat = "37";
  private String lon = "52.01";
  private String userId = "259283d34b7b5560b296681e3aebf880";
  private String townName = "Fereydun Kenar";

  @Test
  public void checkTownByCoordinates() {
    Response response = new CurrentRequest(lat, lon)
        .withAppid(userId)
        .doRequest();
    CurrentResponse currentResponse = response.as(CurrentResponse.class);
    Assert.assertEquals(currentResponse.getCityName(), townName,
        String.format("Сity %s is not located in coordinates lat=%s, lon=%s.", townName, lat, lon));
  }
}
