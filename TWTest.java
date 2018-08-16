import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TWTest extends Init {
    String ExpUser = "Gerardo Valero";  //Ожидаемое имя пользователя
    TWCommonTests dologinIntoTwitter = new TWCommonTests();
    @Test
    public void TWologin() {
        TWlogin();
    }

    @Test
    public void TWlogout() {
    }


}