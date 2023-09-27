package d26_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Zadatak4 {
    public static void main(String[] args) throws InterruptedException {
        /*
        Zadatak (Za vezbanje)
        Napisati program koji matematicku formulu koju korisnik unese izvrsaav na stranici:
        ●	Ucitati stranicu https://www.calculatorsoup.com/calculators/math/basic.php
        ●	Korisnik unosi formulu, samo osnovne matematicke operacija, npr:
        ○	1243+329=
        ○	21912-4=
        ○	12913÷4=
        ●	U programu se formula unosi kao jedan string i potrebno je razbiti formulu na karaktere.
        Za to imate metodu https://www.geeksforgeeks.org/convert-a-string-to-a-list-of-characters-in-java/
        ●	Zatim u odnosu na karakter uradite odredjenu logiku
         */

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.calculatorsoup.com/calculators/math/basic.php");

        Scanner s = new Scanner(System.in);
        System.out.print("Formula: ");
        String formula = s.next();
        List<Character> chars = new ArrayList<>();
        for (char ch : formula.toCharArray()) {
            chars.add(ch);
        }

        int indexOperation=0;
        if(chars.contains('+')){
            indexOperation =  chars.indexOf('+');
        }
        else if(chars.contains('-')){
            indexOperation = chars.indexOf('-');
        }
        else if (chars.contains('*')){
            indexOperation = chars.indexOf('*');
        }
        else if (chars.contains('/')){
            indexOperation = chars.indexOf('/');
        }
        String firstNumber = formula.substring(0,indexOperation);
        String secondNumber = formula.substring(indexOperation+1);

        driver.findElement(By.cssSelector("input[name='cs_display']")).sendKeys(firstNumber);

        if(chars.get(indexOperation)=='/'){
            driver.findElement(By.cssSelector("button[name='cs_divide']")).click();
        }
        else if (chars.get(indexOperation)=='*'){
            driver.findElement(By.cssSelector("button[name='cs_multiply']")).click();
        }
        else if (chars.get(indexOperation)=='-'){
            driver.findElement(By.cssSelector("button[name='cs_subtract']")).click();
        }
        else if (chars.get(indexOperation)=='+'){
            driver.findElement(By.cssSelector("button[name='cs_add']")).click();
        }

        driver.findElement(By.cssSelector("input[name='cs_display']")).sendKeys(secondNumber);

        driver.findElement(By.cssSelector("button[name='cs_equal']")).click();

        Thread.sleep(5000);
        driver.quit();











    }

}
