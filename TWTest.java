import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TWTest extends Init {
    String ExpUser = "Gerardo Valero";
    String username = "Xenus1993@mail.ru" ;
    String userPW = "Aftershock1";

    @Test
    //Проверка возможности логина и логаута
    public void TWLogInOutFunction() {
        TWlogin(username,userPW);
        TWLogout();
    }

    @Test
    //Проверка возможности поста твитов, проверка их отоброжнния (p.s. запускать с осторожностью, могут забанить)
    public void TWpost() {
        TWlogin(username,userPW);
        driver.findElement(By.xpath("//div[@name='tweet']")).sendKeys("What a lovely day!");
        driver.findElement(By.xpath("//span[@class='button-text tweeting-text']")).click();
        driver.findElement(By.xpath("//p[text()='What a lovely day!']"));
        TWLogout();
    }
    @Test
    //Возможность фолловить канал (в данном случае первый попавшийся в списке) и убирать фоллоу
    public void TWfollow() {
        TWlogin(username,userPW);
        driver.findElement(By.xpath("//a[@href='/LuisGerardoMorw/following']")).click();
        waiter("//img[@class='ProfileCard-avatarImage js-action-profile-avatar']", 5);
        waiter("//button/span[text()='Following']", 5);
        driver.findElement(By.xpath("//button/span[text()='Following']")).click();
        waiter("//button/span[text()='Follow']", 5);
        driver.findElement(By.xpath("//button/span[text()='Follow']")).click();
        TWLogout();
    }
    @Test
    //Возможность получить ожидаемый результат по поиску
    public void TWsearch() {
        TWlogin(username,userPW);
        driver.findElement(By.xpath("//input[@class='search-input']")).sendKeys("Donald J. Trump");
        driver.findElement(By.xpath("//span[@class='search-icon js-search-action']")).click();
        waiter("//div[@class='stream-item-header']/a[@href='/realDonaldTrump']", 5);
        driver.findElement(By.xpath("//div[@class='stream-item-header']/a[@href='/realDonaldTrump']"));
        TWLogout();
    }
    @Test
    // Возможность изменить настройки аккаунта с дополнительным подтверждением безопасности
    public void TWAccountSettingChange() {
        TWlogin(username,userPW);
        driver.get("https://twitter.com/settings/account");
        driver.findElement(By.xpath("//select[@id='user_country']")).click();
        driver.findElement(By.xpath("//option[@value='cu']")).click();
        driver.findElement(By.xpath("//button[@id='settings_save']")).click();
        waiter("//button[@id='confirm_dialog_submit_button']", 5);
        driver.findElement(By.xpath("//button[@id='confirm_dialog_submit_button']")).click();
        driver.findElement(By.xpath("//input[@id='auth_password']")).sendKeys(userPW);
        driver.findElement(By.xpath("//button[@id='save_password']")).click();
        TWcurrentCountryNormal();
        driver.get("https://twitter.com/?lang=en");
        TWLogout();
    }
    @Test
    // Возможность отказа от специфических новостей (первый в списке, твиттер будет сам постоянно предлагать новые)
    public void TWnotificationPreferenceChange() {
        TWlogin(username,userPW);
        driver.get("https://twitter.com/i/notifications");
        driver.findElement(By.xpath("//div[@class='dropdown']")).click();
        driver.findElement(By.xpath("//button[@class='ActivityItem-dismissOption dropdown-link']")).click();
        waiter("//div[@class='message']", 2);
        driver.findElement(By.xpath("//span[contains(text(),'Thanks. Twitter will use this to make your experience better.')]"));
        TWLogout();
    }
    @Test
    // Изменение трендов и возврат их к изначальному значению
    public void TWtrendsCheck() {
        TWlogin(username,userPW);
        waiter("//span[text()='Change']",10);
        driver.findElement(By.xpath("//span[text()='Change']")).click();
        waiter("//button[@class='btn-link js-show-dropdown-select']", 5);
        driver.findElement(By.xpath("//button[@class='btn-link js-show-dropdown-select']")).click();
        waiter("//select[@name='regions']", 5);
        driver.findElement(By.xpath("//select[@name='regions']")).click();
        waiter("//select[@class='t1-select']/option[@value = '23424740']", 5);
        driver.findElement(By.xpath("//select[@class='t1-select']/option[@value = '23424740']")).click();
        TWcurrentLocatioDefault();
        TWLogout();
    }
}