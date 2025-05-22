package by.shift.requests;

import io.qameta.allure.Allure;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;

import static by.shift.constants.Constants.BASE_HOROSCOPE_URI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.not;

public class Requests {

    public static Response getRequestToHoroscope(String zodiacSign) {
        return Allure.step(
                String.format("Запрос на API гороскопа по знаку зодиака %s", zodiacSign),
                () -> given()
                        .baseUri(BASE_HOROSCOPE_URI)
                        .basePath(zodiacSign)
                        .contentType(ContentType.JSON)
                        .when()
                        .get()
                        .then()
                        .extract().response()
        );
    }

    public static void checkNotJsonFormatResponseBody(Response response) {
        System.out.println("Проверка тела ответа на несоответствие формату JSON");
        Allure.step(
                "Проверка тела ответа на несоответствие формату JSON",
                () -> {
                    String responseContentType = response
                            .then()
                            .extract().contentType();
                    MatcherAssert.assertThat(responseContentType, not(ContentType.JSON.toString()));
                }
        );
    }
}
