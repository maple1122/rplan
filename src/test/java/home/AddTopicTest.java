package home;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.testng.Assert.*;

/**
 * @author wufeng
 * 新建选题
 * @date 2024/1/31 17:16
 */
public class AddTopicTest {

    @Test
    public void testAddTopic() throws InterruptedException {
//        AddTopic.addTopic(true);
//        AddTopic.addTopic(false);
        AddTopic.addTopic(false,true);
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