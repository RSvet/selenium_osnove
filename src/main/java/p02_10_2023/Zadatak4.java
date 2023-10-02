package p02_10_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.time.Duration;
import java.io.File;



public class Zadatak4 {
    /*
Napisati program koji:
Kreirati screenshots folder u projektu
Ucitava stranicu https://google.com/
Kreira screenshot stranice
Sacuvati screenshot u folderu screenshots pod imenom screenshot1.jpg
Koristan link 1. LAKSE CE VAM BITI PREKO OVOG
Koristan link 2
Koristan link 3
     */

    public static void main(String[] args) throws IOException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.navigate().to("https://google.com/");


        driver.manage().window().maximize();

        File f=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);


        Helper.takeAScreenshot(driver,"screenshots/screenshot1.jpg");






    }
}
