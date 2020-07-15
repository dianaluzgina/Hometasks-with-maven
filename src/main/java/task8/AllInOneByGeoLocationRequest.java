package task8;

import static io.restassured.RestAssured.given;
import io.restassured.http.Method;
import io.restassured.response.Response;

public class AllInOneByGeoLocationRequest {
    private static final String BASE_URI = "https://api.openweathermap.org/data/2.5";
    private String parameters = "onecall?";

    public AllInOneByGeoLocationRequest(String lat, String lon){
        parameters += String.format("lat=%s&lon=%s", lat, lon);
    }

    public AllInOneByGeoLocationRequest withExcludes(Excludes... excludes){
        parameters += "&exclude=" + excludes[0].name().toLowerCase();
        if(excludes.length > 1){
            for(int i = 1; i < excludes.length; ++i){
                parameters += "," + excludes[i].name().toLowerCase();
            }
        }
        return this;
    }

    public AllInOneByGeoLocationRequest withAppid(String appid){
        parameters += "&appid=" + appid;
        return this;
    }

    public Response doRequest(){
        return given()
                .log().all()
                .baseUri(BASE_URI)
                .request(Method.GET, parameters);
    }

    public enum Excludes{
        CURRENT,
        MINUTELY,
        HOURLY,
        DAILY
    }
}
