package xyz.zhoupf.topic1;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，
 * 分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
 * 示例 1：输入：
 * ["CQueue","appendTail","deleteHead","deleteHead"]
 * [[],[3],[],[]]
 * 输出：[null,null,3,-1]
 * 示例 2：输入：
 * ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
 * [[],[],[5],[2],[],[]]
 * 输出：[null,-1,null,null,5,2]
 */
public class _09QueueWithTwoStacks {

    //方式一
    /*LinkedList<Integer> A, B;
    public _09QueueWithTwoStacks() {
        A = new LinkedList<>();
        B = new LinkedList<>();
    }
    public void appendTail(int value) {
        A.addLast(value);
    }
    public int deleteHead() {
        if (!B.isEmpty()) return B.removeLast();
        if (A.isEmpty()) return -1;
        while (!A.isEmpty()){
            B.addLast(A.removeLast());
        }
        return B.removeLast();
    }*/

    //方式二，和方式一没啥区别
    Deque<Integer> stack1;
    Deque<Integer> stack2;
    public _09QueueWithTwoStacks() {
        stack1 = new LinkedList<>();
        stack2 = new LinkedList<>();
    }
    public void appendTail(int value) {
        stack1.add(value);
    }
    public int deleteHead() {
        // 如果第二个栈为空，则循环把stack1中的数据取出来
        if (stack2.isEmpty()){
            while (!stack1.isEmpty()){
                stack2.add(stack1.pop());
            }
        }
        //如果把stack1中的内容弹出给了stack2，但stack2仍然为空，说明stack1为空或者stack2出栈完毕，则直接返回-1
        if (stack2.isEmpty()) return -1;
        //走到这里表明默认stack1为空，stack2不为空，则stack2出栈。
        else {
            int deleteItem = stack2.pop();
            return deleteItem;
        }
    }

}
