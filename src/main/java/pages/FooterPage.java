package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;

public class FooterPage extends BasicPage{
    public FooterPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public WebElement getFooter(){
        return driver.findElement(By.className("footer"));
    }

    public WebElement getTwitter(){
        return driver.findElement(By.cssSelector(".social_twitter>a"));
    }

    public WebElement getFacebook(){
        return driver.findElement(By.cssSelector(".social_facebook>a"));
    }


    public void scrollToFooter(){
        new Actions(driver).scrollToElement(getFooter()).perform();
    }

    public void waitForTwitterIconToBeVisible(){
        wait
                .withMessage("Twitter icon is not visible in the cart page")
                .until(ExpectedConditions.visibilityOf(getTwitter()));
    }

    public void waitForFacebookIconToBeVisible(){
        wait
                .withMessage("Facebook icon is not visible in the cart page")
                .until(ExpectedConditions.visibilityOf(getFacebook()));
    }

}
