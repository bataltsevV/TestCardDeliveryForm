package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;


public class CardDeliveryFormTest {

    @Test
    void sendFormTest() {
        /* Находим текущую дату, после чего добавляем три дня, задаем формат даты, получаем переменную с минимальновозможной датой доставки */
        LocalDate deliveryDateCard = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String minDateOfMeeting = deliveryDateCard.format(formatter);

        /*Configuration.holdBrowserOpen = true;*/
        open("http://localhost:9999");
        $("[data-test-id='city'] [placeholder='Город']").setValue("Йошкар-Ола");
        $("[data-test-id='date'] [class='input__box'] [placeholder='Дата встречи']").sendKeys(Keys.CONTROL+"a");
        $("[data-test-id='date'] [class='input__box'] [placeholder='Дата встречи']").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] [class='input__box'] [placeholder='Дата встречи']").setValue(minDateOfMeeting);
        $("[data-test-id='name'] [type=text]").setValue("Дед-Лайн Дмитрий");
        $("[data-test-id='phone'] [type=tel]").setValue("+79029039293");
        $x("//*[contains(@class,'checkbox__text')]").click();
        $x("//*[contains(text(),'Забронировать')]").click();
        $("[data-test-id='notification'] [class='notification__content']").should(Condition.visible, Duration.ofSeconds(13));
        $("[data-test-id='notification'] [class='notification__content']").shouldHave(Condition.exactText("Встреча успешно забронирована на " + minDateOfMeeting));
    }

}
