package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class InventoryPage extends BasicPage{


    public InventoryPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public boolean locateSauceLabsItem(){
        return elementExists(By.xpath("//div[text()='Sauce Labs Backpack']"));
    }

    public WebElement getAddItemToCartButton(){
        return driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
    }

    public void clickToAddItemToCart(){
        getAddItemToCartButton().click();
    }

    public boolean checkIfThereIsRemoveButton(){
        return elementExists(By.id("remove-sauce-labs-backpack"));
    }

    public boolean checkIfCartBadgeExists(){
        return elementExists(By.className("shopping_cart_badge"));
    }

    public WebElement getCartBadge(){
        if(checkIfCartBadgeExists()){
            return driver.findElement(By.className("shopping_cart_badge"));
        }
        else return null;
    }
    public int getNumberOfItemsFromCartBadge(){
        if(getCartBadge()!=null){
          return  Integer.parseInt(getCartBadge().getText());
        }
        else return 0;
    }

}
