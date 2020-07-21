package linear;

import java.util.Iterator;

/**
 * 获取指定位置i处的元素
 *  Node curr = head.next;
 *         for (int index=0;index<i;index++){
 *             curr = curr.next;
 *         }
 * 找到位置i的前一个结点
 * Node pre = head;
 *         for (int index=0;index<i;index++){
 *             pre = pre.next;
 *         }
 * @param <T>
 */
public class ToWayLinkList<T> implements Iterable<T>{

    //首结点
    private Node head;
    //最后一个结点
    private Node last;
    //链表的长度
    private int N;

    //结点类
    private class Node{
        //存储数据
        public T item;
        //指向上一个结点
        public Node pre;
        //指向下一个结点
        public Node next;

        public Node(T item, Node pre, Node next){
            this.item = item;
            this.pre = pre;
            this.next = next;
        }
    }
    //构造函数
    public ToWayLinkList(){
        this.last = null;
        this.head = new Node(null, null, null);
        this.N = 0;
    }
    //清空链表
    public void clear(){
        this.last = null;
        this.head.pre = null;
        this.head.item = null;
        this.head.next = null;//this.head.next = last;
        this.N = 0;
    }
    //获取链表长度
    public int length(){
        return N;
    }
    //判断链表是否为空
    public boolean isEmpty(){
        return N==0;
    }
    //插入元素t
    public void insert(T t){
        if (isEmpty()){//如果链表为空
            //创建新的节点
            Node newNode = new Node(t, head ,null);
            //让新节点称为尾节点
            last = newNode;
            //让头节点指向尾节点
            head.next = last;
        }else {//如果链表不为空
            Node oldNode = last;
            //创建新的节点
            Node newNode = new Node(t, oldNode, null);
            //让尾节点指向新节点
            oldNode.next = newNode;
            //让新节点称为尾节点
            last = newNode;
        }
        N++;
    }
    //向指定位置i处插入元素t
    public void insert(int i,T t){
        //找到位置i的前一个结点
        Node pre = head;
        for (int index=0;index<i;index++){
            pre = pre.next;
        }
        //找到i位置的结点
        Node curr = pre.next;
        //构建新结点
        Node newNode = new Node(t, pre, curr);
        //让i位置的前一个节点的下一个节点变为新节点
        pre.next = newNode;
        //让i位置的上一个节点成为新节点
        curr.pre = newNode;
        //元素个数+1
        N++;
    }
    //获取指定位置i处的元素
    public T get(int i){
        Node curr = head.next;
        for (int index=0;index<i;index++){
            curr = curr.next;
        }
        return curr.item;
    }
    //找到元素t在链表中第一次出现的位置
    public int indexOf(T t){
        Node n = head;
        for (int index=0;n.next!=null;index++){
            n = n.next;
            if (n.item.equals(t)){
                return index;
            }
        }
        return -1;
    }
    //删除位置i处的元素，并返回该元素
    public T remove(int i){
        //寻找i位置的前一个元素
        Node curr_pre = head;
        for (int index=0;index<i;index++){
            curr_pre = curr_pre.next;
        }
        //i位置的元素
        Node curr = curr_pre.next;
        //i位置的下一个元素
        Node curr_next = curr.next;
        //让i位置的前一个结点的next指向i位置的下一个节点
        curr_pre.next = curr_next;
        //让i位置的下一个结点的pre指向i位置的上一个节点
        curr_next.pre = curr_pre;
        N--;
        return curr.item;
    }
    //获取第一个元素
    public T getFirst(){
        if (isEmpty()){
            return null;
        }
        return head.next.item;
    }
    //获取最后一个元素
    public T getLast(){
        if (isEmpty()){
            return null;
        }
        return last.item;
    }

    @Override
    public Iterator<T> iterator() {
        return new TIterator();
    }
    private class TIterator implements Iterator{
        private Node n = head;
        @Override
        public boolean hasNext() {
            return n.next!=null;
        }

        @Override
        public Object next() {
            n = n.next;//必须这样写，每次n都记录为最新的
            return n.item;
        }
    }

}

//测试代码
class TestToWayLinkList {
    public static void main(String[] args) throws Exception {
        ToWayLinkList<String> list = new ToWayLinkList<>();
        list.insert("乔峰");
        list.insert("虚竹");
        list.insert("段誉");
        list.insert(1,"鸠摩智");
        list.insert(3,"叶二娘");
        for (String str : list) {
            System.out.println(str);
        }
        System.out.println("----------------------");
        String tow = list.get(2);
        System.out.println(tow);
        System.out.println("-------------------------");
        String remove = list.remove(3);
        System.out.println(remove);
        System.out.println(list.length());
        System.out.println("--------------------");
        System.out.println(list.getFirst());
        System.out.println(list.getLast());
    }
}
