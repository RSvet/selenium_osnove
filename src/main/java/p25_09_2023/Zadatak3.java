package p25_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Zadatak3 {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://demoqa.com/text-box");

        ArrayList<String>osoba1 = new ArrayList<>(Arrays.asList("Marija Simic", "marija@mail.com","Novi Sad", "Beograd"));
        ArrayList<String>osoba2 = new ArrayList<>(Arrays.asList("Tijana Micic", "tijana@mail.com","Vrsac", "Beograd"));
        ArrayList<String>osoba3 = new ArrayList<>(Arrays.asList("Milos Maric", "milos@mail.com","Subotica", "Apatin"));

        List<ArrayList> sveOsobe = new ArrayList<>(Arrays.asList(osoba1, osoba2, osoba3));

        for (int i = 0; i < sveOsobe.size(); i++) {
            driver.findElement(By.xpath("//input[@placeholder='Full Name']")).sendKeys((CharSequence) sveOsobe.get(i).get(0));
            driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys((CharSequence) sveOsobe.get(i).get(1));
            driver.findElement(By.cssSelector("textarea#currentAddress")).sendKeys((CharSequence) sveOsobe.get(i).get(2));
            driver.findElement(By.cssSelector("textarea#permanentAddress")).sendKeys((CharSequence) sveOsobe.get(i).get(3));
            driver.findElement(By.id("submit")).click();
            Thread.sleep(2000);

            driver.navigate().refresh();
       }
        driver.quit();



    }
}
