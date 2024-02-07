package home;

import base.AddCommon;
import base.LoginPortal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author wufeng
 * 新建线索
 * @date 2024/1/31 11:15
 */
public class AddClue extends LoginPortal {

    static WebDriver driver;

    //报线索
    public static void addClue() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("div.add-item.clue")).click();//点击报线索
        Thread.sleep(500);
        AddCommon.addClue(driver);
        System.out.println("~~~ addClue()，报线索，执行成功 ~~~");
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
