package home;

import base.AddCommon;
import base.LoginPortal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author wufeng
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
//        List<WebElement> elements = driver.findElements(By.xpath("//form/div"));//内容列表
//        for (int i = 0; i < elements.size(); i++) {
//            if (CommonMethod.isJudgingElement(elements.get(i), By.xpath("./label"))) {
//                //录入标题
//                if (elements.get(i).findElement(By.xpath("./label")).getText().contains("标题")) {
//                    elements.get(i).findElement(By.xpath("./div/div/input")).sendKeys("autoTest线索-" + System.currentTimeMillis());
//                }
//                //选择线索来源
//                if (elements.get(i).findElement(By.xpath("./label")).getText().contains("线索来源")) {
//                    elements.get(i).findElement(By.xpath("./div/div/div/div")).click();
//                    List<WebElement> list = driver.findElements(By.cssSelector("div.el-select-dropdown.el-popper"));
//                    for (int li = 0; li < list.size(); li++) {
//                        if (!list.get(li).getAttribute("style").contains("display")) {
//                            list.get(li).findElement(By.xpath("./div/div/ul/li[last()]")).click();
//                            break;
//                        }
//                    }
//                }
//            }
//            Thread.sleep(500);
//        }
//        driver.findElement(By.tagName("textarea")).sendKeys("autoTest这是线索详情的内容" + System.currentTimeMillis());//录入线索详情
//        driver.findElement(By.cssSelector("button.el-button.save.el-button--default")).click();//点击保存
//        Thread.sleep(3000);
//        driver.findElement(By.cssSelector("i.icon.el-icon-circle-close")).click();//点击关闭
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
