package base;

import Templates.api;

import static Config.Config.*;
import static Config.Urls.*;
import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.Configuration;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import ru.yandex.qatools.allure.model.SeverityLevel;

public class main {
    @Before
    public void setUp() {
        Configuration.timeout = timeout;
        Configuration.browserSize = browserSize;
        Configuration.baseUrl = baseUrl;
    }

    @Test
    @Description(value = "Payment")
    @Severity(value = SeverityLevel.CRITICAL)
    @TestCaseId("42")
    public void Test() throws InterruptedException {
        open("/auth/login");
        api.authBasic();
        api.selectIp();
        var encoded = api.createPayment(); // Transfers to Sber are not Available
        api.ConfirmAndCheckPayment(encoded);
    }

    @Test
    @Description(value = "Draft")
    @Severity(value = SeverityLevel.CRITICAL)
    @TestCaseId("42")
    public void TestDraft() {
        open("/auth/login");
        api.authBasic();
        api.selectIp();
        var encoded = api.createPayment(); // Transfers to Sber are not Available
        // api.SaveAndCheckDraft(encoded);
    }
}
