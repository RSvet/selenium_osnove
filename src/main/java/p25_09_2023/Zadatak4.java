package p25_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadatak4 {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/webtables");
        driver.findElement(By.cssSelector("span#edit-record-1")).click();

        driver.findElement(By.cssSelector("form input[placeholder='First Name']")).clear();
        driver.findElement(By.cssSelector("form input[placeholder='First Name']")).sendKeys("Ana");

        driver.findElement(By.cssSelector("form input#lastName")).clear();
        driver.findElement(By.cssSelector("form input#lastName")).sendKeys("Petrovic");

        driver.findElement(By.cssSelector("form input#userEmail")).clear();
        driver.findElement(By.cssSelector("form input#userEmail")).sendKeys("ana@mail.com");

        driver.findElement(By.xpath("//form//input[@id='age']")).clear();
        driver.findElement(By.xpath("//form//input[@id='age']")).sendKeys("28");

        driver.findElement(By.xpath("//form//input[@placeholder='Salary']")).clear();
        driver.findElement(By.xpath("//form//input[@placeholder='Salary']")).sendKeys("10000");

        driver.findElement(By.xpath("//form//input[@id='department']")).clear();
        driver.findElement(By.xpath("//form//input[@id='department']")).sendKeys("Insurance");

        driver.findElement(By.xpath("//button[@id='submit']")).click();

        Thread.sleep(2000);

        driver.quit();
    }
}
