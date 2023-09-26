package d25_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Zadatak2 {
    public static void main(String[] args) throws InterruptedException {
        /*
        2.	Zadatak
        ●	Niz todo-a (niz stringova) koje treba da uneti. Niz je:
        ○	Visit Paris
        ○	Visit Prague
        ○	Visit London
        ○	Visit New York
        ○	Visit Belgrade
        ●	Maksimizirati prozor
        ●	Ucitati stranicu https://example.cypress.io/todo
        ●	Program petljom prolazi kroz niz todo-a i svaki unosi na stranicu
        ○	Nakon svakog unosa todo-a, unosi se enter. Koristan link
        ●	Nakon svih unosa proci petljom kroz svaki todo koji je na stranici i za svaki cekirati da je completed.
        ●	Cekanje od 5s
        ●	Zatvorite pretrazivac
         */

        List<String> todo = new ArrayList<>(Arrays.asList("Visit Paris","Visit Prague","Visit London","Visit New York", "Visit Belgrade"));

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://example.cypress.io/todo");

        for (int i = 0; i < todo.size(); i++) {
            WebElement todoInput = driver.findElement(By.className("new-todo"));
            todoInput.sendKeys(todo.get(i));
            todoInput.sendKeys(Keys.ENTER);
        }

        List<WebElement>todoList = driver.findElements(By.cssSelector(".todo-list input[type='checkbox']"));
        for (int i = 0; i < todoList.size(); i++) {
            todoList.get(i).click();
        }

        Thread.sleep(5000);

        driver.quit();
    }
}
