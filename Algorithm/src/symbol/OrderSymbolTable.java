package symbol;

public class OrderSymbolTable<Key extends Comparable<Key>,Value> {

    //记录首结点
    private Node head;
    //记录符号表中元素的个数
    private int N;
    public OrderSymbolTable() {
        head = new Node(null,null,null);
        N=0;
    }
    //获取符号表中键值对的个数
    public int size(){
        return N;
    }
    //往符号表中插入键值对
    public void put(Key key,Value value){
        //记录当前结点
        Node curr = head.next;
        //记录上一个结点
        Node curr_pre = head;

        //1.如果要插入的key大于当前结点的key，则一直寻找下一个结点,直到找到插入位置，然后跳出循环
        while (curr!=null && key.compareTo(curr.key) > 0){
            curr_pre = curr;//上一个节点指向当前节点
            curr = curr.next;//当前节点指向下一个节点，以此循环下去
        }

        //2.如果当前结点curr的key和将要插入的key一样，则替换
        if (curr!=null && curr.key.compareTo(key)==0){
            curr.value = value;//key一样，则进行值的替换
            return;
        }

        //跳出循环则表明找到了要插入的地方(而且派出了上面相等的情况)，curr节点的key就是比当前key大，即当前key的节点插入到curr前面
        //3.没有找到相同的key，把新结点插入到curr之前
        Node newNode = new Node(key, value, curr);//新建节点，并让新节点的.next指向curr
        curr_pre.next = newNode;//curr_pre不指向curr了，而是指向curr前面插入的新节点newNode
        N++;
    }
    //删除符号表中键为key的键值对
    public void delete(Key key){
        Node n = head;
        while (n.next!=null){
            if (key.equals(n.next.key)){//如果找到了要删除的key的节点
                //n.next为该节点的前一个节点，n.next.next为节点的后一个节点
                n.next = n.next.next;//删除该节点，即让该节点的前一个节点，指向该节点的后一个节点
                N--;//元素减1
                return;
            }
            n = n.next;//继续遍历下一个节点
        }
    }
    //从符号表中获取key对应的值
    public Value get(Key key){
        Node n = head;
        while (n.next!=null){
            n = n.next;
            if (key.equals(n.key)){
                return n.value;
            }
        }
        return null;
    }


    private class Node{
        //键
        public Key key;
        //值
        public Value value;
        //下一个结点
        public Node next;
        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}

//测试类
class TestOrderSymbolTable {
    public static void main(String[] args) throws Exception {
        OrderSymbolTable<Integer, String> bt = new OrderSymbolTable<>();
        bt.put(1, "张三");
        bt.put(2, "李四");
        bt.put(4, "赵六");
        bt.put(7, "田七");

        bt.put(3, "王五");

    }
}


