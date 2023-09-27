package d26_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Zadatak3 {
    public static void main(String[] args) throws InterruptedException {
        /*
        3.	Zadatak (za vezbanje)
        ●	Niz todo-a (niz stringova) koje treba da uneti. Niz je:
        ○	Visit Paris
        ○	Visit Prague
        ○	Visit London
        ○	Visit New York
        ○	Visit Belgrade
        ●	Maksimizirati prozor
        ●	Ucitati stranicu https://example.cypress.io/todo
        ●	Program petljom prolazi kroz niz todo-a i svaki unosi na stranicu
        ○	Nakon svakog unosa todo-a, unosi se enter
        ○	Validira da li je novi todo dodat na stranici  (ispisati poruku)
        ●	Na kraju programa proci petljom i izbrisati svaki todo sa stranice (klikom na x dugme svakog todo-a)
        ●	Validirati da je na kraju programa broj todo-a na stranici 0. (ispisati poruku)
        ●	Cekanje od 5s
        ●	Zatvorite pretrazivac
         */

        List<String> todo = new ArrayList<>(Arrays.asList("Visit Paris","Visit Prague","Visit London","Visit New York", "Visit Belgrade"));

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://example.cypress.io/todo");

        for (int i = 0; i < todo.size(); i++) {
            int previousListSize = driver.findElements(By.cssSelector(".todo-list>li")).size();
            WebElement todoInput = driver.findElement(By.className("new-todo"));
            todoInput.sendKeys(todo.get(i));
            todoInput.sendKeys(Keys.ENTER);
            int newSize = driver.findElements(By.cssSelector(".todo-list>li")).size();
            if(newSize-previousListSize==1)
            System.out.println("Todo dodat na listu");
        }

        List<WebElement>closeButtons = driver.findElements(By.cssSelector(".todo-list>li button"));
        List<WebElement>items = driver.findElements(By.cssSelector(".todo-list>li"));
        for (int i = 0; i < items.size() ; i++) {
          Actions actions = new Actions(driver);
          actions.moveToElement(items.get(i)).perform();
          actions.click(closeButtons.get(i)).perform();

        }
        int brojTodo = driver.findElements(By.cssSelector(".todo-list>li")).size();
        if(driver.findElements(By.cssSelector(".todo-list>li")).isEmpty())
            System.out.println("Todo obrisani");
        else System.out.println("Broj todo"+brojTodo);

        Thread.sleep(5000);
       driver.quit();



    }
}
