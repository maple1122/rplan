package home;

import base.AddCommon;
import base.CommonMethod;
import base.LoginPortal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * @author wufeng
 * 新建主题
 * @date 2024/1/31 11:15
 */
public class AddTheme extends LoginPortal {

    static WebDriver driver;

    //新建主题
    public static void addTheme() throws InterruptedException {
        driver.findElement(By.cssSelector("div.add-item.theme")).click();//点击新建主题
        Thread.sleep(500);
        AddCommon.addThemeInput(driver);
        System.out.println("~~~ addTheme()，新建主题，执行成功 ~~~");
        Thread.sleep(3000);
    }

    //初始化登录
    static {
        try {
            driver = login();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
