import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;

public abstract class Init {

    String driverPath = "drivers/";
    public WebDriver driver;
    String ExpUser = "Gerardo Valero";  //Ожидаемое имя пользователя
    @BeforeTest
    public void launchBrowser() {
        System.out.println("launching firefox browser");
        System.setProperty("webdriver.gecko.driver", driverPath+"geckodriver.exe");
        driver = new FirefoxDriver();
    }


    @AfterTest
    public void closeDriver() {
        if(driver!=null) {
            driver.quit();
        }
    }

    // ОЖИДАНИЕ ПОКА ЭЛЕМЕНТ НЕ ИСЧЕЗНЕТ
    public void waiterUntilInvis(String SomeLocatorByXpath, int time){
        WebDriverWait waitForOne = new WebDriverWait(driver, time);
        waitForOne.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(SomeLocatorByXpath)));
    }

    //ОЖИДАНИЕ ЭЛЕМЕНТА
    public void waiter(String SomeLocatorByXpath , int time){
        WebDriverWait waitForOne = new WebDriverWait(driver, time);
        waitForOne.until(ExpectedConditions.presenceOfElementLocated(By.xpath(SomeLocatorByXpath)));
    }
        // ДОЖДАТСЬЯ ПОЛНОЙ ЗАГРУЗКИ СТРАНИЦЫ
    public void waitforpagetofullload()
    {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='bx-footer-bottomline']")));
    }

    protected void type(WebElement webelement, String text) {
        webelement.click();
        webelement.clear();
        webelement.sendKeys(text);
    }

    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void TWlogin() {
        driver.get("https://twitter.com/?lang=en");
        driver.findElement(By.xpath("//input[@name='session[username_or_email]']")).sendKeys("Xenus1993@mail.ru");
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("Aftershock1");
        driver.findElement(By.xpath("//input[@value='Log in']")).click();
        waiter("//a[contains(text(),'Gerardo Valero')]", 5);
        String CurrentUser = driver.findElement(By.xpath("//a[contains(text(),'Gerardo Valero')]")).getText();
        Assert.assertEquals(CurrentUser, ExpUser);
    }
    public void TWLogout() {
        waiter("//a[@id='user-dropdown-toggle']", 3);
        driver.findElement(By.xpath("//a[@id='user-dropdown-toggle']")).click();
        driver.findElement(By.xpath("//button[@class='dropdown-link'][contains(text(),'Log out')]")).click();
    }
    public void TWLogOutCheck() {
        driver.get("https://twitter.com/?lang=en");
        driver.findElement(By.xpath("//a[contains(text(),'Sign Up')]"));
    }


}