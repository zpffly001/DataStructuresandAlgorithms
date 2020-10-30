package xyz.zhoupf.primary_algorithm.chuji03_LianBiao;

/**
 * 反转链表
 * 反转一个单链表。
 * 示例:
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->N
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 */
public class ChuJi_LianBiao03 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode ListNode1 = new ListNode(2);
        ListNode ListNode2 = new ListNode(3);
        ListNode ListNode3 = new ListNode(4);
        ListNode ListNode4 = new ListNode(5);
        head.next = ListNode1;
        ListNode1.next = ListNode2;
        ListNode2.next = ListNode3;
        ListNode3.next = ListNode4;
        ListNode4.next = null;
        System.out.println(reverseList(head));
        System.out.println(reverseList3(head));
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null)
            return null;
        //始终指向链表的头节点
        ListNode pFirst = head;
        //三个节点中的第一个节点,1
        ListNode pOne = pFirst;
        //当前需要反转的节点,2
        ListNode pTwo = head.next;
        //下一次即将需要反转的节点,3
        ListNode pThree = null;
        while (pTwo != null){
            //下面三步使第二个指向第一个，第一个指向第三个
            pThree = pTwo.next;
            pOne.next = pThree;
            pTwo.next = pFirst;
            //此时pTwo以和前面的元素pOne完成互换，且每次交换后pTwo都是第一个元素
            pFirst = pTwo;
            pTwo = pThree;
        }
        return pFirst;
    }

    //递归写法
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pNext = head.next;
        head.next = null;
        ListNode reverseListNode = reverseList2(pNext);
        pNext.next = head;
        return reverseListNode;
    }

    public static ListNode reverseList3(ListNode head) {
        if (head == null)
            return null;
        //计算链表长度，存储到len
        int len = 0;
        for(ListNode cur = head; cur != null; cur = cur.next){
            len++;
        }

        // 反转前半段链表
        ListNode cur = head, pre = null;
        for (int i = 0; i < len; i++) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }

        return cur;
    }

    private static class ListNode{
        Integer data;
        ListNode next;
        @Override
        public String toString() {
            return "ListNode{" +
                    "data=" + data +
                    ", next=" + next +
                    '}';
        }
        public ListNode(Integer data){
            this.data = data;
        }
    }

}
