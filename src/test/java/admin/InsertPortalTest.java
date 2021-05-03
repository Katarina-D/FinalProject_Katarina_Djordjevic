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
import pages.InsertPortalPage;
import pages.LoginPage;
import pages.LogoutPage;
import pages.PortalsPage;


public class InsertPortalTest {
    
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static PortalsPage portalsPage;
    private static InsertPortalPage insertPortalPage;
    
    public InsertPortalTest() {
    }
    
     @BeforeClass
    public static void setUpClass() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 5);
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
        insertPortalPage = new InsertPortalPage(driver);
    }
    
    @After
    public void tearDown() {
        LogoutPage logoutPage = new LogoutPage(driver);
        logoutPage.clickNavDropdown();
        logoutPage.clickLogoutButton();
    }
    
     @Test
     public void testAddNewPortal() {

       portalsPage.clickOnAddNewPortalButton();
       String newPortalTitle = "Vesti Online";
       insertPortalPage.enterTitle(newPortalTitle);
       insertPortalPage.enterUrl("https://www.vesti-online.com/sport/");
       insertPortalPage.selectFirstOptionRegionDropdown();
       insertPortalPage.clickOnSaveButton();
       
       String expectedAlertResult = "Portal \"" + newPortalTitle + "\" has been successfully saved!";
       String actualAlertResult = portalsPage.getMessageAlertSuccess();
       assertTrue("Alert message is not correct", expectedAlertResult.equals(actualAlertResult));
       
       String expectedResult = "http://bvtest.school.cubes.rs/admin/portals";
       String actualResult = driver.getCurrentUrl();
       assertTrue("URLs doesn't match", expectedResult.equals(actualResult));
     }
     
     @Test
     public void testCancelAddingNewPortal() {
       
       portalsPage.clickOnAddNewPortalButton();
       insertPortalPage.enterTitle("Vesti Online");
       insertPortalPage.enterUrl("http://www.vesti-online.com/rss/Sport");
       insertPortalPage.clickOnBackButton();
       
       String expectedURL = "http://bvtest.school.cubes.rs/admin/portals";
       String actualResURL = driver.getCurrentUrl();
       assertTrue("URLs doesn't match", expectedURL.equals(actualResURL));
    }
     
     @Test
     public void testUrlLeast10Characters() {
       
       portalsPage.clickOnAddNewPortalButton();
       insertPortalPage.enterTitle("Vesti Online");
       insertPortalPage.enterUrl("sport");
       insertPortalPage.selectFirstOptionRegionDropdown();
       insertPortalPage.clickOnSaveButton();
       
       String expectedAlertResult = "The url must be at least 10 characters.";
       String actualAlertResult = insertPortalPage.getMessageAlertDanger();
       assertTrue("Alert message is not correct", expectedAlertResult.equals(actualAlertResult));
       
       String expectedResult = "http://bvtest.school.cubes.rs/admin/portals/insert";
       String actualResult = driver.getCurrentUrl();
       assertTrue("URLs doesn't match", expectedResult.equals(actualResult));
     }
     
      @Test
     public void testTitleLeast2Characters() {

       portalsPage.clickOnAddNewPortalButton();
       insertPortalPage.enterTitle("V");
       insertPortalPage.enterUrl("ttp://www.vesti-online.com/rss/Sport");
       insertPortalPage.selectFirstOptionRegionDropdown();
       insertPortalPage.clickOnSaveButton();
       
       String expectedAlertResult = "The title must be at least 2 characters.";
       String actualAlertResult = insertPortalPage.getMessageAlertDanger();
       assertTrue("Alert message is not correct", expectedAlertResult.equals(actualAlertResult));
       
       String expectedResult = "http://bvtest.school.cubes.rs/admin/portals/insert";
       String actualResult = driver.getCurrentUrl();
       assertTrue("URLs doesn't match", expectedResult.equals(actualResult));
     }
}
