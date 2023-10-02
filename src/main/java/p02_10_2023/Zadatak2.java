package p02_10_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class Zadatak2 {
    public static void main(String[] args) {
        /*
        Napisati program koji:
Ucitava stranu https://blueimp.github.io/jQuery-File-Upload/
Uploadujte sliku
Ceka se da se pojavi slika u listi uploadovanih fajlova
Koristite uslov da broj elemenata bude 1.
Klik na Start dugme u okviru item-a koji se uploadovao
Ceka se da se pojavi delete dugme pored itema
Klik na delete dugme pored itema
Ceka se da se element obrise
Koristite da broj elemenata bude 0

         */
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.navigate().to("https://blueimp.github.io/jQuery-File-Upload/");
        WebElement inputElement = driver.findElement(By.cssSelector("input[type='file']"));
        inputElement.sendKeys(new File("test_data/puppies.jpg").getAbsolutePath());


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("tbody.files>tr.template-upload"),1));
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[data-type='DELETE']"))).click();
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("tbody.files>tr.template-download"),0));
        System.out.println("File deleted");




    }
}
