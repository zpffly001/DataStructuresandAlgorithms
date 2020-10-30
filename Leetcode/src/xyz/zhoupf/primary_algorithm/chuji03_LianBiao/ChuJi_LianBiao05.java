package xyz.zhoupf.primary_algorithm.chuji03_LianBiao;

public class ChuJi_LianBiao05 {

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
        System.out.println(isPalindrome(head));
    }

    /**
     * 1.关注代码的完整性、规范性、鲁棒性。
     * 2.大致思路是反转前一半链表结点,分别和后面的链表结点值一一进行比较;
     * 3.求出链表长度len,反转前半段链表,此时pre指向反转链表的头结点,也就是原链表的中间结点,cur指向后半段链表的第一个节点,如果len为奇数个,那么后半段链表的第一个节点是中间节点,需要将cur后移一位,最后遍历pre和cur比较值即可;
     * 4.完结。
     */
    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;

        //计算链表长度，存储到len
        int len = 0;
        for(ListNode cur = head; cur != null; cur = cur.next){
            len++;
        }

        // 反转前半段链表
        ListNode cur = head, pre = null;
        for (int i = 0; i < len / 2; i++) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        // 奇数个链表结点cur后移一位
        if (len % 2 != 0){
            cur = cur.next;
        }
        // 遍历比较pre和cur的值相等否
        for(ListNode p = cur, q = pre; p != null && q != null; p = p.next, q = q.next){
            if (p.val != q.val)
                return false;
        }
        return true;
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
