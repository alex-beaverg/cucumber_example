package by.shift.hooks;

import io.cucumber.java.BeforeAll;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

public class ApiHoroscopeHooks {

    @BeforeAll
    public static void beforeAll() {
        RestAssured.filters(
                new RequestLoggingFilter(LogDetail.HEADERS),
                new ResponseLoggingFilter(LogDetail.STATUS)
        );
    }
}
