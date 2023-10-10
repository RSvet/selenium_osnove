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

public class ProductsTests extends BasicTest {

  @Test
    public void test1(){
      driver.navigate().to("https://s.bootsnipp.com/iframe/K5yrx");
  }




}
