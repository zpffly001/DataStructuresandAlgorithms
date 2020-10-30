package xyz.zhoupf.primary_algorithm.chuji03_LianBiao;

/**
 * 合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 
 * 示例：
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 */
public class ChuJi_LianBiao04 {

    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);
        ListNode ListNode1 = new ListNode(2);
        ListNode ListNode2 = new ListNode(4);
        head1.next = ListNode1;
        ListNode1.next = ListNode2;
        ListNode2.next = null;

        ListNode head2 = new ListNode(1);
        ListNode ListNode01 = new ListNode(3);
        ListNode ListNode02 = new ListNode(4);
        head2.next = ListNode01;
        ListNode01.next = ListNode02;
        ListNode02.next = null;

        System.out.println(mergeTwoLists(head1, head2));

    }

    //合并两个有序链表
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode initial = new ListNode(0);
        ListNode curr = initial;
        while (l1 != null && l2 != null){
            //比较一下，哪个值小就把哪个放到新的链表中
            if (l1.data <= l2.data){
                curr.next = l1;
                l1 = l1.next;
            }else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        //然后把那个不为空的链表挂到新的链表中
        curr.next = l1 == null ? l2 : l1;
        return initial.next;
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
