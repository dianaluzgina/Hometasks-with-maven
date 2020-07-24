package task8;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import task8.requests.CurrentRequest;
import task8.responses.currentResponse.CurrentResponse;

public class CurrentWeatherInGomelTest {

  private String townName = "Gomel";
  private String userId = "259283d34b7b5560b296681e3aebf880";
  private String spaceStateToCheck = "Clouds";
  private String latToCheck = "52.42";
  private String lonToCheck = "31.01";
  private String countryToCheck = "BY";

  @Test
  public void currentWeatherInGomel() {
    Response response = new CurrentRequest(townName)
        .withAppid(userId)
        .doRequest();
    CurrentResponse currentResponse = response.as(CurrentResponse.class);
    SoftAssert softAssert = new SoftAssert();
    softAssert
        .assertNotEquals(currentResponse.getWeathers().get(0).getSpaceState(), spaceStateToCheck,
            String.format("State of space in Gomel is equal to %s", spaceStateToCheck));
    softAssert.assertEquals(currentResponse.getCoordinates().getLatitude(), latToCheck,
        String.format("Latitude of Gomel isn't equal to %s", latToCheck));
    softAssert.assertEquals(currentResponse.getCoordinates().getLongitude(), lonToCheck,
        String.format("Longitude of Gomel isn't equal to %s", lonToCheck));
    softAssert.assertEquals(currentResponse.getCountryInformation().getCountry(), countryToCheck,
        String.format("CountyCode of Gomel isn't equal to %s", countryToCheck));
    softAssert.assertAll();
  }
}
