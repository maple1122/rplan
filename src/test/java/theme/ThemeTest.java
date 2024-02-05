package theme;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.testng.Assert.*;

/**
 * @author wufeng
 * @date 2024/2/2 15:07
 */
public class ThemeTest {

    @Test(priority = 1)//新建主题
    public void testAddTheme() throws InterruptedException {
        Theme.addTheme();
    }

    @Test(priority = 2)//编辑主题
    public void testEditTheme() throws InterruptedException {
        Theme.editTheme();
    }

    @Test(priority = 3)//删除主题
    public void testDelTheme() throws InterruptedException {
        Theme.delTheme();
    }

    @BeforeMethod
    public void testStart(Method method) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>> Test case: "
                + method.getName());
    }

    @AfterMethod
    public void testEnd(Method method) {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<< Test End!\n");
    }
}