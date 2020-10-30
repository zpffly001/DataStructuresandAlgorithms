package xyz.zhoupf.algorithm_classification.double_pointer;

/**
 * 返回倒数第 k 个节点--程序员面试金典
 * 实现一种算法，找出单向链表中倒数第 k 个节点。返回该节点的值。
 *
 * 注意：本题相对原题稍作改动
 * 示例：输入： 1->2->3->4->5 和 k = 2 输出： 4
 */
public class _02_02ReturnsTheKthNodeFromTheBottom {

    public int kthToLast(ListNode head, int k) {
        ListNode pointNode = head;
        ListNode mostNode = head;
        int len = 0;
        while (pointNode.next != null){
            if (len < k - 1){
                len++;
            }else {
                mostNode = mostNode.next;
            }
            pointNode = pointNode.next;
        }
        return mostNode.val;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static void main(String[] args) {

    }

}
