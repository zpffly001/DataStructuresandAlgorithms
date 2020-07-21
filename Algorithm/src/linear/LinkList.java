package linear;

import java.util.Iterator;

public class LinkList<T> implements Iterable<T>{
    //记录头结点
    private Node head;
    //记录链表的长度
    private int N;

    //结点类
    private class Node{
        //存储数据
        T item;
        //下一个结点
        Node next;
        public Node(T item, Node next){
            this.item = item;
            this.next = next;
        }
    }

    public LinkList(){
        //初始化头结点
        this.head = new Node(null, null);
        this.N = 0;
    }
    //清空链表
    public void clear(){
        head.next=null;
        head.item=null;
        N=0;
    }
    //获取链表的长度
    public int length(){
        return N;
    }
    //判断链表是否为空
    public boolean isEmpty(){
        return N==0;
    }
    //获取指定位置i出的元素
    public T get(int i) {
        if (i<0||i>=N){
            throw new RuntimeException("位置不合法！");
        }
        //创建一个新节点，指向链表的第一个元素
        Node n = head.next;
        for (int index=0;index<i;index++){
            n = n.next;
        }
        return n.item;
    }
    //向链表中添加元素t
    public void insert(T t){
        //找到最后一个节点
        Node n = head;
        while (n.next!=null){//如果跳出循环，说明已经到了最后一个
            n = n.next;
        }
        Node newNode = new Node(t, null);
        n.next = newNode;
        //链表长度+1
        N++;
    }
    //向指定位置i处，添加元素t
    public void insert(int i,T t){
        //寻找位置i之前的结点
        Node pre = head;
        for (int index = 0; index <=i-1; index++) {
            pre = pre.next;
        }
        //位置i的结点
        Node curr = pre.next;

        //构建新的结点，让新结点指向位置i的结点
        Node newNode = new Node(t, curr);
        //让之前的结点指向新结点
        pre.next = newNode;
        //长度+1
        N++;
    }

    //删除指定位置i处的元素，并返回被删除的元素
    public T remove(int i){
        if (i<0 || i>=N){
            throw new RuntimeException("位置不合法");
        }
        //寻找i之前的元素
        Node pre = head;
        for (int index = 0; index <=i-1; index++) {
            pre = pre.next;
        }
        //当前i位置的结点
        Node curr = pre.next;
        //前一个结点指向下一个结点，然后i这个节点没人连接，就会被GC，即删除当前结点
        pre.next = curr.next;
        //长度-1
        N--;
        return curr.item;
    }

    //查找元素t在链表中第一次出现的位置
    public int indexOf(T t){
        Node n = head;
        for (int i=0;n.next!=null;i++){
            n=n.next;
            if (n.item.equals(t)){
                return i;
            }
        }
        return -1;
    }

    //实现Iterable接口的iterator()方法，实现遍历功能
    @Override
    public Iterator iterator() {
        return new LIterator();
    }
    private class LIterator implements Iterator<T>{
        private Node n;
        public LIterator() {
            this.n = head;
        }
        @Override
        public boolean hasNext() {
            return n.next!=null;
        }
        @Override
        public T next() {
            n = n.next;
            return n.item;
        }
    }
    //单链表的反转
    public void reverse(){
        if (N==0){
        //当前是空链表，不需要反转
            return;
        }
        reverse(head.next);//传入原链表第一个元素
    }
    /**
     *递归调用反转的原理：
     * 反转后的节点的.next置为null,就是不让反转后的节点指向下一个，然后在下一次调用时，再把本届点.next指向下一次调用的那个节点
     * @param curr 当前遍历的结点
     * @return 反转后当前结点上一个结点
     */
    public Node reverse(Node curr){
        //已经到了最后一个元素
        if (curr.next == null){
            //已经到了最后一个元素，即反转完成，让头结点应该指向原链表中的最后一个元素
            head.next = curr;
            return curr;
        }

        //递归调用反转当前节点curr的下一个节点，返回的结果是反转后，当前节点的上一个节点即，curr.pre--3
        Node curr_pre = reverse(curr.next);//curr.next是当前节点的下一个节点--未反转前，若curr是2
        //以下两步是反转过程
        curr_pre.next = curr;//设置3的下一个为2
        //当前结点的下一个结点设为null
        curr.next = null;//2的下一个为空
        //返回当前结点
        return curr;
    }

}

//测试代码
class TestLinkList {
    public static void main(String[] args) throws Exception {
        LinkList<String> list = new LinkList<>();
        list.insert(0, "张三");
        list.insert(1, "李四");
        list.insert(2, "王五");
        list.insert(3, "赵六");
        //测试length方法
        for (String s : list) {
            System.out.println(s);
        }
        System.out.println(list.length());
        System.out.println("-------------------");
        //测试get方法
        System.out.println(list.get(2));
        System.out.println("------------------------");
        //测试remove方法
        String remove = list.remove(1);
        System.out.println(remove);
        System.out.println(list.length());
        System.out.println("----------------");
        for (String s : list) {
            System.out.println(s);
        }
        list.reverse();
        for (String i : list) {
            System.out.print(i+" ");
        }

    }
}

//快慢指针问题
class TestFastSlow {
    public static void main(String[] args) throws Exception {
        Node<String> first = new Node<String>("aa", null);
        Node<String> second = new Node<String>("bb", null);
        Node<String> third = new Node<String>("cc", null);
        Node<String> fourth = new Node<String>("dd", null);
        Node<String> fifth = new Node<String>("ee", null);
        Node<String> six = new Node<String>("ff", null);
        Node<String> seven = new Node<String>("gg", null);
        //完成结点之间的指向
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        fifth.next = six;
        six.next = seven;
        //查找中间值
        String mid = getMid(first);
        System.out.println("中间值为："+mid);
    }
    /**
     * @param first 链表的首结点
     * @return 链表的中间结点的值
     */
    public static <T>T getMid(Node first) {
        Node<T> slow = first;
        Node<T> fast = first;
        while (fast!=null && fast.next!=null){//fast.next!=null避免了偶数个时，差一个没有遍历完的情况，因为遍历的速度为2，因此只可能正好遍历完或者差一个没有遍历完
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow.item;
    }
    //结点类
    private static class Node<T> {
        //存储数据
        T item;
        //下一个结点
        Node next;
        public Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }
    }
}

//测试类
class TestIsCircle {
    public static void main(String[] args) throws Exception {
        Node<String> first = new Node<String>("aa", null);
        Node<String> second = new Node<String>("bb", null);
        Node<String> third = new Node<String>("cc", null);
        Node<String> fourth = new Node<String>("dd", null);
        Node<String> fifth = new Node<String>("ee", null);
        Node<String> six = new Node<String>("ff", null);
        Node<String> seven = new Node<String>("gg", null);
        //完成结点之间的指向
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        fifth.next = six;
        six.next = seven;
        //产生环
        seven.next = second;
        //判断链表是否有环
        boolean circle = isCircle(first);
        System.out.println(circle);
        System.out.println(first.toString());
    }
    /**
     * 判断链表中是否有环
     * @param first 链表首结点
     * @return ture为有环，false为无环
     */
    public static boolean isCircle(Node<String> first) {//泛型类型再定义引用时，可以不标泛型标志
        Node<String> slow = first;
        Node<String> fast = first;
        while (fast!=null && fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
            if (fast.equals(slow)){
                return true;
            }
        }
        return false;
    }
    //结点类
    private static class Node<T> {
        //存储数据
        T item;
        //下一个结点
        Node next;
        public Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }
    }
}


/**
 * 测试有环链表的入口在哪的问题
 * 原理：当快慢指针相遇时，我们可以判断到链表中有环，这时重新设定一个新指针指向链表的起点，且步长与慢指针一样
 * 为1，则慢指针与“新”指针相遇的地方就是环的入口。证明这一结论牵涉到数论的知识，这里略，只讲实现
 */
class TestCircleEntrance {
    public static void main(String[] args) throws Exception {
        Node<String> first = new Node<String>("aa", null);
        Node<String> second = new Node<String>("bb", null);
        Node<String> third = new Node<String>("cc", null);
        Node<String> fourth = new Node<String>("dd", null);
        Node<String> fifth = new Node<String>("ee", null);
        Node<String> six = new Node<String>("ff", null);
        Node<String> seven = new Node<String>("gg", null);
        //完成结点之间的指向
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        fifth.next = six;
        six.next = seven;
        //产生环
        seven.next = third;
        //查找环的入口结点
        Node<String> entrance = getEntrance(first);
        System.out.println("first链表中环的入口结点元素为："+entrance.item);
    }
    /**
     * 查找有环链表中环的入口结点
     * @param first 链表首结点
     * @return 环的入口结点
     */
    public static Node getEntrance(Node<String> first) {
        //定义快慢指针
        Node<String> slow = first;
        Node<String> fast = first;
        Node<String> temp = null;
        //判断链表是否有环，如果快慢指针相遇，则表明有环，在此时再链表第一位创建一个新的指针
        while (fast!=null && fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
            if (fast.equals(slow)){
                temp = first;
                continue;//结束本次循环，继续下次循环
            }
            //让临时节点变换
            if (temp!=null){//说明有环
                temp = temp.next;
                if (temp.equals(slow)){
                    return temp;
                }
            }
        }

        return null;
    }
    //结点类
    private static class Node<T> {
        //存储数据
        T item;
        //下一个结点
        Node next;
        public Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }
    }
}
