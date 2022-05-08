package others;

import org.junit.Test;

import java.util.Stack;

/**
 * @Author:Z
 * @Date:2022/5/3 18:18
 * @Description: 栈测试
 * @Version:1.0
 */
public class StackTest {

    //测试栈指针
    @Test
    public void testStackPeek(){
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        int tmpPeek = stack.peek();
        System.out.println(tmpPeek);
        stack.push(3);
        stack.push(4);
        System.out.println(tmpPeek);
    }
}
