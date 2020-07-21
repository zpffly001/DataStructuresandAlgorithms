package symbol;

public class SymbolTable<Key,Value> {

    //记录首结点
    private Node head;
    //记录符号表中元素的个数
    private int N;
    public SymbolTable() {
        head = new Node(null,null,null);
        N=0;
    }
    //获取符号表中键值对的个数
    public int size(){
        return N;
    }
    //往符号表中插入键值对
    public void put(Key key,Value value){
        //先从符号表中查找有没有键为key的键值对，如果有就直接替换值，然后结束
        Node n = head;
        while (n.next!=null){
            n = n.next;
            if (key.equals(n.key)){
                n.value = value;//把值替换了
                return;
            }
        }

        //除了循环，运行到这里，说明符号表中没有键为key的键值对
        Node oldFirst = head.next;
        Node newNode = new Node(key, value, oldFirst);
        head.next = newNode;
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
class TestSymbolTable {
    public static void main(String[] args) throws Exception {
        SymbolTable<String, String> symbolTable = new SymbolTable<>();
        symbolTable.put("1","乔峰");
        symbolTable.put("2","虚竹");
        symbolTable.put("3","段誉");
        System.out.println("插入完毕后，元素的个数：" + symbolTable.size());

        symbolTable.put("2","慕容复");
        System.out.println("替换完毕后，元素的个数：" + symbolTable.size());

        System.out.println("替换完毕后，键2对应的值为：" + symbolTable.get("2"));

        symbolTable.delete("2");
        System.out.println("删除完毕后，元素的个数：" + symbolTable.size());
    }
}


