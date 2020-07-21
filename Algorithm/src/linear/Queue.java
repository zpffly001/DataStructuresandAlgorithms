package linear;

import java.util.Iterator;

public class Queue<T> implements Iterable<T>{

    //记录首结点
    private Node head;
    //记录最后一个结点
    private Node last;
    //栈中元素的个数
    private int N;

    public Queue(){
        this.head = new Node(null, null);
        this.last = null;
        this.N = 0;
    }
    //判断队列是否为空
    public boolean isEmpty(){
        return N==0;
    }
    //返回队列中元素的个数
    public int size(){
        return N;
    }
    //向队列中插入元素t
    public void enqueue(T t){
        //当尾节点为空，即链表还没有插入数据
        if (last == null){
            Node newNode = new Node(t, null);
            head.next = newNode;
            last = newNode;
        }else {
            Node oldLast = last;
            Node newNode = new Node(t, null);
            oldLast.next = newNode;
            last = newNode;
        }
        //个数+1
        N++;
    }
    //从队列中拿出一个元素
    public T dequeue() {
        if (isEmpty()){
            return null;
        }

        Node oldFirst = head.next;
        head.next = oldFirst.next;
        N--;

        if (isEmpty()){
            last=null;
        }
        return oldFirst.item;
    }

    @Override
    public Iterator<T> iterator() {
        return new ITerator();
    }
    private class ITerator implements Iterator{

        Node n = head;
        @Override
        public boolean hasNext() {
            return n.next!=null;
        }

        @Override
        public Object next() {
            n = n.next;
            return n.item;
        }
    }

    private class Node{
        public T item;
        public Node next;
        public Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }
    }
}

//测试代码
class TestQueue {
    public static void main(String[] args) throws Exception {
        Queue<String> queue = new Queue<>();
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        queue.enqueue("d");
        for (String str : queue) {
            System.out.print(str+" ");
        }
        System.out.println("-----------------------------");
        String result = queue.dequeue();
        System.out.println("出列了元素："+result);
        String result1 = queue.dequeue();
        System.out.println("出列了元素："+result1);
        String result2 = queue.dequeue();
        System.out.println("出列了元素："+result2);
        String result3 = queue.dequeue();
        System.out.println("出列了元素："+result3);
        String result4 = queue.dequeue();
        System.out.println("出列了元素："+result4);
        System.out.println(queue.size());
    }
}