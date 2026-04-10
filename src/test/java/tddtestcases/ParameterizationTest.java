package tddtestcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParameterizationTest {
    WebDriver driver;

    @Test
    @Parameters("browser")
    public void testWithParameters(String browser) {
        System.out.println("Browser name is "+ browser);
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
            driver.get("https://www.google.com");
        }
    }
}
