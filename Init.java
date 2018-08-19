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
    String AccPass = "Aftershock1";
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
        driver.get("https://twitter.com/logout");
        waiter("//button[@class='EdgeButton EdgeButton--primary js-submit'][text()='Log out']",5);
        driver.findElement(By.xpath("//button[@class='EdgeButton EdgeButton--primary js-submit'][text()='Log out']")).click();
        waiter("//button[@class='EdgeButton EdgeButton--primary js-submit'][text()='Log out']",5);
        driver.findElement(By.xpath("//button[@class='EdgeButton EdgeButton--primary js-submit'][text()='Log out']")).click();
        waiter("//a[contains(text(),'Sign Up')]",5);
        driver.findElement(By.xpath("//a[contains(text(),'Sign Up')]"));
    }
    public void TWcurrentCountryNormal() {
        driver.get("https://twitter.com/settings/account");
        driver.findElement(By.xpath("//select[@id='user_country']")).click();
        driver.findElement(By.xpath("//option[@value='cg']")).click();
        driver.findElement(By.xpath("//button[@id='settings_save']")).click();
        waiter("//button[@id='confirm_dialog_submit_button']", 5);
        driver.findElement(By.xpath("//button[@id='confirm_dialog_submit_button']")).click();
        driver.findElement(By.xpath("//input[@id='auth_password']")).sendKeys(AccPass);
        driver.findElement(By.xpath("//button[@id='save_password']")).click();
    }
    public void TWcurrentLocatioDefault() {
        driver.get("https://twitter.com/?lang=en");
        waiter("//span[text()='Change']",10);
        driver.findElement(By.xpath("//span[text()='Change']")).click();
        waiter("//button[@class='btn-link js-show-dropdown-select']", 5);
        driver.findElement(By.xpath("//button[@class='btn-link js-show-dropdown-select']")).click();
        waiter("//select[@name='regions']", 5);
        driver.findElement(By.xpath("//select[@name='regions']")).click();
        waiter("//select[@class='t1-select']/option[@value = '23424787']", 5);
        driver.findElement(By.xpath("//select[@class='t1-select']/option[@value = '23424787']")).click();
    }
}