package clue;

import base.AddCommon;
import base.CommonMethod;
import base.LoginPortal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

/**
 * @author wufeng
 * 线索-添加线索、添加选题、加入研判、采用、编辑、删除
 * @date 2024/2/4 9:31
 */
public class Clue extends LoginPortal {

    static WebDriver driver;

    //新建线索
    public static void addClue() throws InterruptedException {
        getButton("线索");//点击新建线索
        Thread.sleep(500);
        AddCommon.addClue(driver);//添加线索
        System.out.println("~~~ addClue()，新建线索，执行成功 ~~~");
        Thread.sleep(3000);
    }

    //添加选题
    public static void addTopicByClue() throws InterruptedException {
        search();//搜索测试线索
        if (CommonMethod.isJudgingElement(driver, By.xpath("//table[@class='el-table__body']/tbody/tr"))) {//校验是否有auto线索数据
            driver.findElement(By.xpath("//table[@class='el-table__body']/tbody/tr[1]/td[1]/div/label/span")).click();//点击复选框
            getButton("选题");//点击加入选题
            Thread.sleep(1000);
            driver.findElement(By.linkText("添加安排")).click();
            Thread.sleep(500);
            AddCommon.addInterView(driver);
            Thread.sleep(1000);
            driver.findElement(By.xpath("//header/button[1]")).click();//点击保存
            Thread.sleep(3000);
            driver.findElement(By.cssSelector("i.icon.el-icon-circle-close")).click();//点击关闭
            Thread.sleep(1000);
            System.out.println("~~~ addTopicByClue()，添加选题，执行成功 ~~~");
        } else System.out.println("没有auto测试数据");
        Thread.sleep(3000);
    }

    //加入研判
    public static void addDetermine() throws InterruptedException {
        search();//搜索测试线索
        if (CommonMethod.isJudgingElement(driver, By.xpath("//table[@class='el-table__body']/tbody/tr"))) {//校验是否有auto线索数据
            driver.findElement(By.xpath("//table[@class='el-table__body']/tbody/tr[1]/td[1]/div/label/span")).click();//点击第一条线索复选框
            getButton("加入研判");//点击加入研判
            Thread.sleep(1000);
            System.out.println("~~~ addDetermine()，加入研判，执行成功 ~~~");
        } else System.out.println("没有auto测试数据");
        Thread.sleep(3000);
    }

    //研判删除线索
    public static void delDetermine() throws InterruptedException {
        String numStr = driver.findElement(By.className("clueNums")).getText();//研判线索数
        int num = Integer.parseInt(numStr);//线索数转为整型
        if (num > 0) {
            driver.findElement(By.className("researchBtn")).click();//点击研判，打开研判图层
            Thread.sleep(1000);
            List<WebElement> titleList = driver.findElements(By.xpath("//ul[@id='titleList']/li"));//标题列表
            for (int i = 0; i < titleList.size(); i++) {
                if (titleList.get(i).getText().contains("auto"))//判断标题是否有auto
                    titleList.get(i).findElement(By.xpath("./span[2]")).click();//点击删除
                Thread.sleep(200);
            }
            driver.findElement(By.className("clue-screen-close")).click();//关闭研判图层
            System.out.println("~~~ delDetermine()，研判删除线索，执行成功 ~~~");
        } else System.out.println("没有可删除的");
        Thread.sleep(3000);
    }

    //采用线索
    public static void use() throws InterruptedException {
        search();//搜索测试线索
        if (CommonMethod.isJudgingElement(driver, By.xpath("//table[@class='el-table__body']/tbody/tr"))) {//校验是否有auto线索数据
            List<WebElement> trs = driver.findElements(By.xpath("//table[@class='el-table__body']/tbody/tr"));
            boolean hasUnused = false;
            for (int i = 0; i < trs.size(); i++) {
                if (!trs.get(i).findElement(By.xpath("./td[last()]/div/span")).getAttribute("class").contains("stateUse")) {//校验是否不是已使用状态的
                    trs.get(i).findElement(By.xpath("./td[1]/div/label/span")).click();//点击第一条符合条件的线索复选框
                    hasUnused = true;
                    break;
                }
            }
            if (hasUnused) {
                getButton("采用");//点击采用
                System.out.println("~~~ use()，线索采用，执行成功 ~~~");
            } else System.out.println("没有未使用状态的测试线索");
            Thread.sleep(1000);
        } else System.out.println("没有auto测试数据");
        Thread.sleep(3000);
    }

    //线索关键词
    public static void addClueKeyword() throws InterruptedException {
        getButton("线索关键词");//点击线索关键词
        if (!searchTestGroup()) {//校验是否没有测试分类
            addGroup();//新建测试分类
            searchTestGroup();//查询点击测试分类
        }
        driver.findElement(By.xpath("//div[@class='labelInput el-input']/input")).sendKeys("autoTest" + System.currentTimeMillis());//录入线索关键词
        driver.findElement(By.xpath("//div[@class='addKeywordContanier fl']/form/div[2]/div/button")).click();//点击新增
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("i.icon.el-icon-circle-close")).click();//关闭图层
        System.out.println("~~~ clueKeyword()，添加线索关键词，操作成功 ~~~");
        Thread.sleep(3000);
    }

    //删除线索关键词
    public static void delClueKeyword() throws InterruptedException {
        getButton("线索关键词");//点击线索关键词
        if (searchTestGroup()) {//校验是否有测试分类
            if (CommonMethod.isJudgingElement(driver, By.className("label-list-item"))) {//校验是否有关键词数据
                boolean hasTestKeyword = false;
                List<WebElement> labelList = driver.findElements(By.className("label-list-item"));//关键词列表
                for (int i = 0; i < labelList.size(); i++) {//遍历每个关键词
                    if (labelList.get(i).getText().contains("auto")) {//校验是否有auto关键词
                        Actions actions = new Actions(driver);
                        actions.moveToElement(labelList.get(i)).perform();//光标悬浮显示出删除icon
                        Thread.sleep(300);
                        labelList.get(i).findElement(By.xpath("./a")).click();//点击删除icon
                        Thread.sleep(500);
                        driver.findElement(By.cssSelector("button.el-button.el-button--default.el-button--small.el-button--primary")).click();//点击删除确认弹窗
                        Thread.sleep(1000);
                        driver.findElement(By.cssSelector("i.icon.el-icon-circle-close")).click();//点击关闭图层icon
                        hasTestKeyword = true;
                        System.out.println("~~~ delClueKeyword()，删除线索关键词，操作成功 ~~~");
                        break;
                    }
                }
                if (!hasTestKeyword) System.out.println("测试分类下没有自动化创建的线索关键词");
            } else System.out.println("测试分类下没有关键词");
        } else System.out.println("没有测试分类，无auto线索关键词");
        Thread.sleep(3000);
    }

    //编辑线索
    public static void editClue() throws InterruptedException {
        search();//搜索测试线索
        if (CommonMethod.isJudgingElement(driver, By.xpath("//table[@class='el-table__body']/tbody/tr"))) {//校验是否有auto线索数据
            driver.findElement(By.xpath("//table[@class='el-table__body']/tbody/tr[1]/td[1]/div/label/span")).click();//点击第一条线索复选框
            getButton("编辑");//点击编辑
            Thread.sleep(1000);
            driver.findElement(By.xpath("//form/div[1]/div/div/input")).clear();//清空标题
            driver.findElement(By.xpath("//form/div[1]/div/div/input")).sendKeys("autoTest线索-update-" + System.currentTimeMillis());//录入新标题
            driver.findElement(By.className("el-textarea__inner")).sendKeys("-update-" + System.currentTimeMillis());//编辑描述
            Thread.sleep(500);
            driver.findElement(By.cssSelector("button.el-button.save.el-button--default")).click();//点击保存
            Thread.sleep(1000);
            driver.findElement(By.cssSelector("i.icon.el-icon-circle-close")).click();//点击关闭
            System.out.println("~~~ editClue()，编辑线索，操作成功 ~~~");
        } else System.out.println("没有auto测试数据");
        Thread.sleep(3000);
    }


    //删除线索
    public static void delClue() throws InterruptedException {
        search();//搜索测试线索
        if (CommonMethod.isJudgingElement(driver, By.xpath("//table[@class='el-table__body']/tbody/tr"))) {//校验是否有auto线索数据
            driver.findElement(By.xpath("//table[@class='el-table__body']/tbody/tr[1]/td[1]/div/label/span")).click();//点击第一条线索复选框
            getButton("删除");//点击编辑
            Thread.sleep(500);
            driver.findElement(By.cssSelector("button.el-button.el-button--default.el-button--small.el-button--primary")).click();//点击保存
            System.out.println("~~~ delClue()，删除线索，操作成功 ~~~");
        } else System.out.println("没有auto测试数据");
        Thread.sleep(3000);
    }

    //点击按钮
    private static void getButton(String buttonString) throws InterruptedException {
        List<WebElement> buttons = driver.findElements(By.xpath("//div[@class='el-col el-col-21']/*"));//线索按钮
        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).getText().equals(buttonString)) {
                buttons.get(i).click();//点击添加线索icon
                break;
            }
        }
        Thread.sleep(1000);
    }

    //搜索测试数据
    private static void search() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(500);
        List<WebElement> searchItems = driver.findElements(By.xpath("//form/div"));//筛选搜索
        searchItems.get(3).findElement(By.xpath("./div/div/div")).click();//点击开始日期
        Thread.sleep(500);
        driver.findElement(By.cssSelector("button.el-picker-panel__icon-btn.el-date-picker__prev-btn.el-icon-arrow-left")).click();//点击上个月
        Thread.sleep(500);
        driver.findElement(By.xpath("//table[@class='el-date-table']/tbody/tr[3]/td[1]")).click();//点击某个日期
        Thread.sleep(500);
        searchItems.get(4).findElement(By.xpath("./div/div/div")).click();//开始结束日期
        Thread.sleep(500);
        List<WebElement> dateDivs = driver.findElements(By.cssSelector("div.el-picker-panel.el-date-picker.el-popper"));
        for (int i = 0; i < dateDivs.size(); i++) {
            if (!dateDivs.get(i).getAttribute("style").contains("display")) {//找到有效图层
                dateDivs.get(i).findElement(By.xpath("./div/div/div[1]/button[last()]")).click();//点击下个月
                Thread.sleep(500);
                dateDivs.get(i).findElement(By.xpath("./div/div/div[2]/table/tbody/tr[3]/td")).click();//点击某个日期
                break;
            }
        }
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@class='el-form-item form-input']/div/div/input")).sendKeys("auto");//录入搜索关键词
        Thread.sleep(500);
        driver.findElement(By.id("confirmBtn")).click();//点击搜索
        Thread.sleep(1000);
    }

    //添加分类
    private static void addGroup() throws InterruptedException {
        driver.findElement(By.cssSelector("button.el-button.el-button--text")).click();//点击添加分类
        Thread.sleep(500);
        driver.findElement(By.xpath("//div[@class='el-input']/input")).sendKeys("测试");//录入关键词
        driver.findElement(By.cssSelector("button.el-button.el-button--default.el-button--small.el-button--primary")).click();//点击确定
        Thread.sleep(500);
    }

    //查询测试分类
    private static boolean searchTestGroup() throws InterruptedException {
        boolean hasTestGroup = false;
        if (CommonMethod.isJudgingElement(driver, By.xpath("//div[@class='groupList']/a"))) {//校验是否有数据
            List<WebElement> groupList = driver.findElements(By.xpath("//div[@class='groupList']/a"));//数据列表
            for (int i = 0; i < groupList.size(); i++) {
                if (groupList.get(i).getText().contains("测试")) {//校验名称包含测试的
                    groupList.get(i).click();//点击该分类
                    hasTestGroup = true;
                    break;
                }
            }
        }
        Thread.sleep(500);
        return hasTestGroup;
    }

    //初始化登录
    static {
        try {
            driver = login();
            driver.get(domain + "/rplan/static/index.html#/clue");//跳转到线索总览页面
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
