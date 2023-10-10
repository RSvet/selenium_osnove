package tests;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import p02_10_2023.Helper;
import pages.TablePage;
import pages.UpdateDialogPage;
import retry.BootsnippRetry;

import java.io.IOException;
import java.time.Duration;


public class BootsnippTests extends BasicTest{

    @Test(priority = 1, retryAnalyzer = BootsnippRetry.class)
    public void verifyPageIsOpened() {
        Assert.assertEquals(driver.getTitle(),
                "Table with Edit and Update Data - Bootsnipp.com",
                "Error message");
    }

    @Test(priority=2, retryAnalyzer = BootsnippRetry.class)
    public void editRow(){

        Assert.assertEquals(tablePage.getVisibleRowNumber(), 2, "Error message");

        String firstName = "Svetlana";
        String lastName = "Radisic";
        String middleName = "Milenko";

        tablePage.clickOnEditButtonByRowIndex(0);

        updateDialogPage.waitForDialogToBeVisible();

        updateDialogPage.clearAndTypeFirstName(firstName);
        updateDialogPage.clearAndTypeLastName(lastName);
        updateDialogPage.clearAndTypeMiddleName(middleName);

        updateDialogPage.clickOnUpdateButton();

        updateDialogPage.waitForDialogToBeInvisible();

        Assert.assertEquals(tablePage.getFirstNameColumnValue(0), firstName,
                "First name does not match");

        Assert.assertEquals(tablePage.getLastNameColumnValue(0), lastName,
                "Last name does not match");

        Assert.assertEquals(tablePage.getMiddleNameColumnValue(0), middleName,
                "Middle name does not match");

    }


    @Test(priority = 3, retryAnalyzer = BootsnippRetry.class)
    public void deleteRow() {

        int rowNumberBeforeDelete = tablePage.getVisibleRowNumber();

        tablePage.clickOnDeleteButtonByRowIndex(0);
        deleteDialogPage.waitForDialogToBeVisible();
        Assert.assertEquals(
                deleteDialogPage.getDialogBodyMessage(),
                "Are you sure you want to delete this data?",
                "Delete dialog message is not valid.");

        deleteDialogPage.clickOnDeleteButton();
        deleteDialogPage.waitForDialogToBeInvisible();

        int rowNumberAfterDelete = tablePage.getVisibleRowNumber();

        Assert.assertEquals(
                rowNumberAfterDelete,
                rowNumberBeforeDelete - 1,
                "Row should be deleted.");

    }




}
