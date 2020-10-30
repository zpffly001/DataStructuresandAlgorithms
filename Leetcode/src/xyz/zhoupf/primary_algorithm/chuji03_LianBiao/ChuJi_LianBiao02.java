package xyz.zhoupf.primary_algorithm.chuji03_LianBiao;

/**
 * 删除链表的倒数第N个节点
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * 示例：
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 * 给定的 n 保证是有效的。
 * 进阶：
 * 你能尝试使用一趟扫描实现吗？
 */

public class ChuJi_LianBiao02 {

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
        //ListNode newHead = deleteListNode(2);
        System.out.println(removeNthFromEnd(head, 2));
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null)
            return head;
        ListNode p = head;
        ListNode q = head;
        //用count记录走了多少步，和最终链表的长度
        int count = 0;
        while (q.next != null){
            count++;
            //前n步只让q指针走，q刚开始指向第一个元素，起始走了n步后指向第1+n个元素因此后面即便是q和p一起走，那么q走到最后一位元素时，p指向倒数第n位元素的前一位，因为在之前q多走了一步
            if (count <= n)
                q = q.next;
            else{
                q = q.next;
                p = p.next;
            }
        }
        //循环结束时p到达了倒数n个元素的前面一个元素
        //两个特殊情况，即链表只有一个元素和要删除的为头节点的情况
        //head.next == null表示元素只有一个。
        //n == count + 1表示删除最后一个元素，因为count+1表示链表长度
        if (head.next == null || n == count + 1)
            head = head.next;
        else
            //p这时指向的是倒数第n个元素前面的那个元素
            p.next = p.next.next;
        return head;
    }

    private static class ListNode {
        private int data;
        ListNode next;
        public ListNode(int data){
            this.data = data;
        }

        //获取链表长度
        public static int getLength(ListNode ListNode){
            int length = 1;
            while (ListNode.next != null){
                ++length;
                ListNode = ListNode.next;
            }
            return length;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "data=" + data +
                    ", next=" + next +
                    '}';
        }
    }

}
