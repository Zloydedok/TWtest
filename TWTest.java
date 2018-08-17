import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TWTest extends Init {
    String ExpUser = "Gerardo Valero";  //Ожидаемое имя пользователя//div[@class='ProfileCard js-actionable-user']/a

    @Test
    public void TWLogInOutFunction() {
        TWlogin();
        TWLogout();
        TWLogOutCheck();
    }

    @Test
    public void TWpost() {
        TWlogin();
        driver.findElement(By.xpath("//div[@name='tweet']")).sendKeys("What a lovely day!");
        driver.findElement(By.xpath("//span[@class='button-text tweeting-text']")).click();
        driver.findElement(By.xpath("//p[text()='What a lovely day!']"));
        TWLogout();
        TWLogOutCheck();
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
        TWLogOutCheck();
    }
}