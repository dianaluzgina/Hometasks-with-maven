package task8;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import task8.requests.AllInOneByGeoLocationRequest;
import task8.responses.dailyResponse.DailyResponse;

import static task8.requests.AllInOneByGeoLocationRequest.Excludes.*;

public class MaxHumidityInMadridTest {

  private String latMadrid = "40.42";
  private String lonMadrid = "-3.7";
  private String userId = "259283d34b7b5560b296681e3aebf880";
  private int percentageOfHumidity = 89;

  @Test
  public void maxHumidityInMadrid() {
    Response response = new AllInOneByGeoLocationRequest(latMadrid, lonMadrid)
        .withExcludes(CURRENT, MINUTELY, HOURLY)
        .withAppid(userId)
        .doRequest();
    DailyResponse dailyResponseMadrid = response.as(DailyResponse.class);
    int maxHumidity = dailyResponseMadrid.getWeathers().get(0).getHumidity();
    for (int i = 1; i < 5; i++) {
      if (dailyResponseMadrid.getWeathers().get(i).getHumidity() > maxHumidity) {
        maxHumidity = dailyResponseMadrid.getWeathers().get(i).getHumidity();
      }
    }
    System.out.println("max humidity in Madrid next 5 days is " + maxHumidity);
    Assert.assertTrue(maxHumidity > percentageOfHumidity, String
        .format("Humidity in Madrid next 5 days is less than %s percentage", percentageOfHumidity));
  }
}
