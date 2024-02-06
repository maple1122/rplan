package home;

import base.AddCommon;
import base.CommonMethod;
import base.LoginPortal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

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
//        List<WebElement> elements = driver.findElements(By.xpath("//main/form/div"));
//        boolean addInterView = false;
//        for (int i = 0; i < elements.size(); i++) {
//            if (CommonMethod.isJudgingElement(elements.get(i), By.xpath("./label"))) {
//                if (elements.get(i).findElement(By.xpath("./label")).getText().contains("标题")) {
//                    elements.get(i).findElement(By.xpath("./div/div/input")).sendKeys("autoTest选题-" + System.currentTimeMillis());
//                }
//                if (elements.get(i).findElement(By.xpath("./label")).getText().contains("描述")) {
//                    elements.get(i).findElement(By.xpath("./div/div/textarea")).sendKeys("autoTest选题描述-" + System.currentTimeMillis());
//                }
//                if (elements.get(i).findElement(By.xpath("./label")).getText().contains("起止时间")) {
//                    elements.get(i).findElement(By.xpath("./div/div/input[2]")).click();
//                    Thread.sleep(200);
//                    driver.findElement(By.cssSelector("td.available.today.in-range.start-date.end-date")).click();
//                    driver.findElement(By.xpath("//div[@class='el-picker-panel__body']/div[2]/table/tbody/tr[last()-1]/td[1]")).click();
//                }
//                if (!isDepartment)
//                    if (elements.get(i).findElement(By.xpath("./label")).getText().contains("选题级别"))
//                        elements.get(i).findElement(By.xpath("./div/div/label[2]/span/span")).click();
//                if (hasInterView) {
//                    if (elements.get(i).findElement(By.xpath("./label")).getText().contains("采访安排")) {
//                        elements.get(i).findElement(By.xpath("./div/a")).click();
//                        Thread.sleep(500);
//                        addInterView = addInterView();
//                        if (!addInterView) System.out.println("采访安排未添加成功");
//                    }
//                }
//            }
//            Thread.sleep(500);
//        }
//        driver.findElement(By.cssSelector("button.el-button.save.el-button--default")).click();//点击保存
//        Thread.sleep(3000);
//        driver.findElement(By.cssSelector("i.icon.el-icon-circle-close")).click();//点击关闭
        System.out.println("~~~ addTopic()，新建选题，执行成功 ~~~");
        Thread.sleep(3000);
    }

//    //添加采访任务
//    private static boolean addInterView() throws InterruptedException {
//        boolean hasReporter = false;
//        List<WebElement> list = driver.findElements(By.xpath("//form[@class='el-form']/div"));
//        for (int i = 0; i < list.size(); i++) {
//            if (CommonMethod.isJudgingElement(list.get(i), By.xpath("./label"))) {
//                if (list.get(i).findElement(By.xpath("./label")).getText().contains("采访时间")) {
//                    list.get(i).findElement(By.xpath("./div/div/div")).click();
//                    Thread.sleep(500);
//                    driver.findElement(By.cssSelector("button.el-picker-panel__icon-btn.el-date-picker__next-btn.el-icon-arrow-right")).click();//点击下个月
//                    Thread.sleep(500);
//                    driver.findElement(By.xpath("//div[@class='el-picker-panel__content']/table/tbody/tr[3]/td/div")).click();//点击某个日期
//                    driver.findElement(By.xpath("//div[@class='el-date-picker__time-header']/span[2]/div/input")).sendKeys("08:00:00");//设置时间
//                    driver.findElement(By.cssSelector("button.el-time-panel__btn.confirm")).click();//设置时间的确定
//                    Thread.sleep(200);
//                    driver.findElement(By.cssSelector("button.el-button.el-picker-panel__link-btn.el-button--default.el-button--mini.is-plain")).click();//点击确定关闭日期控件
//                }
//                if (list.get(i).findElement(By.xpath("./label")).getText().contains("采访记者")) {
//                    list.get(i).findElement(By.xpath("./div/div/div")).click();
//                    Thread.sleep(500);
//                    list.get(i).findElement(By.xpath("./div/div/div/input")).sendKeys("吴枫");
//                    Thread.sleep(300);
//                    List<WebElement> listReporter = driver.findElements(By.cssSelector("div.el-select-dropdown.el-popper.is-multiple"));
//                    for (int r = 0; r < listReporter.size(); r++) {
//                        if (!listReporter.get(r).getAttribute("style").contains("display")) {
//                            if (CommonMethod.isJudgingElement(listReporter.get(r), By.xpath("./div/div/ul/ul/li[2]/ul/li"))) {
//                                listReporter.get(r).findElement(By.xpath("./div/div/ul/ul/li[2]/ul/li")).click();
//                                hasReporter = true;
//                                break;
//                            }
//                        }
//                        Thread.sleep(300);
//                    }
//                    list.get(i).findElement(By.xpath("./label")).click();//失去焦点关闭下拉选项
//                }
//                if (list.get(i).findElement(By.xpath("./label")).getText().contains("出行方式")) {
//                    list.get(i).findElement(By.xpath("./div/div/div")).click();//点击编辑框
//                    Thread.sleep(500);
//                    driver.findElement(By.xpath("//div[@class='el-select-dropdown el-popper']/div/div/ul/li[1]")).click();//点击第一个选项-派车
//                }
//                if (list.get(i).findElement(By.xpath("./label")).getText().contains("采访说明")) {
//                    list.get(i).findElement(By.xpath("./div/div/textarea")).sendKeys("autoTest这是采访说明-" + System.currentTimeMillis());
//                }
//            }
//            Thread.sleep(500);
//        }
//        System.out.println("22222222222");
//        if (hasReporter) {
//            driver.findElement(By.cssSelector("button.el-button.el-button--primary.el-button--small")).click();//点击采访安排
//            Thread.sleep(500);
//        }
//        List<WebElement> dialogs = driver.findElements(By.cssSelector("div.el-dialog__wrapper.clearfix"));//确定按钮div的list
//        for (int d = 0; d < dialogs.size(); d++) {
//            if (!dialogs.get(d).getAttribute("style").contains("display")) {//校验是否是有效div
//                if (hasReporter) {
//                    dialogs.get(d).findElement(By.xpath("./div/div[3]/div/button[1]")).click();//点击确定
//                } else dialogs.get(d).findElement(By.xpath("./div/div[3]/div/button[3]")).click();//点击取消
//                break;
//            }
//            Thread.sleep(500);
//        }
//        return hasReporter;
//    }

    //初始化登录
    static {
        try {
            driver = login();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
