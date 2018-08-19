import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TWTest extends Init {
    String ExpUser = "Gerardo Valero";


    @Test
    public void TWLogInOutFunction() {
        TWlogin();
        TWLogout();
    }

    @Test
    public void TWpost() {
        TWlogin();
        driver.findElement(By.xpath("//div[@name='tweet']")).sendKeys("What a lovely day!");
        driver.findElement(By.xpath("//span[@class='button-text tweeting-text']")).click();
        driver.findElement(By.xpath("//p[text()='What a lovely day!']"));
        TWLogout();
    }
    @Test
    public void TWfollow() {
        TWlogin();
        driver.findElement(By.xpath("//a[@href='/LuisGerardoMorw/following']")).click();
        waiter("//img[@class='ProfileCard-avatarImage js-action-profile-avatar']", 5);
        waiter("//button/span[text()='Following']", 5);
        driver.findElement(By.xpath("//button/span[text()='Following']")).click();
        waiter("//button/span[text()='Follow']", 5);
        driver.findElement(By.xpath("//button/span[text()='Follow']")).click();
        TWLogout();
    }
    @Test
    public void TWsearch() {
        TWlogin();
        driver.findElement(By.xpath("//input[@class='search-input']")).sendKeys("Donald J. Trump");
        driver.findElement(By.xpath("//span[@class='search-icon js-search-action']")).click();
        waiter("//div[@class='stream-item-header']/a[@href='/realDonaldTrump']", 5);
        driver.findElement(By.xpath("//div[@class='stream-item-header']/a[@href='/realDonaldTrump']"));
        TWLogout();
    }
    @Test
    public void TWAccountSettingChange() {
        TWlogin();
        driver.get("https://twitter.com/settings/account");
        driver.findElement(By.xpath("//select[@id='user_country']")).click();
        driver.findElement(By.xpath("//option[@value='cu']")).click();
        driver.findElement(By.xpath("//button[@id='settings_save']")).click();
        waiter("//button[@id='confirm_dialog_submit_button']", 5);
        driver.findElement(By.xpath("//button[@id='confirm_dialog_submit_button']")).click();
        driver.findElement(By.xpath("//input[@id='auth_password']")).sendKeys(AccPass);
        driver.findElement(By.xpath("//button[@id='save_password']")).click();
        TWcurrentCountryNormal();
        driver.get("https://twitter.com/?lang=en");
        TWLogout();
    }
    @Test
    public void TWnotificationPreferenceChange() {
        TWlogin();
        driver.get("https://twitter.com/i/notifications");
        driver.findElement(By.xpath("//div[@class='dropdown']")).click();
        driver.findElement(By.xpath("//button[@class='ActivityItem-dismissOption dropdown-link']")).click();
        waiter("//div[@class='message']", 2);
        driver.findElement(By.xpath("//span[contains(text(),'Thanks. Twitter will use this to make your experience better.')]"));
        TWLogout();
    }
}