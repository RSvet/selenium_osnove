package d02_10_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Zadatak5 {
    public static void main(String[] args) {
        /*
        Zadatak (za vezbanje)
Napisati program koji:
●	Ucitava stranicu https://blueimp.github.io/jQuery-File-Upload/
●	Uploaduje sve cetiri slike odjenom (slike iz prvog zadatka)
●	Ceka da se prikazu 4 item-a a upload
●	Klik na upload
●	Ceka da se upload zavrsi
         */

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        List<String> imgPaths =
                new ArrayList<>(Arrays.asList(
                        "box_images/5544590_11zon.jpg",
                        "box_images/puppies_11zon.jpg",
                        "box_images/puppy.jpg",
                        "box_images/puppy cat_11zon.jpg"));


        String paths="";
        for (int i = 0; i < imgPaths.size(); i++) {
            paths += new File(imgPaths.get(i)).getAbsolutePath();
            if(i<imgPaths.size()-1)
                paths += "\n";
        }

        WebElement inputElement = driver.findElement(By.cssSelector("input[type='file']"));
        inputElement.sendKeys(paths);

        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("tbody.files>tr.template-upload"),imgPaths.size()));
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("tbody.files>tr.template-download"),imgPaths.size()));
        System.out.println("Upload finished!");

    }
}
