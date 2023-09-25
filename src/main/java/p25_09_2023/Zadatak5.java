package p25_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Zadatak5 {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        List<String> linkovi = new ArrayList<>(Arrays.asList("https://google.com/","https://youtube.com/","https://www.ebay.com/","https://www.kupujemprodajem.com/"));
        for (int i = 0; i < linkovi.size(); i++) {
            driver.get(linkovi.get(i));
            System.out.println(driver.getTitle());
        }
        driver.quit();
    }
}
