package clue;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.testng.Assert.*;

/**
 * @author wufeng
 * @date 2024/2/6 14:25
 */
public class ClueTest {

    @Test(priority = 1)//新建线索
    public void testAddClue() throws InterruptedException {
        Clue.addClue();
    }

    @Test(priority = 2)//添加选题
    public void testAddTopicByClue() throws InterruptedException {
        Clue.addTopicByClue();
    }

    @Test(priority = 3)//加入研判
    public void testAddDetermine() throws InterruptedException {
        Clue.addDetermine();
    }

    @Test(priority = 4)//研判删除测试线索
    public void testDelDetermine() throws InterruptedException {
        Clue.delDetermine();
    }

    @Test(priority =5 )//采用
    public void testUse() throws InterruptedException {
        Clue.use();
    }

    @Test(priority = 6)//添加线索关键词
    public void testAddClueKeyword() throws InterruptedException {
        Clue.addClueKeyword();
    }

    @Test(priority = 7)//删除线索关键词
    public void testDelClueKeyword() throws InterruptedException {
        Clue.delClueKeyword();
    }

    @Test(priority =8 )//编辑线索
    public void testEditClue() throws InterruptedException {
        Clue.editClue();
    }

    @Test(priority = 9)//删除线索
    public void testDelClue() throws InterruptedException {
        Clue.delClue();
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