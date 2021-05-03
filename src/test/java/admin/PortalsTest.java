package admin;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.DashboardPage;
import pages.LoginPage;
import pages.LogoutPage;
import pages.PortalsPage;


public class PortalsTest {
    
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static PortalsPage portalsPage;
    
    public PortalsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 1);
        driver.manage().window().fullscreen();
    }
    
    @AfterClass
    public static void tearDownClass() {
        driver.quit();
    }
    
    @Before
    public void setUp() {
        driver.get("http://bvtest.school.cubes.rs/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail("qa@cubes.rs");
        loginPage.enterPassword("cubesqa");
        loginPage.clickOnLoginButton();
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.clickOnPortalsNavLink();
        portalsPage = new PortalsPage(driver);
    }
    
    @After
    public void tearDown() {
        LogoutPage logoutPage = new LogoutPage(driver);
        logoutPage.clickNavDropdown();
        logoutPage.clickLogoutButton();
    }
    
      @Test
     public void testDeleteLastPortal() {

        String expectedLastPortalTitle = portalsPage.getNextToLastPortalTitle();
        String lastPortalTitle = portalsPage.getLastPortalTitle();
        portalsPage.clickOnLastIconDeleteButton();
        
        portalsPage.clickOnDeleteButton();
        
        String expectedAlertResult = "Portal \"" + lastPortalTitle + "\" has been successfully deleted!";
        String actualAlertResult = portalsPage.getMessageAlertSuccess();
        assertTrue("Alert message is not correct", expectedAlertResult.equals(actualAlertResult));  
        
        String expectedResult = "http://bvtest.school.cubes.rs/admin/portals";
        String actualResult = driver.getCurrentUrl();
        assertTrue("URLs doesn't match", expectedResult.equals(actualResult));
        
        String actualLastResult = portalsPage.getLastPortalTitle();
        assertTrue("The last result is not match", expectedLastPortalTitle.equals(actualLastResult));
     
     }
     
     
}
