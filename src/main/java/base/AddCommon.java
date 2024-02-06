package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * @author wufeng
 * @date 2024/2/2 16:00
 */
public class AddCommon {

    //新建主题
    public static void addThemeInput(WebDriver driver) throws InterruptedException {
        List<WebElement> elements = driver.findElements(By.xpath("//form[@class='el-form']/div"));//内容项list
        //录入主题信息
        for (int i = 0; i < elements.size(); i++) {//遍历每个内容项
            if (CommonMethod.isJudgingElement(elements.get(i), By.xpath("./label"))) {//校验是否是有效内容项
                //录入标题
                if (elements.get(i).findElement(By.xpath("./label")).getText().contains("标题")) {
                    elements.get(i).findElement(By.xpath("./div/div/input")).sendKeys("autoTest主题-" + System.currentTimeMillis());//录入标题
                }
                //录入主题描述
                if (elements.get(i).findElement(By.xpath("./label")).getText().contains("描述")) {
                    elements.get(i).findElement(By.xpath("./div/div/textarea")).sendKeys("autoTest主题描述-" + System.currentTimeMillis());//录入主题描述
                }
                //选择选题
                if (elements.get(i).findElement(By.xpath("./label")).getText().contains("选题")) {
                    elements.get(i).findElement(By.xpath("./div/a")).click();//点击选题
                    Thread.sleep(300);
                    driver.findElement(By.xpath("//div[@class='keyword-search']/input")).sendKeys("auto");//搜索选题
                    driver.findElement(By.cssSelector("button.el-button.search-btn.not-hover.el-button--default")).click();//点击搜索
                    Thread.sleep(200);
                    //校验是否有选题
                    if (CommonMethod.isJudgingElement(driver, By.xpath("//table[@class='el-table__body']/tbody/tr"))) {
                        driver.findElement(By.xpath("//table[@class='el-table__body']/tbody/tr[1]")).click();//选择第一条选题
                        Thread.sleep(200);
                        driver.findElement(By.xpath("//div[@class='el-dialog']/div[3]/span/button[1]")).click();//点击确定，关闭选择选题图层
                    } else
                        driver.findElement(By.xpath("//div[@class='el-dialog']/div[3]/span/button[2]")).click();//点击取消，关闭选择选题图层
                }
                //选择稿件
                if (elements.get(i).findElement(By.xpath("./label")).getText().contains("稿件")) {
                    elements.get(i).findElement(By.xpath("./div/a")).click();//点击稿件
                    Thread.sleep(300);
                    driver.findElement(By.id("manuscriptSearchInput")).sendKeys("auto");//搜索稿件
                    driver.findElement(By.id("confirmBtn")).click();//点击搜索
                    Thread.sleep(200);
                    if (CommonMethod.isJudgingElement(driver, By.xpath("//ul[@class='manuscript-list']/li"))) {//判断是否有数据
                        driver.findElement(By.xpath("//ul[@class='manuscript-list']/li[1]/div/label/span/span")).click();//选中第一条
                        Thread.sleep(200);
                        driver.findElement(By.xpath("//div[@class='el-dialog manuscript-dialog']/div[3]/span/button[1]")).click();//点击确定
                    } else
                        driver.findElement(By.xpath("//div[@class='el-dialog manuscript-dialog']/div[3]/span/button[2]")).click();//点击取消
                }
            }
            Thread.sleep(500);
        }
        driver.findElement(By.cssSelector("button.el-button.save.el-button--default")).click();//点击保存
        Thread.sleep(1000);
    }

    //新建选题
    public static void addTopic(boolean isDepartment, boolean hasInterView, WebDriver driver) throws InterruptedException {

        List<WebElement> elements = driver.findElements(By.xpath("//main/form/div"));
        boolean addInterView = false;
        for (int i = 0; i < elements.size(); i++) {
            if (CommonMethod.isJudgingElement(elements.get(i), By.xpath("./label"))) {//校验是否是有效内容项
                //录入标题
                if (elements.get(i).findElement(By.xpath("./label")).getText().contains("标题")) {
                    elements.get(i).findElement(By.xpath("./div/div/input")).sendKeys("autoTest选题-" + System.currentTimeMillis());
                }
                //录入描述
                if (elements.get(i).findElement(By.xpath("./label")).getText().contains("描述")) {
                    elements.get(i).findElement(By.xpath("./div/div/textarea")).sendKeys("autoTest选题描述-" + System.currentTimeMillis());
                }
                //设置起止时间
                if (elements.get(i).findElement(By.xpath("./label")).getText().contains("起止时间")) {
                    elements.get(i).findElement(By.xpath("./div/div/input[2]")).click();
                    Thread.sleep(200);
                    driver.findElement(By.cssSelector("td.available.today.in-range.start-date.end-date")).click();//点击下个月
                    driver.findElement(By.xpath("//div[@class='el-picker-panel__body']/div[2]/table/tbody/tr[last()-1]/td[1]")).click();//点击下个月的某一天
                }
                //是否是部门线索
                if (!isDepartment)
                    if (elements.get(i).findElement(By.xpath("./label")).getText().contains("选题级别"))
                        elements.get(i).findElement(By.xpath("./div/div/label[2]/span/span")).click();
                //是否需要添加采访
                if (hasInterView) {
                    if (elements.get(i).findElement(By.xpath("./label")).getText().contains("采访安排")) {
                        elements.get(i).findElement(By.xpath("./div/a")).click();
                        Thread.sleep(500);
                        addInterView = addInterView(driver);//调用添加采访安排
                        if (!addInterView) System.out.println("采访安排未添加成功");
                    }
                }
                //添加关联稿件
                if (elements.get(i).findElement(By.xpath("./label")).getText().contains("关联稿件")) {
                    elements.get(i).findElement(By.xpath("./div/a")).click();
                    Thread.sleep(500);
                    addData(driver);
                }
                //添加所属主题
                if (elements.get(i).findElement(By.xpath("./label")).getText().contains("所属主题")) {
                    elements.get(i).findElement(By.xpath("./div/a")).click();
                    Thread.sleep(500);
                    addData(driver);
                }
                //添加线索
                if (elements.get(i).findElement(By.xpath("./label")).getText().contains("线索")) {
                    elements.get(i).findElement(By.xpath("./div/a")).click();
                    Thread.sleep(500);
                    addData(driver);
                }
            }
            Thread.sleep(500);
        }
        driver.findElement(By.cssSelector("button.el-button.save.el-button--default")).click();//点击保存
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("i.icon.el-icon-circle-close")).click();//点击关闭
        Thread.sleep(1000);
    }

    //报线索
    public static void addClue(WebDriver driver) throws InterruptedException {

        List<WebElement> elements = driver.findElements(By.xpath("//form/div"));//内容列表
        for (int i = 0; i < elements.size(); i++) {
            if (CommonMethod.isJudgingElement(elements.get(i), By.xpath("./label"))) {
                //录入标题
                if (elements.get(i).findElement(By.xpath("./label")).getText().contains("标题")) {//
                    elements.get(i).findElement(By.xpath("./div/div/input")).sendKeys("autoTest线索-" + System.currentTimeMillis());
                }
                //选择线索来源
                if (elements.get(i).findElement(By.xpath("./label")).getText().contains("线索来源")) {
                    elements.get(i).findElement(By.xpath("./div/div/div/div")).click();//点击线索来源下拉框
                    List<WebElement> list = driver.findElements(By.cssSelector("div.el-select-dropdown.el-popper"));//线索来源列表
                    for (int li = 0; li < list.size(); li++) {
                        if (!list.get(li).getAttribute("style").contains("display")) {
                            list.get(li).findElement(By.xpath("./div/div/ul/li[last()]")).click();//选择最后一条线索来源“其他”
                            break;
                        }
                    }
                }
            }
            Thread.sleep(500);
        }
        driver.findElement(By.tagName("textarea")).sendKeys("autoTest这是线索详情的内容" + System.currentTimeMillis());//录入线索详情
        driver.findElement(By.cssSelector("button.el-button.save.el-button--default")).click();//点击保存
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("i.icon.el-icon-circle-close")).click();//点击关闭
        Thread.sleep(1000);
    }

    //添加采访任务
    public static boolean addInterView(WebDriver driver) throws InterruptedException {
        boolean hasReporter = false;
        List<WebElement> list = driver.findElements(By.xpath("//form[@class='el-form']/div"));
        for (int i = 0; i < list.size(); i++) {
            if (CommonMethod.isJudgingElement(list.get(i), By.xpath("./label"))) {
                if (list.get(i).findElement(By.xpath("./label")).getText().contains("采访时间")) {
                    list.get(i).findElement(By.xpath("./div/div/div")).click();
                    Thread.sleep(500);
                    List<WebElement> dateDivs = driver.findElements(By.cssSelector("div.el-picker-panel.el-date-picker.el-popper"));
                    for (int d = 0; d < dateDivs.size(); d++) {
                        if (!dateDivs.get(d).getAttribute("style").contains("display")) {//有效图层
                            dateDivs.get(d).findElement(By.xpath("./div/div/div[2]/button[last()]")).click();//点击下个月
                            Thread.sleep(500);
                            dateDivs.get(d).findElement(By.xpath("./div/div/div[3]/table/tbody/tr[3]/td/div")).click();//点击某个日期
                            Thread.sleep(500);
                            dateDivs.get(d).findElement(By.xpath("./div/div/div/span[2]/div/input")).clear();
                            dateDivs.get(d).findElement(By.xpath("./div/div/div/span[2]/div/input")).sendKeys("08:00:00");
                            driver.findElement(By.cssSelector("button.el-time-panel__btn.confirm")).click();//设置时间的确定
                            Thread.sleep(500);
                            dateDivs.get(d).findElement(By.xpath("./div[2]/button[2]")).click();
                            Thread.sleep(500);
                            break;
                        }
                    }
//                    driver.findElement(By.cssSelector("button.el-picker-panel__icon-btn.el-date-picker__next-btn.el-icon-arrow-right")).click();//点击下个月
//                    Thread.sleep(500);
//                    driver.findElement(By.xpath("//div[@class='el-picker-panel__content']/table/tbody/tr[3]/td/div")).click();//点击某个日期
//                    driver.findElement(By.xpath("//div[@class='el-date-picker__time-header']/span[2]/div/input")).sendKeys("08:00:00");//设置时间
//                    driver.findElement(By.cssSelector("button.el-time-panel__btn.confirm")).click();//设置时间的确定
//                    Thread.sleep(200);
//                    driver.findElement(By.cssSelector("button.el-button.el-picker-panel__link-btn.el-button--default.el-button--mini.is-plain")).click();//点击确定关闭日期控件
                }
                if (list.get(i).findElement(By.xpath("./label")).getText().contains("采访记者")) {
                    list.get(i).findElement(By.xpath("./div/div/div")).click();
                    Thread.sleep(500);
                    list.get(i).findElement(By.xpath("./div/div/div/input")).sendKeys("吴枫");
                    Thread.sleep(300);
                    List<WebElement> listReporter = driver.findElements(By.cssSelector("div.el-select-dropdown.el-popper.is-multiple"));
                    for (int r = 0; r < listReporter.size(); r++) {
                        if (!listReporter.get(r).getAttribute("style").contains("display")) {
                            if (CommonMethod.isJudgingElement(listReporter.get(r), By.xpath("./div/div/ul/ul/li[2]/ul/li"))) {
                                listReporter.get(r).findElement(By.xpath("./div/div/ul/ul/li[2]/ul/li")).click();
                                hasReporter = true;
                                break;
                            }
                        }
                        Thread.sleep(300);
                    }
                    list.get(i).findElement(By.xpath("./label")).click();//失去焦点关闭下拉选项
                }
                if (list.get(i).findElement(By.xpath("./label")).getText().contains("出行方式")) {
                    list.get(i).findElement(By.xpath("./div/div/div")).click();//点击编辑框
                    Thread.sleep(500);
                    driver.findElement(By.xpath("//div[@class='el-select-dropdown el-popper']/div/div/ul/li[1]")).click();//点击第一个选项-派车
                }
                if (list.get(i).findElement(By.xpath("./label")).getText().contains("采访说明")) {
                    list.get(i).findElement(By.xpath("./div/div/textarea")).sendKeys("autoTest这是采访说明-" + System.currentTimeMillis());
                }
            }
            Thread.sleep(500);
        }
        if (hasReporter) {
            driver.findElement(By.cssSelector("button.el-button.el-button--primary.el-button--small")).click();//点击采访安排
            Thread.sleep(500);
        }
        List<WebElement> dialogs = driver.findElements(By.cssSelector("div.el-dialog__wrapper.clearfix"));//确定按钮div的list
        for (int d = 0; d < dialogs.size(); d++) {
            if (!dialogs.get(d).getAttribute("style").contains("display")) {//校验是否是有效div
                if (hasReporter) {
                    dialogs.get(d).findElement(By.xpath("./div/div[3]/div/button[1]")).click();//点击确定
                } else dialogs.get(d).findElement(By.xpath("./div/div[3]/div/button[3]")).click();//点击取消
                break;
            }
            Thread.sleep(500);
        }
        return hasReporter;
    }

    //添加列表数据
    private static void addData(WebDriver driver) throws InterruptedException {
        List<WebElement> dialogs = driver.findElements(By.xpath("//main/div"));//dialog图层
        WebElement dialog = null;
        for (int d = 0; d < dialogs.size(); d++) {
            if (!dialogs.get(d).getAttribute("style").contains("display")) {//校验是否是有效dialog图层
                dialog = dialogs.get(d);//取有效的dialog图层
                break;
            }
        }
        dialog.findElement(By.xpath("./div/div[2]/div/div/div/input")).sendKeys("auto");//录入关键词
        dialog.findElement(By.xpath("./div/div[2]/div/div/div/button")).click();//点击搜索
        Thread.sleep(1000);
        if (CommonMethod.isJudgingElement(dialog, By.xpath("./div/div[2]/div[2]/div[3]/table/tbody/tr"))) {//校验是否有数据
            List<WebElement> trs = dialog.findElements(By.xpath("./div/div[2]/div[2]/div[3]/table/tbody/tr"));//数据列表
            for (int t = 0; t < trs.size(); t++) {//遍历所有数据
                trs.get(t).findElement(By.xpath("./td[1]/div/label/span")).click();//选中稿件
            }
            Thread.sleep(1000);
            dialog.findElement(By.xpath("./div/div[last()]/span/button[1]")).click();//点击确定
        } else
            dialog.findElement(By.xpath("./div/div[last()]/span/button[2]")).click();//点击取消
        Thread.sleep(500);
    }

}
