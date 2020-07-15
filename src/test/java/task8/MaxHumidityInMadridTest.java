package task8;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static task8.AllInOneByGeoLocationRequest.Excludes.*;

public class MaxHumidityInMadridTest {
    private String latMadrid = "40.42";
    private String lonMadrid = "-3.7";
    private String userId = "259283d34b7b5560b296681e3aebf880";
    private int percentageOfHumidity = 89;

    @Test
    public void maxHumidityInMadrid(){
        Response response = new AllInOneByGeoLocationRequest(latMadrid, lonMadrid)
                .withExcludes(CURRENT, MINUTELY, HOURLY)
                .withAppid(userId)
                .doRequest();
        DailyResponse dailyResponseMadrid = response.as(DailyResponse.class);
        int maxHumidity = dailyResponseMadrid.weathers.get(0).humidity;
        for (int i=1; i<5;i++) {
            if (dailyResponseMadrid.weathers.get(i).humidity > maxHumidity)
            { maxHumidity = dailyResponseMadrid.weathers.get(i).humidity;}
        }
        System.out.println("max humidity in Madrid next 5 days is " + maxHumidity);
        Assert.assertTrue(maxHumidity > percentageOfHumidity, String.format("Humidity in Madrid next 5 days is less than %s percentage", percentageOfHumidity));
    }
}
