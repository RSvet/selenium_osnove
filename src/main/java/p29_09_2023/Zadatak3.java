package p29_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.SQLOutput;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Zadatak3 {
    public static void main(String[] args) throws InterruptedException {
        /*
        3.Zadatak (ZA VEZBANJE)
Napisati program koji implementira search test case za task-ove:
Ucitati stranicu https://s.bootsnipp.com/iframe/8dqr
Klik na filter dugme iz Tasks tabele
Ceka da polje za input bude vidljivo. (Postaviti odgovarajuce poruke u slucaju greske)
Za pretragu unosi tekst za koji nema rezultata pretrage npr: dsdsdsds
Ceka da se pojavi No results found red i proverava ispisanu poruku da li je tekst “No results found”
Za pretragu unosi sledeci tekst mi
Validira da red No results found  vise ne postoji
Validira rezultate pretrage 🔥
Pravila pretrage:
Red ce biti u rezultatu ukoliko bar jedna kolona tog reda sadrzi termin pretrage.
Pretraga nije case sensitive, sto znaci da radi i za velika i mala slova.
Ispisuje odgovarajuce poruke
Klik na filter dugme
Ceka da polje za pretragu postane nevidljivo. (Postaviti odgovarajuce poruke u slucaju greske)
         */

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://s.bootsnipp.com/iframe/8dqr");

        List<WebElement> tableRows = driver.findElements(By.cssSelector("#task-table>tbody>tr"));

        driver.findElement(By.cssSelector(".col-md-6:nth-child(2) .pull-right")).click();
        WebElement inputSearch = driver.findElement(By.cssSelector(".col-md-6:nth-child(2) .panel-body input"));
        wait
                .withMessage("Filter tasks input is not visible")
                .until(ExpectedConditions.visibilityOf(inputSearch)).sendKeys("dsdsdsds");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#task-table .filterTable_no_results td")));
        if (driver.findElement(By.cssSelector("#task-table .filterTable_no_results td")).getText().equals("No results found"))
            System.out.println("No results found message is present.");

        Thread.sleep(2000);

        inputSearch.clear();
        String text = "mi";
        inputSearch.sendKeys(text);

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#task-table .filterTable_no_results td")));
        System.out.println("Text '"+text+"' is entered in the filter search field. No results found message is not present.");

        for (int i = 0; i < tableRows.size(); i++) {
            boolean check=false;
            List<WebElement>rowCells = tableRows.get(i).findElements(By.tagName("td"));
            for (int j = 0; j < rowCells.size(); j++) {
                if(rowCells.get(j).getAttribute(("innerHTML")).toLowerCase().contains(text.toLowerCase())){
                    check = true;
                }
            }
            if(check){
                    wait
                            .withMessage("Error - Row no." + (i + 1) + "contains results but is not in search result")
                            .until(ExpectedConditions.visibilityOf(tableRows.get(i)));
                    System.out.println("Row no. " + (i + 1) + " contains results.");
                }
        }
        Thread.sleep(2000);
        driver.findElement(By.cssSelector(".col-md-6:nth-child(2) .pull-right")).click();
        wait
                .withMessage("Filter tasks input is still visible.")
                .until(ExpectedConditions.invisibilityOf(inputSearch));
        System.out.println("Input search field is not visible anymore.");

    }
}

