package p26_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadatak6 {
    public static void main(String[] args) {
        /*
        Ucitati stranicu https://google.com
            Maksimizovati prozor
            Prostavite dimenzije prozora na 700px i visinu na 700px
         */

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://google.com ");
        driver.manage().window().maximize();
        driver.manage().window().setSize(new Dimension(700, 700));

        Dimension size = driver.manage().window().getSize();

        System.out.println(size.width);
        System.out.println(size.height);


    }
}
