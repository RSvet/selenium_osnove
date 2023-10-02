package p02_10_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

public class Zadatak6 {
    public static void main(String[] args) throws IOException {
        /*
        Zadatak
        Po tekstu zadataka 4, kreirajte screenshot i sacuvajte ga u
        folderu screenshots pod imenom screenshot-[dan]-[mesec]-[godina] [sat]-[minut]-[sekund].jpg
        Koristan link https://www.javatpoint.com/java-date-to-string
         */

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh-mm-ss");
        String dateS = dateFormat.format(date);

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.navigate().to("https://google.com");

        driver.manage().window().maximize();

        Helper.takeAScreenshot(driver,"screenshots/screenshot-"+dateS+".jpg");



    }
    }

