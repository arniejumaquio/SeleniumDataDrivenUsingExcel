package rahulshettyacademy;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class SampleTest3 {

    @Test( dataProvider = "getInvalidCredentials")
    public void loginErrorValidationTest(String testScenario,String userName,String passWord) throws IOException {

        System.out.println("Test Scenario = "+testScenario);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless=new");

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(180));

        driver.get("https://rahulshettyacademy.com/client/");

        WebElement emailField = driver.findElement(By.xpath("//input[@placeholder='email@example.com']"));
        WebElement passwordField = driver.findElement(By.cssSelector("input[placeholder='enter your passsword']"));
        WebElement loginButton = driver.findElement(By.id("login"));

        emailField.sendKeys(userName);
        passwordField.sendKeys(passWord);
        loginButton.click();

        WebElement errorToast = driver.findElement(By.cssSelector("div[aria-label='Incorrect email or password.']"));
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        webDriverWait.until(ExpectedConditions.visibilityOf(errorToast));
        String errorMessage = errorToast.getText();

        Assert.assertEquals(errorMessage, "Incorrect email or password.");

        driver.quit();
    }

    @DataProvider(name="getInvalidCredentials")
    public Object[][]  getData() throws IOException {

      GetDataFromExcelUtility getDataFromExcelUtility = new GetDataFromExcelUtility();
      Object[][] data = getDataFromExcelUtility.getData("src/main/resources/TestData2.xlsx","Sheet1");
      return data;

    }


}
