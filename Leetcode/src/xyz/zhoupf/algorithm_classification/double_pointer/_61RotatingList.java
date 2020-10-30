package xyz.zhoupf.algorithm_classification.double_pointer;

/**
 * 61- 旋转链表
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 *
 * 示例 1:
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 输出: 4->5->1->2->3->NULL
 * 解释:
 * 向右旋转 1 步: 5->1->2->3->4->NULL
 * 向右旋转 2 步: 4->5->1->2->3->NULL
 */
public class _61RotatingList {

    /**
     * 先将链表闭合成环
     * 找到相应的位置断开这个环，确定新的链表头和链表尾
     */
    public ListNode rotateRight(ListNode head, int k) {

        if (head == null) return null;
        if (head.next == null) return head;

        ListNode old_tail = head;
        //代表链表元素的个数
        int n;
        //让链表形成一个闭环
        for (n = 1; old_tail.next != null; n++)
            old_tail = old_tail.next;
        old_tail.next = head;

        ListNode new_tail = head;
        //循环，让new_tail指向要被截断的节点的前一位，即阶段后的链表的最后一位。
        for (int i = 0; i < n - k % n - 1; i++)
            new_tail = new_tail.next;
        ListNode new_head = new_tail.next;

        new_tail.next = null;
        return new_head;


        /*ListNode node;
        ListNode firstNode = head;
        ListNode lastNode = head;
        for (int i = 0; i < k; i++) {
            firstNode = firstNode.next;
        }
        while (firstNode.next != null){
            firstNode = firstNode.next;
            lastNode = lastNode.next;
        }
        firstNode.next = head;
        head = lastNode.next;
        lastNode.next = null;
        return head;*/
    }

    private class ListNode{
        int var;
        ListNode next;
        ListNode(int x){
            var = x;
        }
    }
}
