package admin;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.InsertSourcePage;
import pages.DashboardPage;
import pages.LoginPage;
import pages.LogoutPage;
import pages.SourcesPage;

public class InsertSourceTest {
    
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static SourcesPage sourcesPage;
    private static InsertSourcePage insertSourcePage;
    
    public InsertSourceTest() {
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
        dashboardPage.clickOnSourcesNavLink();
        sourcesPage = new SourcesPage(driver);
        insertSourcePage = new InsertSourcePage(driver);
    }
    
    @After
    public void tearDown() {
        LogoutPage logoutPage = new LogoutPage(driver);
        logoutPage.clickNavDropdown();
        logoutPage.clickLogoutButton();
    }
    
    @Test
     public void testAddNewSourceWithProcessorVoid() {
       
        sourcesPage.clickOnAddNewSourceButton();
        insertSourcePage.selectFirstOptionFromPortalDropdown();
        String portal = insertSourcePage.getFirstOptionFromDropdown();
        insertSourcePage.selectFirstOptionFromCategoryDropdown();
        String category = insertSourcePage.getFirstOptionFromDropdown();
        String newSourceTitle = portal + " - " + category;
        insertSourcePage.enterTitle(newSourceTitle);
        insertSourcePage.enterUrl("https://www.vesti-online.com/sport/");
        insertSourcePage.selectFirstOptionFromTypeDropdown();
        insertSourcePage.selectVisibleTextFromProcessorDropdown("void");
       
        insertSourcePage.clickOnSaveButton();
        
        String expectedAlertResult = "Source \"" + newSourceTitle + "\" has been successfully saved!";
        String actualAlertResult = sourcesPage.getMessageAlertSuccess();
        assertTrue("Alert message is not correct", expectedAlertResult.equals(actualAlertResult));
       
        String expectedResult = "http://bvtest.school.cubes.rs/admin/sources";
        String actualResult = driver.getCurrentUrl();
        assertTrue("URLs doesn't match", expectedResult.equals(actualResult));
     }
     
     @Test
     public void testAddNewSourceWithProcessorCategory() {
       
       sourcesPage.clickOnAddNewSourceButton();
       insertSourcePage.selectFirstOptionFromPortalDropdown();
       String portalName = insertSourcePage.getFirstOptionFromDropdown();
       insertSourcePage.enterTitle(portalName);
       insertSourcePage.enterUrl("https://www.vesti-online.com/sport/");
       insertSourcePage.selectVisibleTextFromProcessorDropdown("category");
       insertSourcePage.clickOnSaveButton();
        
       String expectedAlertResult = "Source \"" + portalName + "\" has been successfully saved!";
       String actualAlertResult = sourcesPage.getMessageAlertSuccess();
       assertTrue("Alert message is not correct", expectedAlertResult.equals(actualAlertResult));
       
       String expectedResult = "http://bvtest.school.cubes.rs/admin/sources";
       String actualResult = driver.getCurrentUrl();
       assertTrue("URLs doesn't match", expectedResult.equals(actualResult));
       
     }
     
      @Test
     public void testCancelAddingNewSource() {
       
       sourcesPage.clickOnAddNewSourceButton();
       insertSourcePage.selectFirstOptionFromPortalDropdown();
       String portal = insertSourcePage.getFirstOptionFromDropdown();
       insertSourcePage.selectFirstOptionFromCategoryDropdown();
       String category = insertSourcePage.getFirstOptionFromDropdown();
       String newSourceTitle = portal + " - " + category;
       insertSourcePage.enterTitle(newSourceTitle);
       insertSourcePage.enterUrl("https://www.vesti-online.com/sport/");
       insertSourcePage.selectFirstOptionFromTypeDropdown();
       insertSourcePage.clickOnBackButton();
       
        String expectedURL = "http://bvtest.school.cubes.rs/admin/sources";
        String actualResURL = driver.getCurrentUrl();
        assertTrue("URLs doesn't match", expectedURL.equals(actualResURL));
     }
     
     @Test
     public void testUrlFormatInvalid() {
       
       sourcesPage.clickOnAddNewSourceButton();
       insertSourcePage.selectFirstOptionFromPortalDropdown();
       String portal = insertSourcePage.getFirstOptionFromDropdown();
       insertSourcePage.selectFirstOptionFromCategoryDropdown();
       String category = insertSourcePage.getFirstOptionFromDropdown();
       String newSourceTitle = portal + " - " + category;
       insertSourcePage.enterTitle(newSourceTitle);
       insertSourcePage.enterUrl("vesti-online.com");
       insertSourcePage.selectFirstOptionFromTypeDropdown();
       insertSourcePage.selectVisibleTextFromProcessorDropdown("void");
       
       insertSourcePage.clickOnSaveButton();
        
        String expectedAlertResult = "The url format is invalid.";
        String actualAlertResult = insertSourcePage.getMessageAlertDanger();
        assertTrue("Alert message is not correct", expectedAlertResult.equals(actualAlertResult));
       
        String expectedResult = "http://bvtest.school.cubes.rs/admin/sources/insert";
        String actualResult = driver.getCurrentUrl();
        assertTrue("URLs doesn't match", expectedResult.equals(actualResult));
     }
     
     @Test
     public void testTitleLeast2Characters() {
       
       sourcesPage.clickOnAddNewSourceButton();
       insertSourcePage.selectFirstOptionFromPortalDropdown();
       insertSourcePage.selectFirstOptionFromCategoryDropdown();
       insertSourcePage.enterTitle("V");
       insertSourcePage.enterUrl("https://www.vesti-online.com/sport/");
       insertSourcePage.selectFirstOptionFromTypeDropdown();
       insertSourcePage.selectVisibleTextFromProcessorDropdown("void");
       
       insertSourcePage.clickOnSaveButton();
        
       String expectedAlertResult = "The title must be at least 2 characters.";
       String actualAlertResult = insertSourcePage.getMessageAlertDanger();
       assertTrue("Alert message is not correct", expectedAlertResult.equals(actualAlertResult));
       
       String expectedResult = "http://bvtest.school.cubes.rs/admin/sources/insert";
       String actualResult = driver.getCurrentUrl();
       assertTrue("URLs doesn't match", expectedResult.equals(actualResult));
     }
     
     
     
     
}
