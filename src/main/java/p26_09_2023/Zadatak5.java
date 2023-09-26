package p26_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadatak5 {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://demoqa.com/automation-practice-form");
        Thread.sleep(2000);

        WebElement checkbox = driver.findElement(By.cssSelector("[for='hobbies-checkbox-1']"));
        checkbox.click();
        Thread.sleep(2000);
        WebElement find = driver.findElement(By.id("hobbies-checkbox-1"));
        System.out.println(find.isSelected());






    }
}
