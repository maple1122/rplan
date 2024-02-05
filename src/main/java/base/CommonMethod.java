package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

/**
 * @author wufeng
 * @date 2024/1/31 10:56
 */
public class CommonMethod {

    //校验元素是否能找到
    public static boolean isJudgingElement(WebDriver driver, By by) {
        try {
            driver.findElement(by);
            return true;//有登录按钮，登录界面
        } catch (Exception e) {
            return false;//无登录按钮，非登录界面
        }
    }

    //校验元素是否存在
    public static boolean isJudgingElement(WebElement webElement, By by) {
        try {
            webElement.findElement(by);
            return true;//有登录按钮，登录界面
        } catch (Exception e) {
            return false;//无登录按钮，非登录界面
        }
    }

    //模块跳转
    public static boolean jumpModule(WebDriver driver, String serviceName) throws InterruptedException {
        boolean success = false;
        if (isJudgingElement(driver, By.className("swiper-wrapper"))) {
            List<WebElement> pages = driver.findElements(By.xpath("//div[@class='swiper-pagination bm swiper-pagination-custom']/span"));//模块页面
            for (int p = 0; p < pages.size(); p++) {//遍历每个模块页面
                pages.get(p).click();//点击页面模块下方切换，跳转到模块页面
                Thread.sleep(500);
                List<WebElement> box = driver.findElements(By.xpath("//div[@class='swiper-wrapper']/div[" + (p + 1) + "]/div/div"));//当前页面模块组
                for (int i = 0; i < box.size(); i++) {//遍历当前页面模块组
                    List<WebElement> list = box.get(i).findElements(By.xpath("./a"));//当前页面模块组中的功能模块
                    for (int j=0;j< list.size();j++){//遍历模块组中的模块
                        if (list.get(j).getText().contains(serviceName)){//校验该模块名称是否与期望相符
                            list.get(j).click();//点击该应用模块
                            Thread.sleep(2000);
                            swichWindow(driver);//切换到该标签页
                            success=true;
                            break;
                        }
                        Thread.sleep(100);
                    }
                    if (success) break;
                    Thread.sleep(100);
                }
                if (success)break;
                Thread.sleep(100);
            }
        }
        return success;
    }

    //切换到新标签页
    public static void swichWindow(WebDriver driver) {
        Set<String> windowHandles = driver.getWindowHandles();
        WebDriver.TargetLocator targetLocator = driver.switchTo();
        String handle = "";
        for (String windowHandle : windowHandles) {
            handle = windowHandle;
        }
        targetLocator.window(handle);// 切换到对应标签页
    }

}
