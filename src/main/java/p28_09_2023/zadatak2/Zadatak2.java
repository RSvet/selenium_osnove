package p28_09_2023.zadatak2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Zadatak2 {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        TestHelper help = new TestHelper(driver);

        driver.get("https://demoqa.com/modal-dialogs");

        help.setDefaultImplicitWait();
        driver.findElement(By.id("showLargeModal")).click();
        help.setImplicitWait(5);

        System.out.println(help.elementExists(By.className("modal-content")));

        System.out.println(help.elementExistsByList(By.className("modal-content")));


    }
}
