package topic;

import base.AddCommon;
import base.CommonMethod;
import base.LoginPortal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * @author wufeng
 * @date 2024/2/4 9:31
 */
public class Topic extends LoginPortal {

    static WebDriver driver;

    //建选题
    public static void addTopic(boolean isdepartment, boolean hasInterview) throws InterruptedException {
        driver.findElement(By.cssSelector("button.el-button.save.el-button--default")).click();//点击新建选题按钮
        Thread.sleep(500);
        AddCommon.addTopic(isdepartment, hasInterview, driver);//录入选题信息，并提交
        System.out.println("~~~ addTopic()，建选题，执行成功 ~~~");
        Thread.sleep(3000);
    }

    //审核选题
    public static void audit() throws InterruptedException {
        statusFilter("未生效");//筛选未生效状态，
        if (CommonMethod.isJudgingElement(driver, By.xpath("//div[@class='list']/div"))) {//校验是否有数据
            driver.findElement(By.xpath("//div[@class='list']/div/div/label/span")).click();//单选第一条数据的复选框
            driver.findElement(By.xpath("//div[@class='el-col el-col-21']/span[1]")).click();//点击审核
            Thread.sleep(500);
            driver.findElement(By.cssSelector("button.el-button.el-button--default.el-button--small.el-button--primary")).click();
            System.out.println("~~~ audit()，审核选题，执行成功 ~~~");
        }else System.out.println("没有auto测试数据");
        Thread.sleep(3000);
    }

    //删除选题
    public static void delete() throws InterruptedException {
        statusFilter("未生效");
        if (CommonMethod.isJudgingElement(driver, By.xpath("//div[@class='list']/div"))) {//校验是否有数据
            driver.findElement(By.xpath("//div[@class='list']/div/div/label/span")).click();//单选第一条数据的复选框
            driver.findElement(By.xpath("//div[@class='el-col el-col-21']/span[2]")).click();//点击删除
            Thread.sleep(500);
            driver.findElement(By.cssSelector("button.el-button.el-button--default.el-button--small.el-button--primary")).click();
            System.out.println("~~~ delete()，删除选题，执行成功 ~~~");
        }else System.out.println("没有auto测试数据");
        Thread.sleep(3000);
    }

    //搜索auto选题
    private static void search() throws InterruptedException {
        driver.findElement(By.xpath("//div[@class='keyword-search']/input")).clear();
        driver.findElement(By.xpath("//div[@class='keyword-search']/input")).sendKeys("auto");//录入搜索关键字
        driver.findElement(By.cssSelector("button.el-button.search-btn.not-hover.el-button--default")).click();//点击搜索
        Thread.sleep(1000);
    }

    //带状态搜索
    private static void statusFilter(String status) throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(500);
        List<WebElement> statusList = driver.findElements(By.xpath("//header[@class='el-header search operate-area']/div[2]/div[2]/label"));//状态筛选list
        for (int i = 0; i < statusList.size(); i++) {//遍历每个状态筛选
            if (statusList.get(i).getText().contains(status)) {//校验状态
                statusList.get(i).click();//点击状态
                break;
            }
        }
        search();//搜索
    }

    //初始化登录
    static {
        try {
            driver = login();
            driver.get(domain + "/rplan/static/index.html#/Topic");//跳转到宣传主题页面
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
