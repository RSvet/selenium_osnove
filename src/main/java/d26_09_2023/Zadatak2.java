package d26_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Zadatak2 {
    public static void main(String[] args) throws InterruptedException {
        /*

    2.	Zadatak
    Napisati program koji:
    ●	Ucitava stranicu https://s.bootsnipp.com/iframe/Dq2X
    ●	Klikce na svaki iks da ugasi obavestenje i proverava da li se nakon klika element obrisao sa stranice
        i ispisuje odgovarajuce poruke (OVO JE POTREBNO RESITI PETLJOM)
    ●	POMOC: Brisite elemente odozdo.
    ●	(ZA VEZBANJE)Probajte da resite da se elemementi brisu i odozgo
         */

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://s.bootsnipp.com/iframe/Dq2X");
        Thread.sleep(1000);
        List<WebElement> buttons = driver.findElements(By.cssSelector(".alert>button"));

        for (int i = buttons.size()-1; i >= 0; i--) {
            int previousListSize = buttons.size();
            System.out.println(previousListSize);
            buttons.get(i).click();
            buttons = driver.findElements(By.cssSelector(".alert>button"));
            if(previousListSize - buttons.size()==1)
            System.out.println("Element je obrisan");
            else System.out.println("Element nije obrisan");
            Thread.sleep(2000);
        }

        /*
        	(ZA VEZBANJE)Probajte da resite da se elemementi brisu i odozgo
         */

//        for (int i = 0; i < buttons.size(); i++) {
//            int previousListSize = driver.findElements(By.cssSelector(".alert>button")).size();
//            buttons.get(i).click();
//            List<WebElement> buttons2 = driver.findElements(By.cssSelector(".alert>button"));
//            if(previousListSize - buttons2.size()==1)
//            System.out.println("Element je obrisan");
//            else System.out.println("Element nije obrisan");
//            Thread.sleep(2000);
//        }

        driver.quit();
    }
}
