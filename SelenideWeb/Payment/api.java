package Templates;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.jetbrains.annotations.NotNull;

import java.util.SplittableRandom;

import static Config.Config.*;
import static Config.AuthValues.*;
import static Config.Selectors.*;
import static Config.Urls.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static java.time.Duration.ofMillis;
import static com.codeborne.selenide.Selectors.*;

class PaymentInfo {
    public SelenideElement ancestor;
    public String scoreName;
    public String bankName;
    public String recipientName;
    public String bin;
    public String awesomeText;
    public String sum;
    public String knp;
    public String senderScore;

    public void RecipientInfo(SelenideElement recipient) {
        this.scoreName = recipient.find(".wrap-text").getText();
        this.bankName = recipient.find(".wrap-text.text-gray").getText();
        this.ancestor = recipient.ancestor("div");
        this.recipientName= ancestor.find(".recipient-title").getText();
        this.bin = ancestor.find(".small.text-gray").getText();
        recipient.click();
    }

    public void KnpInfo(SelenideElement knp) {
        this.knp = knp.find("span").getText();
        knp.click();
    }

    public void SenderInfo(SelenideElement sender) {
        var x = sender.ancestor("account-item");
        this.senderScore = x.find("text-dark").getText();
        sender.click();
    }
}

public class api {
    public static void authBasic() {
        $(byXpath(phonePath)).shouldBe(visible, ofMillis(callbackDuration)).val(login);
        $(byXpath(passPath)).shouldBe(visible, ofMillis(callbackDuration)).val(pass).pressEnter();
        $(byXpath(smsPath)).shouldBe(visible, ofMillis(callbackDuration)).val(smsMock).pressEnter();
    }

    public static void selectIp() {
        $(byXpath(ipCollectionPath)).shouldBe(visible, ofMillis(callbackDuration)).click();
        $(byXpath(neededIpPath)).shouldBe(visible, ofMillis(callbackDuration)).click();
    }

    public static PaymentInfo createPayment() {
        open(orderPaymentUrl);
        int r;
        var random = new SplittableRandom();
        var paymentInfo = new PaymentInfo();

        var recipient = PickRandomFromDropdown(directorySelectPath, directoryCompanyList, not(Condition.matchText("Сбербанк")));
        paymentInfo.RecipientInfo(recipient);

        var knp = PickRandomFromDropdown(knpPath, knpListPath);
        paymentInfo.KnpInfo(knp);

        r = random.nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
        String encodedText = String.format(awesomeText, r);
        $(textArea).shouldBe(visible, ofMillis(callbackDuration)).val(encodedText);

        var sender = PickRandomFromDropdown(scoreSelectPath, scoreListPath, not(Condition.exactText("0")));
        paymentInfo.SenderInfo(sender);

        r = random.nextInt(1, 1001);
        $(byXpath(paymentSumPath)).shouldBe(visible, ofMillis(callbackDuration)).val(Integer.toString(r));

        return paymentInfo;
        //return new PaymentInfo(el, knp, sender, encodedText, Integer.toString(r));
    }

    public static void ConfirmAndCheckPayment(PaymentInfo paymentInfo) {
        $(byXpath(submitPaymentPath)).shouldBe(visible, ofMillis(callbackDuration)).click();
        $(byXpath(smsPath)).shouldBe(visible, ofMillis(callbackDuration)).val(smsMock).pressEnter();
        $(byXpath(checkPaymentStatus)).shouldBe(visible, ofMillis(callbackDuration)).click();
        $(byXpath(processingPayments)).shouldHave(Condition.matchText(paymentInfo.awesomeText)).click();

        // block money
        $(byXpath("//h3/j-money[@showsign=\"true\"]//span[2]")).shouldHave(Condition.exactText(paymentInfo.sum), ofMillis(callbackDuration));
        $(byXpath("//section[contains(@class, \"details__block\")]/div[contains(@class, \"p2\")][1]")).shouldHave(Condition.exactText(paymentInfo.recipientName), ofMillis(callbackDuration));
        $(byXpath("//section[4]//div[contains(@class, \"j-desc-row\")][4]/div[2]")).shouldHave(Condition.exactText(paymentInfo.scoreName), ofMillis(callbackDuration));
        $(byXpath("//section[5]//div[contains(@class, \"j-desc-row\")][5]/div[2]")).shouldHave(Condition.exactText(paymentInfo.bankName), ofMillis(callbackDuration));
        $(byXpath("//section[5]//div[contains(@class, \"j-desc-row\")][5]/div[2]")).shouldHave(Condition.exactText(paymentInfo.knp), ofMillis(callbackDuration));
        $(byXpath("//section[5]//div[contains(@class, \"j-desc-row\")][6]/div[2]")).shouldHave(Condition.exactText(paymentInfo.bin), ofMillis(callbackDuration));
    }

    public static void SaveAndCheckDraft(String encodedText) {
        $(byXpath(saveDraftPath)).shouldBe(visible, ofMillis(callbackDuration)).click();
        open(draftUrl);
        $(byXpath(processingPayments)).shouldHave(Condition.matchText(encodedText));
    }

    private static SelenideElement PickRandomFromDropdown(String dpSelectorPath, String elSelectorPath, @NotNull Condition condition) {
        $(byXpath(dpSelectorPath)).shouldBe(visible, ofMillis(callbackDuration)).click();
        $(byXpath(elSelectorPath)).shouldBe(visible, ofMillis(callbackDuration));

        ElementsCollection collection;
        collection = $$(byXpath(elSelectorPath)).filter(condition);

        int r = new SplittableRandom().nextInt(0, collection.size());
        return collection.get(r);
    }
    private static SelenideElement PickRandomFromDropdown(String dpSelectorPath, String elSelectorPath) {
        $(byXpath(dpSelectorPath)).shouldBe(visible, ofMillis(callbackDuration)).click();
        $(byXpath(elSelectorPath)).shouldBe(visible, ofMillis(callbackDuration));

        ElementsCollection collection;
        collection = $$(byXpath(elSelectorPath));

        int r = new SplittableRandom().nextInt(0, collection.size());
        return collection.get(r);
    }
}
