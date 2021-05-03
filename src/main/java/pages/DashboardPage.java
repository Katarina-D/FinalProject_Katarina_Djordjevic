package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class DashboardPage {
    
    private WebDriver driver;
    private By navPortalLocator = By.linkText("Portals");
    private By navSourceLocator = By.linkText("Sources");
    
    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }
    
    public void clickOnPortalsNavLink() {
        driver.findElement(navPortalLocator).click();
    }
    
    public void clickOnSourcesNavLink() {
        driver.findElement(navSourceLocator).click();
    }
    
}
