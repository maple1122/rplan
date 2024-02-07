package home;

import base.AddCommon;
import base.LoginPortal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author wufeng
 * 新建选题
 * @date 2024/1/31 11:15
 */
public class AddTopic extends LoginPortal {

    static WebDriver driver;

    //新建选题
    public static void addTopic(boolean isDepartment) throws InterruptedException {
        addTopic(isDepartment, false);
    }

    //新建选题
    public static void addTopic(boolean isDepartment, boolean hasInterView) throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("div.add-item.topic")).click();//点击新建选题
        Thread.sleep(500);
        AddCommon.addTopic(isDepartment,hasInterView,driver);
        System.out.println("~~~ addTopic()，新建选题，执行成功 ~~~");
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
