package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasicPage{
    public CartPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public boolean checkIfAddedItemsExist(){
        return elementExists(By.className("cart_item"));
    }

    public String getNameFromAddedItem(){
        return  driver.findElement(By.className("inventory_item_name")).getText();
    }


}
