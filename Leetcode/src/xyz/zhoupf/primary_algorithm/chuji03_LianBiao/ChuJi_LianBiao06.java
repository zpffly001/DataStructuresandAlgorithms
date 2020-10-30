package xyz.zhoupf.primary_algorithm.chuji03_LianBiao;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 环形链表
 * 给定一个链表，判断链表中是否有环。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 */
public class ChuJi_LianBiao06 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode ListNode1 = new ListNode(2);
        ListNode ListNode2 = new ListNode(3);
        ListNode ListNode3 = new ListNode(2);
        ListNode ListNode4 = new ListNode(1);
        head.next = ListNode1;
        ListNode1.next = ListNode2;
        ListNode2.next = ListNode3;
        ListNode3.next = ListNode4;
        ListNode4.next = null;
        System.out.println(hasCycle(head));
    }

    public static boolean hasCycle(ListNode head) {
        Set<ListNode> set = new LinkedHashSet<>();
        while (head != null){
            if (set.contains(head)){
                return true;
            }
            set.add(head);
            head = head.next;
        }

        return false;
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

}
