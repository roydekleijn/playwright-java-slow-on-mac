import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class LoginTest {
    Playwright playwright;
    Browser browser;
    // New instance for each test method.
    Page page;

    @AfterMethod
    public void tearDown(ITestResult result) {
        System.out.println(result.getEndMillis() - result.getStartMillis());
    }

    @Test(invocationCount = 2)
    public void login() {
        playwright = Playwright.create();
        playwright.selectors().setTestIdAttribute("data-test");
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setChannel("msedge"));
        page = browser.newPage();

        page.navigate("https://practicesoftwaretesting.com");
        page.getByTestId("nav-sign-in").click();
        page.getByTestId("email").fill("customer@practicesoftwaretesting.com");
        page.getByTestId("password").fill("welcome01");
        page.getByTestId("login-submit").click();
        assertEquals(page.locator("h1").textContent(), "My account");

        playwright.close();
    }

}
