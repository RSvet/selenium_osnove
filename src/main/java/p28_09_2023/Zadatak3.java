package p28_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Zadatak3 {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("file:///C:/Users/Svetlana/Desktop/bootcamp/projekti-rad%20na%20casu/Zadatak4.html");
        driver.findElement(By.id("showInBtn")).click();

        wait
            .withMessage("Poruka se nije pojavila za 10s")
            .until(ExpectedConditions.presenceOfElementLocated(By.id("id-0")));
        System.out.println("Prva poruka se pojavila");

        //vezbanje
        for (int i = 0; i < 4; i++) {
            wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//*[contains(@id, 'id-')]"),i+1));
//            wait
//                .withMessage(i+1+". poruka se nije pojavila za 10s")
//                .until(ExpectedConditions.presenceOfElementLocated(By.id("id-"+i)));
            driver.findElement(By.id("showInBtn")).click();
        }

    }
}
