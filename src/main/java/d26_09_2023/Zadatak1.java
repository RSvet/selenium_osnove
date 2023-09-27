package d26_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Scanner;

public class Zadatak1 {
    /*
    1.	Zadatak
        Napisati program koji:
        ●	Ucitava stranicu https://demoqa.com/automation-practice-form
        ●	Popunjava formu sta stranice. Korisnik unosi podatke sa tastature za popunu forme.
        ●	(za vezbanje) Probajte da unese i datum. Sa datumom se radi isto kao i sa obicnim inputom sa sendKeys.
        ●	Klik na submit

     */
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://demoqa.com/automation-practice-form");


        Scanner s = new Scanner(System.in);
        System.out.print("Enter first name: ");
        String name = s.next();
        driver.findElement(By.id("firstName")).sendKeys(name.substring(0,1).toUpperCase()+name.substring(1).toLowerCase());
        System.out.print("Enter surname: ");
        String surname = s.next();
        driver.findElement(By.id("lastName")).sendKeys(surname.substring(0,1).toUpperCase()+surname.substring(1).toLowerCase());
        System.out.print("Enter mail: ");
        driver.findElement(By.id("userEmail")).sendKeys(s.next());
        System.out.print("Choose sex: ");
        String pol = s.next();
        driver.findElement(By.xpath("//label[text()='"+pol.substring(0, 1).toUpperCase() + pol.substring(1).toLowerCase()+"']")).click();
        System.out.print("Enter phone number: ");
        driver.findElement(By.id("userNumber")).sendKeys(s.next());

        WebElement dateInput = driver.findElement(By.id("dateOfBirthInput"));
        String inputValue = dateInput.getAttribute("value");
          System.out.print("Month of birth: ");
          String month = s.next();
          System.out.print("Day of birth (number): ");
          String day = s.next();
          System.out.print("Year of birth: ");
          String year = s.next();
          dateInput.click();

        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.HOME).build().perform();
        actions.keyDown(Keys.LEFT_SHIFT);
        for (int i = 0; i < inputValue.length(); i++){
            actions.sendKeys(Keys.ARROW_RIGHT);
        }
        actions.keyUp(Keys.LEFT_SHIFT);
        actions.build().perform();

        dateInput.sendKeys(month+"/"+day+"/"+year);
        dateInput.sendKeys(Keys.ENTER);

        System.out.print("Enter subject: ");
        String subject = s.next();
        driver.findElement(By.id("subjectsInput")).sendKeys(subject);
        driver.findElement(By.cssSelector(".subjects-auto-complete__option:first-child")).click();
        System.out.println("Choose hobby (sports, reading, music)");
        String hobby = s.next();
        driver.findElement(By.xpath("//label[text()='"+hobby.substring(0,1).toUpperCase()+hobby.substring(1).toLowerCase()+"']")).click();
        driver.findElement(By.id("uploadPicture")).sendKeys("C:\\Users\\Svetlana\\Pictures\\Backgrounds\\1238351.jpg");

        System.out.println("Enter current address");
        String address = s.next();
        driver.findElement(By.id("currentAddress")).sendKeys(address);












    }
}
