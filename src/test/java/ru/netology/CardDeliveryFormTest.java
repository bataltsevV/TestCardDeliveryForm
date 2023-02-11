package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryFormTest {
    public String generateMinDateOfMeeting() {
        Dates dates = new Dates();
        return dates.generateDate(3, "dd.MM.yyyy");
    }
    @Test
    void sendFormTest() {
        String dateOfMeeting = this.generateMinDateOfMeeting();
        //Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $("[data-test-id='city'] [placeholder='Город']").setValue("Йошкар-Ола");
        $("[data-test-id='date'] [class='input__box'] [placeholder='Дата встречи']").sendKeys(Keys.CONTROL+"a");
        $("[data-test-id='date'] [class='input__box'] [placeholder='Дата встречи']").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] [class='input__box'] [placeholder='Дата встречи']").setValue(dateOfMeeting);
        $("[data-test-id='name'] [type=text]").setValue("Дед-Лайн Дмитрий");
        $("[data-test-id='phone'] [type=tel]").setValue("+79029039293");
        $x("//*[contains(@class,'checkbox__text')]").click();
        $x("//*[contains(text(),'Забронировать')]").click();
        $("[data-test-id='notification'] [class='notification__content']").should(Condition.visible, Duration.ofSeconds(15));
        $("[data-test-id='notification'] [class='notification__content']").shouldHave(Condition.exactText("Встреча успешно забронирована на " + dateOfMeeting));
    }
}
