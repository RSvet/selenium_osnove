package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class TablePage extends BasicPage {
    public TablePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }
    public List<WebElement> getRows() {
        return driver.findElements(By.cssSelector("tbody tr"));
    }
    public int getVisibleRowNumber() {
        List<WebElement> rows = getRows();
        int counter = 0;
        for (WebElement row : rows) {
            if (!row.findElements(By.tagName("td")).isEmpty()) {
                counter++;
            }
        }
        return counter;
    }

    public void clickOnEditButtonByRowIndex(int rowIndex) {
        driver.findElements(By.className("update")).get(rowIndex).click();
    }

    public void clickOnDeleteButtonByRowIndex(int rowIndex) {
        driver.findElements(By.className("delete")).get(rowIndex).click();
    }

    public String getFirstNameColumnValue(int rowIndex) {
        return driver.findElements(By.xpath("//tbody/tr/td[2]"))
                .get(rowIndex).getText();
    }

    public String getLastNameColumnValue(int rowIndex) {
        return driver.findElements(By.xpath("//tbody/tr/td[3]"))
                .get(rowIndex).getText();
    }
    public String getMiddleNameColumnValue(int rowIndex) {
        return driver.findElements(By.xpath("//tbody/tr/td[4]"))
                .get(rowIndex).getText();
    }
}
