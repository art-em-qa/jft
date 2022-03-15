package ru.stqa.pft.addressbook;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class UserCreationTest {
  private WebDriver wd;


  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    System.setProperty("webdriver.chrome.driver", "/usr/local/bin");
    wd = new ChromeDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook");
    login("admin", "secret");
  }

  private void login(String username, String password) {
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys(username);
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys(password);
    wd.findElement(By.xpath("//input[@value='Login']")).click();
  }

  @Test
  public void testUserCreation() throws Exception {
    initCreateNewUser();
    fillFormNewUser(new UserData("Antonio", "Fagundes", "Portugal, St.Barbara", "+0123456789", "a.fagundes@stbarbara.com"));
    submitCreateNewUser();
    returnHomePage();
  }

  private void returnHomePage() {
    wd.findElement(By.linkText("home page")).click();
  }

  private void submitCreateNewUser() {
    wd.findElement(By.name("submit")).click();
  }

  private void fillFormNewUser(UserData userData) {
    wd.findElement(By.name("firstname")).clear();
    wd.findElement(By.name("firstname")).sendKeys(userData.getFirstname());
    wd.findElement(By.name("lastname")).clear();
    wd.findElement(By.name("lastname")).sendKeys(userData.getLastname());
    wd.findElement(By.name("address")).clear();
    wd.findElement(By.name("address")).sendKeys(userData.getAddress());
    wd.findElement(By.name("work")).clear();
    wd.findElement(By.name("work")).sendKeys(userData.getWorkphone());
    wd.findElement(By.name("email")).clear();
    wd.findElement(By.name("email")).sendKeys(userData.getEmail());
  }

  private void initCreateNewUser() {
    wd.findElement(By.linkText("add new")).click();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
    logout();
    wd.quit();
  }

  private void logout() {
    wd.findElement(By.linkText("Logout")).click();
  }

  private boolean isElementPresent(By by) {
    try {
      wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }
}
