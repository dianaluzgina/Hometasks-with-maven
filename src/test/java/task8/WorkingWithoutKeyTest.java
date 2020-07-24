package task8;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import task8.requests.AllInOneByGeoLocationRequest;

import static task8.requests.AllInOneByGeoLocationRequest.Excludes.*;

public class WorkingWithoutKeyTest {

  private String testLat = "33.441792";
  private String testLon = "-94.037689";

  @Test
  public void workingWithoutKey() {
    Response response = new AllInOneByGeoLocationRequest(testLat, testLon)
        .withExcludes(MINUTELY, HOURLY, CURRENT)
        .doRequest();
    Assert.assertEquals(response.getStatusCode(), 401,
        "Status code of response differs from which we've expected");
  }
}
