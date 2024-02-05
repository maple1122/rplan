package topic;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.testng.Assert.*;

/**
 * @author wufeng
 * @date 2024/2/4 10:58
 */
public class TopicTest {

    @Test(priority = 1)//新建选题
    public void testAddTopic() throws InterruptedException {
        Topic.addTopic(true,true);
    }

//    @Test(priority = 2)//审核选题
//    public void testAudit() throws InterruptedException {
//        Topic.audit();
//    }

    @Test(priority = 3)//删除选题
    public void testDelete() throws InterruptedException {
        Topic.delete();
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