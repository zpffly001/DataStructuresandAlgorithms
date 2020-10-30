package xyz.zhoupf.primary_algorithm.chuji07_designquestion;

/**
 * 最小栈
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 */
public class chuji02_MinStack {

    static Node head;
    //用链表摸方栈，每次push加入的节点放到头部
    public static void push(int x){
        if (head == null){
            head = new Node(x, x);
        }else {
            Node n = new Node(x, Math.min(x, head.min));
            //为了让每次添加进来的数据都是最前面的那个，先让n的下一位指向当前链表最前面
            //这样新链表就是表头了，为了下面迭代，让引用head再次指向现在链表首部n节点
            //正好，在初始化n时，min是全链表的最小值，连在了头部，且head引用仍然指向首部
            n.next = head;
            head = n;
        }
    }

    public static void pop() {
        if (head != null) head = head.next;
    }

    public static int top() {
        if (head != null) return head.value;
        return -1;
    }

    public static int getMin() {
        if(null!=head) return head.min;
        return -1;
    }


    private static class Node{
        int value;
        int min;
        Node next;

        Node(int x, int min){
            this.value = x;
            this.min = min;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 9, 20, 1, 18};
        for (int i = 0; i < arr.length; i++) {
            push(arr[i]);
        }
        System.out.println(getMin());
    }

}
