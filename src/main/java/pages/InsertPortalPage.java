package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;


public class InsertPortalPage {
    private WebDriver driver;
    
    private By titleFieldLocator = By.id("title");
    private By urlFieldLocator = By.id("url");
    private By regionDropdownLocator = By.name("region_id");
    private By saveButtonLocator = By.id("save-portal-button");
    private By backButtonLocator = By.id("back-button");
    private By alertDangerMessageLocator = By.className("alert-danger");
    
    
    public InsertPortalPage(WebDriver driver) {
        this.driver = driver;
    }
    
    
    public void enterTitle(String email) {
        driver.findElement(titleFieldLocator).sendKeys(email);
    }
    
    public void enterUrl(String url) {
        driver.findElement(urlFieldLocator).sendKeys(url);
    }
    
    public void selectFirstOptionRegionDropdown() {
        Select regionDropdown = new Select(driver.findElement(regionDropdownLocator));
        regionDropdown.selectByIndex(0);
    }
    
    public void clickOnSaveButton() {
        driver.findElement(saveButtonLocator).click();
    }
    
    public void clickOnBackButton() {
        driver.findElement(backButtonLocator).click();
    }
    
    public String getMessageAlertDanger() {
        return driver.findElement(alertDangerMessageLocator).getText();
    }
    
}
