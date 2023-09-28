package p28_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class Zadatak1 {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.get("https://demoqa.com/modal-dialogs");
        driver.findElement(By.id("showLargeModal")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

       boolean isOpened = true;
        try{
           driver.findElement(By.className("modal-content"));

        }catch (Exception a){
            isOpened=false;
        }
        if(isOpened){
            System.out.println("Otvara modal");

        }
        else System.out.println("Ne otvara modal");



        List<WebElement> modali = driver.findElements(By.className("modal-content"));

        if(modali.isEmpty()){
            System.out.println("Ne otvara modal");
        }else System.out.println("Otvara modal");



    }
}
