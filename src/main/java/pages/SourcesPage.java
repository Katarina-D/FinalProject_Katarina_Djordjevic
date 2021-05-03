package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

    

public class SourcesPage {
    
    private WebDriver driver;
    private By addNewPortalButtonLocator = By.className("pull-right");
    private By alertSuccessMessageLocator = By.className("alert-success");
    
    
    public SourcesPage(WebDriver driver) {
        this.driver = driver;
    }
    
    
    public void clickOnAddNewSourceButton() {
        driver.findElement(addNewPortalButtonLocator).click();
    }
    
    public String getMessageAlertSuccess() {
        return driver.findElement(alertSuccessMessageLocator).getText();
    }
    
}
