package xyz.zhoupf.topic1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * 从尾到头打印链表
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 * 示例 1：输入：head = [1,3,2] 输出：[2,3,1]
 * 限制：0 <= 链表长度 <= 10000
 */
public class _05PrintLinkedListFromEndToEnd {

    //方法一，创建临时变量存储中间值，反转链表
    public static int[] reversePrint(ListNode head) {
        ListNode pre = null;
        while (head != null){
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        ArrayList<Integer> list = new ArrayList<>();
        while (pre != null){
            list.add(pre.val);
            System.out.println(pre.val);
            pre = pre.next;
        }
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    //方法二：栈，以后只要是关于反转的，优先考虑栈
    public int[] reversePrint01(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode temp = head;
        while (temp != null){
            stack.push(temp);
            temp = temp.next;
        }
        int size = stack.size();
        int[] print = new int[size];
        for (int i = 0; i < size; i++) {
            print[i] = stack.pop().val;
        }
        return print;
    }

    /**
     * 方法三：递归法
     * 递推阶段： 每次传入 head.next ，以 head == null（即走过链表尾部节点）为递归终止条件，此时直接返回。
     * 回溯阶段： 层层回溯时，将当前节点值加入列表，即tmp.add(head.val)。
     * 最终，将列表 tmp 转化为数组 res ，并返回即可。
     */
    ArrayList<Integer> tmp = new ArrayList<Integer>();
    public int[] reversePrint02(ListNode head) {
        recur(head);
        int[] res = new int[tmp.size()];
        for(int i = 0; i < res.length; i++)
            res[i] = tmp.get(i);
        return res;
    }
    private void recur(ListNode head){
        //递推阶段： 每次传入 head.next ，以 head == null（即走过链表尾部节点）为递归终止条件，此时直接返回。
        if (head == null) return;
        recur(head.next);
        //回溯阶段： 层层回溯时，将当前节点值加入列表，即tmp.add(head.val)。
        tmp.add(head.val);
    }

    private static class ListNode{
        Integer val;
        ListNode next;
        @Override
        public String toString() {
            return "ListNode{" +
                    "data=" + val +
                    ", next=" + next +
                    '}';
        }
        public ListNode(Integer data){
            this.val = data;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        head.next = node1;
        node1.next = node2;
        node2.next = null;
        System.out.println(Arrays.toString(reversePrint(head)));
    }

}
