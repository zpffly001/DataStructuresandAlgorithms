package xyz.zhoupf.top100;

import java.util.Deque;

/**
 * 两数相加
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 示例：输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)     输出：7 -> 0 -> 8   原因：342 + 465 = 807
 */
public class _02AddTwoNumbersTogether {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null){
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            //第一次迭代时，carry为0，因为第一次迭代就是从最后开始的，之前不会有进位
            int sum = x + y + carry;
            //如果x + y有进位，则carry为1，正好下次迭代时会加上1.
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        //这个是防止最后一次迭代有进位，但是while循环跳出了，因此我们要手动加上
        if (carry > 0){
            curr.next = new ListNode(carry);
        }
        //dummyHead和curr是指向同一个链表，最后curr循环到了链表尾部，而我们要的是链表头部，因此返回dummyHead，又因为链表首位为0，因此要从链表的第二个元素开始返回
        return dummyHead.next;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

}
