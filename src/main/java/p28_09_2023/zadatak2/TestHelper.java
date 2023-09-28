package p28_09_2023.zadatak2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class TestHelper {
    private WebDriver driver;

    public TestHelper(WebDriver driver) {
       this.driver = driver;
    }

    public boolean elementExists(By criteria){

        try{
           driver.findElement(criteria);
           return true;
        }catch (Exception a){
           return false;
        }
    }
    public boolean elementExistsByList(By criteria){
        return !driver.findElements(criteria).isEmpty();
    }

    public void setDefaultImplicitWait(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    public void setImplicitWait(int vreme){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(vreme));
    }
}
