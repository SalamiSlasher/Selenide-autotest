package Config;

public class Selectors {
    public static String phonePath = "//j-phone-input[1]/div[1]/input[1]";
    public static String passPath = "//j-password-input[1]/div[1]/input[1]";
    public static String smsPath = "//j-sms-input[1]/div[1]/input[1]";

    public static String ipCollectionPath = "//j-aside[1]/section[2]/section[1]/section[1]/section[1]/a[1]";
    public static String neededIpPath = "//j-aside[1]//button[2]";

    public static String directorySelectPath = "//j-payment-order[1]/div[1]//fieldset[1]/div[1]/button[1]";
    public static String directoryCompanyList = "//li//div[@class=\"account-pill account-pill_gray account-pill_fill ng-star-inserted\"]";
    public static String textArea = "textarea";
    public static String awesomeText = "This is awesome text with code: %d";

    public static String knpPath = "//j-select[@role=\"listbox\"]";
    public static String knpListPath = "//div[@role=\"option\"]";

    public static String scoreSelectPath = "//j-account-dropdown";
    public static String scoreListPath = "//j-money/span[2]";

    public static String paymentSumPath = "//input[@id=\"j-input-6\"]";
    public static String submitPaymentPath = "//button[@type=\"submit\"]";
    public static String checkPaymentStatus = "//button[@class=\"btn btn-primary btn-block\"]";

    public static String processingPayments = "//div[@class=\"text-muted text-small transaction__description\"]";

    public static String saveDraftPath = "//j-payment-order[1]/div[1]/form[1]/div[4]/div[1]/button[1]";

}
