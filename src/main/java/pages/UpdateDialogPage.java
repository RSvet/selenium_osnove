package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UpdateDialogPage extends BasicPage {
    public UpdateDialogPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }
    public void waitForDialogToBeVisible() {
        wait
                .withMessage("Edit dialog should be visible.")
                .until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("edit")));

    }
    public void waitForDialogToBeInvisible() {
        wait
                .withMessage("Edit dialog should be invisible.")
                .until(
                        ExpectedConditions.invisibilityOfElementLocated(By.id("edit")));

    }
    public WebElement getFirstNameInput() {
        return driver.findElement(By.id("fn"));
    }
    public WebElement getLastNameInput() {
        return driver.findElement(By.id("ln"));
    }
    public WebElement getMiddleNameInput() {
        return driver.findElement(By.id("mn"));
    }

    public WebElement getUpdateButton() {
        return driver.findElement(By.id("up"));
    }

    public void clearAndTypeFirstName(String firstName) {
        getFirstNameInput().clear();
        getFirstNameInput().sendKeys(firstName);
    }
    public void clearAndTypeLastName(String lastName) {
        getLastNameInput().clear();
        getLastNameInput().sendKeys(lastName);
    }

    public void clearAndTypeMiddleName(String middleName) {
        getMiddleNameInput().clear();
        getMiddleNameInput().sendKeys(middleName);
    }

    public void clickOnUpdateButton() {
        getUpdateButton().click();
    }
}
