package by.shift.step_definitions;

import by.shift.context.Context;
import io.cucumber.java.ru.Также;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Allure;
import io.restassured.response.Response;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static by.shift.requests.Requests.checkNotJsonFormatResponseBody;
import static org.assertj.core.api.Assertions.assertThat;

public class ApiHoroscopeStepDefinitions {

    @Тогда("Статус ответа должен быть {string}")
    public void checkResponseStatus(String expectedStatusCode) {
        Response response = (Response) Context.getContext("Horoscope Response");
        System.out.printf("Проверяем Статус Код ответа, должен быть равен: %s%n", expectedStatusCode);
        Allure.step(
                String.format("Проверка статус кода ответа, должен быть равен: %s", expectedStatusCode),
                () -> {
                    Allure.attachment("Ожидаемый статус код", expectedStatusCode);
                    Allure.attachment("Фактический статус код", String.valueOf(response.getStatusCode()));
                    assertThat(response.getStatusCode())
                        .isEqualTo(Integer.parseInt(expectedStatusCode));
                }
        );
    }

    @Также("В ответе поле \"sign\" должно быть равно {string}")
    public void checkResponseSignField(String expectedSign) {
        Response response = (Response) Context.getContext("Horoscope Response");
        if (response.getStatusCode() == 404) {
            checkNotJsonFormatResponseBody(response);
        } else {
            System.out.printf("Проверяем поле \"sign\" ответа, должно быть равно: \"%s\"%n", expectedSign);
            Allure.step(
                    String.format("Проверка значения поля \"sign\" в ответе, должно быть равно: %s", expectedSign),
                    () -> {
                        Allure.attachment("Ожидаемое значение поля \"sign\"", expectedSign);
                        Allure.attachment("Фактическое значение поля \"sign\"",
                                String.valueOf(response.jsonPath().getString("sign")));
                        assertThat(response.jsonPath().getString("sign"))
                                .isEqualTo(expectedSign);
                    }
            );
        }
    }

    @Также("В ответе должна быть сегодняшняя дата в поле \"date\"")
    public void checkResponseDateField() {
        Response response = (Response) Context.getContext("Horoscope Response");
        if (response.getStatusCode() == 404) {
            checkNotJsonFormatResponseBody(response);
        } else {
            String todayDate = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
            System.out.printf("Проверяем поле \"date\" ответа, дата должна быть сегодняшней: \"%s\"%n", todayDate);
            Allure.step(
                    "Проверка значения поля \"date\" в ответе, дата должна быть сегодняшней",
                    () -> {
                        Allure.attachment("Ожидаемое значение поля \"date\"", todayDate);
                        Allure.attachment("Фактическое значение поля \"sign\"",
                                String.valueOf(response.jsonPath().getString("date")));
                        assertThat(response.jsonPath().getString("date"))
                                .matches(todayDate);
                    }
            );
        }
    }
}
