package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;


public class LeftNavPage extends BasicPage {
    public LeftNavPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void waitLeftNavMenu(){
        wait
                .withMessage("Left menu did not appear")
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("bm-item-list")));
    }
    public boolean doesLogoutButtonExist() {
        return elementExists(By.linkText("Logout"), 0);
    }

    public WebElement getLogoutLink() {
        return driver.findElement(By.linkText("Logout"));
    }

    public void clickLogoutButton(){
        getLogoutLink().click();
    }

    public List<WebElement> getMenuOptions(){
        return driver.findElements(By.cssSelector(".bm-item-list>a"));
    }

    public List<String>getTextFromMenuOptions(){
        List<String>menuOptions = new ArrayList<>();

        for (int i = 0; i < getNumberOfMenuOptions(); i++) {
            menuOptions.add(getMenuOptions().get(i).getText());
        }
        return menuOptions;
    }

    public int getNumberOfMenuOptions(){
        return getMenuOptions().size();
    }





}
