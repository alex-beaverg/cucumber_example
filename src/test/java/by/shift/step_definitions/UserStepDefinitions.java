package by.shift.step_definitions;

import by.shift.context.Context;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;

import static by.shift.constants.Constants.BASE_HOROSCOPE_URI;
import static by.shift.requests.Requests.getRequestToHoroscope;

public class UserStepDefinitions {

    @Дано("Задан знак зодиака {string}")
    public void setZodiacSign(String zodiacSign) {
        System.out.printf("Задаем знак зодиака: \"%s\"%n", zodiacSign);
        Context.putContext("Zodiac Sign", zodiacSign);
    }

    @Когда("Я отправляю запрос на гороскоп для этого знака")
    public void sendRequestToHoroscope() {
        System.out.printf("Отправляем запрос по адресу: %s%n",
                BASE_HOROSCOPE_URI + Context.getContext("Zodiac Sign"));
        Context.putContext("Horoscope Response",
                getRequestToHoroscope(String.valueOf(Context.getContext("Zodiac Sign"))));
    }
}
