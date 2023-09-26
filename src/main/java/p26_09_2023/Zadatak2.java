package p26_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Zadatak2 {
    public static void main(String[] args) throws InterruptedException {
        /*
            Zadatak
            Napisati program koji:
            Ucitava stranicu https://s.bootsnipp.com/iframe/z80en
            Hvata sve elemente prve kolone i stampa tekst svakog elementa.
            Kako da od nekog elementa procitamo tekst imate na sledecem linku
            Ceka 1s
            Hvata sve elemente prvog reda i stampa tekst svakog elementa
            Ceka 5s
            Zatvara pretrazivac
         */


        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://s.bootsnipp.com/iframe/z80en");

        driver.findElement(By.cssSelector("ul.nav-tabs--vertical>li:first-child")).click();

        List<WebElement> firstColumn = driver.findElements(By.cssSelector("#lorem tbody>tr>td:first-child"));

        for (int i = 0; i < firstColumn.size(); i++) {
            System.out.println(firstColumn.get(i).getText());
        }

        Thread.sleep(1000);

        List<WebElement>firstRow = driver.findElements(By.cssSelector("#lorem tbody>tr:first-child>td"));

        for (int i = 0; i < firstRow.size(); i++) {
            System.out.println(firstRow.get(i).getText());
        }

        Thread.sleep(5000);
        driver.quit();




    }
}
