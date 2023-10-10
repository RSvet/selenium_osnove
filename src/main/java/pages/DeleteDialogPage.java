package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DeleteDialogPage extends BasicPage {
    public DeleteDialogPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }
    public void waitForDialogToBeVisible() {
        wait
                .withMessage("Delete dialog should be visible.")
                .until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("delete")));

    }
    public void waitForDialogToBeInvisible() {
        wait
                .withMessage("Delete dialog should be invisible.")
                .until(
                        ExpectedConditions.invisibilityOfElementLocated(By.id("delete")));

    }

    public WebElement getDialogBody() {
        return driver.findElement(By.cssSelector("#delete .modal-body"));
    }

    public String getDialogBodyMessage() {
        return getDialogBody().getText();
    }

    public WebElement getDeleteButton() {
        return driver.findElement(By.id("del"));
    }
    public void clickOnDeleteButton() {
//        sacekaj ovde da bude klikljivo
        getDeleteButton().click();
    }
}
