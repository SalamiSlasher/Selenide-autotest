package org.selenide.examples.appium;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import static org.selenide.examples.appium.Selectors.*;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;


import java.time.Duration;
public class VlifeAuthTest {
    @BeforeEach
    void setUp() {
        closeWebDriver();
        Configuration.browserSize = null;
        Configuration.browser = AndroidDriverWithCalculator.class.getName();
        open();
    }

    @Test
    void calculator() throws InterruptedException {
        $(By.id(skipBtn)).click();
        $(By.id(loginBtn)).click();
        $(By.id(phoneNum)).val("7010912070");
        $(By.id(phoneContinue)).click();
        $(By.id(passwordID)).val("asdfghjklP0");
        $(By.xpath(passContinue)).click();

        $(By.id(skipBtn)).click();

        $(By.id("username_text_view")).shouldHave(Condition.exactText("Двн Дан"));
        $(By.id("user_data_text_view")).shouldHave(Condition.exactText("+7701 091 2070 • Алматы"));
    }
    @Test
    void encoding() {
        System.out.println("Привет!");
    }
}
