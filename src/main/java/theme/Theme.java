package theme;

import base.AddCommon;
import base.CommonMethod;
import base.LoginPortal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author wufeng
 * 编辑、删除主题
 * @date 2024/2/2 11:45
 */
public class Theme extends LoginPortal {

    static WebDriver driver;

    //新增主题
    public static void addTheme() throws InterruptedException {
        driver.findElement(By.xpath("//button[@class='el-button el-button--default']")).click();//点击新建
        Thread.sleep(500);
        AddCommon.addThemeInput(driver);//录入主题信息并提交
        System.out.println("~~~ addTheme()，新建主题，执行成功 ~~~");
        Thread.sleep(3000);
    }

    //编辑主题
    public static void editTheme() throws InterruptedException {
        search();//搜索auto主题数据
        if (CommonMethod.isJudgingElement(driver, By.xpath("//div[@class='list']/div"))) {//校验是否有测试数据
            driver.findElement(By.xpath("//div[@class='list']/div/div[2]/a")).click();//点击标题
            Thread.sleep(1000);
            driver.findElement(By.cssSelector("button.el-button.save.el-button--default")).click();//点击编辑
            Thread.sleep(500);
            driver.findElement(By.xpath("//form[@class='el-form']/div[1]/div/div/input")).clear();//清空标题
            driver.findElement(By.xpath("//form[@class='el-form']/div[1]/div/div/input")).sendKeys("autoTest主题-编辑-" + System.currentTimeMillis());//录入标题信息
            driver.findElement(By.xpath("//form[@class='el-form']/div[2]/div/div/textarea")).sendKeys("-update(" + System.currentTimeMillis() + ")");//录入内容
            driver.findElement(By.cssSelector("button.el-button.save.el-button--default")).click();//点击保存
            Thread.sleep(1000);
            driver.findElement(By.cssSelector("i.icon.el-icon-circle-close")).click();//点击关闭
            System.out.println("~~~ editTheme()，编辑主题，执行成功 ~~~");
        } else System.out.println("没有auto测试数据");
        Thread.sleep(3000);
    }

    //删除主题
    public static void delTheme() throws InterruptedException {
        search();//搜索auto主题数据
        if (CommonMethod.isJudgingElement(driver,By.xpath("//div[@class='list']/div"))){//校验是否有测试数据
            driver.findElement(By.xpath("//div[@class='list']/div/div/label/span")).click();//点击选中
            driver.findElement(By.xpath("//span[@class='el-button el-button--default']")).click();//点击删除
            Thread.sleep(500);
            driver.findElement(By.cssSelector("button.el-button.el-button--default.el-button--small.el-button--primary")).click();//确定删除
            System.out.println("~~~ delTheme()，删除主题，执行成功 ~~~");
        }else System.out.println("没有auto测试数据");
        Thread.sleep(3000);
    }

    //搜索测试主题
    private static void search() throws InterruptedException {
        driver.navigate().refresh();//浏览器刷新
        driver.findElement(By.id("inputTxt")).sendKeys("auto");//录入auto数据
        driver.findElement(By.id("confirmBtn")).click();//点击搜索
        Thread.sleep(1000);
    }

    //初始化登录
    static {
        try {
            driver = login();
            driver.get(domain + "/rplan/static/index.html#/theme");//跳转到宣传主题页面
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
