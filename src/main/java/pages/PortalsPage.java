package pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PortalsPage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    private List<WebElement> portalRows;
    private By addNewPortalButtonLocator = By.className("pull-right");
    
    private By portalRowsLocators = By.cssSelector("table#portalsTable .ui-sortable-handle");
    private By iconButtonDeleteLocator = By.cssSelector("table#portalsTable button.btn-default[title=\"Delete\"]"); 
    private By deleteButtonLocator = By.cssSelector("div#portalDeleteDialog button.btn-primary");
    private By alertSuccessMessageLocator = By.className("alert-success");
    
    public PortalsPage(WebDriver driver) {
        this.driver = driver;
    }
    
    
    public void clickOnAddNewPortalButton() {
        driver.findElement(addNewPortalButtonLocator).click();
    }
    
    public List<WebElement> listPortalRows() {
        return driver.findElements(portalRowsLocators);
    }
    
    public void clickOnLastIconDeleteButton() {
       portalRows = listPortalRows();
       portalRows.get(portalRows.size() - 1).findElement(iconButtonDeleteLocator).click();
    }
    
    public String getNextToLastPortalTitle() {
        portalRows = listPortalRows();
        return portalRows.get(portalRows.size() - 2).findElement(iconButtonDeleteLocator).getAttribute("data-portal_title");
    }
    
    public String getLastPortalTitle() {
        portalRows = listPortalRows();
        return portalRows.get(portalRows.size() - 1).findElement(iconButtonDeleteLocator).getAttribute("data-portal_title");
    }
    
    public void clickOnDeleteButton() {
        wait = new WebDriverWait(driver, 1);
        wait.until(ExpectedConditions.elementToBeClickable(deleteButtonLocator)).click();
    }
    
    public String getMessageAlertSuccess() {
        return driver.findElement(alertSuccessMessageLocator).getText();
    }
    
    
}
