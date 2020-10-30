package xyz.zhoupf.algorithm_classification.double_pointer;

/**
 * 分隔链表
 * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 * 你应当保留两个分区中每个节点的初始相对位置。
 * 示例:  输入: head = 1->4->3->2->5->2, x = 3  输出: 1->2->2->4->3->5
 */
public class _86SeparateLinkedList {

    public ListNode partition(ListNode head, int x) {
        ListNode before_head = new ListNode(0);
        ListNode after_head = new ListNode(0);
        //这里存放大的变量的链表，和小的变量的链表分别先指向哑节点，即before,after他们的next第二个节点才是存储数据的开始
        ListNode before = before_head;
        ListNode after = after_head;

        while (head != null){
            if (head.val < x){
                before.next = head;
                before = before.next;
            }else {
                after.next = head;
                after = after.next;
            }
            head = head.next;
        }
        after.next = null;
        //这里before其实指向存放小的变量的链表的最后一位了，同理after其实指向存放大的变量的链表的最后一位了
        //因此要让小链表的最后一位before的下一位指向大链表的第一位，即after_head.next，因为after_head指向的是哑节点
        before.next = after_head.next;

        return before_head.next;
    }


     public class ListNode {
         int val;
         ListNode next;
         ListNode(int x) { val = x; }
     }

}
