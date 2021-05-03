package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LogoutPage {
    private WebDriver driver;
    private By navDropdown = By.className("dropdown-toggle");
    private By logoutButton = By.className("fa-sign-out");
    
    public LogoutPage(WebDriver driver) {
        this.driver = driver;
    }
    
    public void clickNavDropdown() {
        driver.findElement(navDropdown).click();
    }
    
    public void clickLogoutButton() {
        driver.findElement(logoutButton).click();
    }
}
