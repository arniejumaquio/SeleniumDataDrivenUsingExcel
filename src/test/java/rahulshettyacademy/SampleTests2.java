package rahulshettyacademy;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

public class SampleTests2 {

    @Test
    public void test() throws IOException {

        GetDataFromExcelUtility getDataFromExcelUtility = new GetDataFromExcelUtility();
        ArrayList<String> testData = getDataFromExcelUtility.getData("Purchase");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless=new");

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(180));

        driver.get("https://rahulshettyacademy.com/client/#/auth/login");


        WebElement emailField = driver.findElement(By.cssSelector("input#userEmail"));
        WebElement passwordField = driver.findElement(By.cssSelector("input#userPassword"));

        emailField.sendKeys(testData.get(1));
        passwordField.sendKeys(testData.get(2));

        WebElement loginButton = driver.findElement(By.id("login"));
        loginButton.click();
        
    }

}
