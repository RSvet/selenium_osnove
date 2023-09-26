package p26_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Zadatak3 {
    public static void main(String[] args) {
        /*
        3.Zadatak
        Napisti program koji:
        Ucitava stranicu https://s.bootsnipp.com/iframe/z80en
        Hvata sve elemente iz tabele i stampa tekst svakog elementa. Kako da od nekog elementa procitamo tekst imate na sledecem linku
        Ceka 5s
        Zatvara pretrazivac
        Stampa treba da bude kao u primeru:
        John	Doe	john@example.com
        Mary	Moe	mary@example.com
        July	Dooley	july@example.com
         */

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://s.bootsnipp.com/iframe/z80en");

        driver.findElement(By.cssSelector("ul.nav-tabs--vertical>li:first-child")).click();

        List<WebElement>allRows = driver.findElements(By.cssSelector("#lorem table>tbody>tr"));

        for (int i = 0; i < allRows.size(); i++) {
          List<WebElement>cells = allRows.get(i).findElements(By.tagName("td"));
            for (int j = 0; j < cells.size(); j++) {
                System.out.print(cells.get(j).getText()+" ");
            }
            System.out.println();

        }
    }
}
