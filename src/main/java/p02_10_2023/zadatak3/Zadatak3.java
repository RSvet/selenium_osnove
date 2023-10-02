package p02_10_2023.zadatak3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import java.io.*;
import java.time.Duration;

public class Zadatak3 {
    public static void main(String[] args) throws IOException {
        /*
        Napisati program koji
Kreirati folder downloads folder u projektu
Ucitava stranu https://www.pexels.com/photo/a-woman-holding-a-laptop-in-the-living-room-6585859/
Cita href atribut sa glavne slike sa stranice
Koristi link iz href atributa za skidanje slike
Sliku sacuvajte u folderu downloads pod nazivom woman-holding-laptop.jpeg
Koristan link za skidanje fajlova u javi
Azurirajte gitignore da ignorise downloads folder
         */

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.navigate().to("https://www.pexels.com/photo/a-woman-holding-a-laptop-in-the-living-room-6585859/");
        String link = driver.findElement(By.className("PhotoZoom_mediumLink__MCCiP")).getAttribute("href");

        Filedownloader.downloadImg(link, "downloads/woman-holding-laptop.jpg");


        // drugi nacin iz Filedownloader klase
        /*
         String url = "https://cdn.britannica.com/29/150929-050-547070A1/lion-Kenya-Masai-Mara-National-Reserve.jpg ";
        String downloadFilePath = "downloads/ljuti-lav.jpg";
        Helper.downloadUsingStream(url, downloadFilePath);
         */


    }


}
