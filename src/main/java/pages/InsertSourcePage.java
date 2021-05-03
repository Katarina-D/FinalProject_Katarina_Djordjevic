package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class InsertSourcePage {
    private WebDriver driver;
    private Select dropdown;
    
    private By portalDropdownLocator = By.id("sourcePortalSelect");
    private By categoryDropdownLocator = By.id("sourceCategorySelect");
    private By titleFieldLocator = By.id("sourceTitleText");
    private By urlFieldLocator = By.id("sourceUrlText");
    private By typeDropdownLocator = By.id("sourceNewsFetcherSelect");
    private By processorDropdownLocator = By.id("sourceNewsProcessorSelect");
    private By saveButtonLocator = By.id("save-source-button");
    private By backButtonLocator = By.id("back-button");
    private By alertDangerMessageLocator = By.className("alert-danger");
    
    public InsertSourcePage(WebDriver driver) {
        this.driver = driver;
    }
    
    
    public void selectFirstOptionFromPortalDropdown() {
       dropdown = new Select(driver.findElement(portalDropdownLocator));
       dropdown.selectByIndex(1);
    }
    
    public String getFirstOptionFromDropdown() {
       WebElement selectOption = dropdown.getFirstSelectedOption();
       return selectOption.getText();
    }
    
    public void selectFirstOptionFromCategoryDropdown() {
       dropdown = new Select(driver.findElement(categoryDropdownLocator));
       dropdown.selectByIndex(1);
    }
    
    public void enterTitle(String email) {
        driver.findElement(titleFieldLocator).sendKeys(email);
    }
    
    public void enterUrl(String url) {
        driver.findElement(urlFieldLocator).sendKeys(url);
    }
    
    public void selectFirstOptionFromTypeDropdown() {
       dropdown = new Select(driver.findElement(typeDropdownLocator));
       dropdown.selectByIndex(0);
    }
    
    public void selectVisibleTextFromProcessorDropdown(String text) {
       dropdown = new Select(driver.findElement(processorDropdownLocator));
       dropdown.selectByVisibleText(text);
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
