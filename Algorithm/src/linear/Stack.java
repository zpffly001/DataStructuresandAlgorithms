package linear;

import java.util.Iterator;

public class Stack<T> implements Iterable<T>{

    //记录首结点
    private Node head;
    //栈中元素的个数
    private int N;

    public Stack() {
        head = new Node(null,null);
        N=0;
    }
    //判断当前栈中元素个数是否为0
    public boolean isEmpty(){
        return N==0;
    }
    //把t元素压入栈
    public void push(T t) {
        //找到首节点指向的第一个节点
        Node oldFirst = head.next;
        //创建新节点
        Node newNode = new Node(t, null);
        //让首节点指向新节点
        head.next = newNode;
        //让新节点指向原来的第一个节点(因为入栈了一个元素，原来的第一个节点变成了现在的第二个节点)
        newNode.next = oldFirst;
        //元素个数+1
        N++;
    }
    //弹出栈顶元素
    public T pop(){
        //找到头节点指向的第一个节点(就是出栈前的第一个存储数据的节点)
        Node oldFirst = head.next;
        //安全性校验，要不然会出现空指针
        if (oldFirst==null){
            return null;
        }
        //让头节点指向原来的第一个节点的下一个节点(因为出栈，第一个节点要删除)
        head.next = oldFirst.next;
        //元素个数-1
        N--;
        return oldFirst.item;
    }
    //获取栈中元素的个数
    public int size(){
        return N;
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
class TestStack {
    public static void main(String[] args) throws Exception {
        Stack<String> stack = new Stack<>();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");
        for (String str : stack) {
            System.out.print(str+" ");
        }
        System.out.println("-----------------------------");
        String result = stack.pop();
        System.out.println("弹出了元素："+result);
        System.out.println("剩余元素的个数" + stack.size());
    }
}

//测试括号匹配问题
class BracketsMatch {
    public static void main(String[] args) {
        String str = "(上海(长安)())";
        boolean match = isMatch(str);
        System.out.println(str+"中的括号是否匹配："+match);
    }
    /**
     * 判断str中的括号是否匹配
     * @param str 括号组成的字符串
     * @return 如果匹配，返回true，如果不匹配，返回false
     */
    public static boolean isMatch(String str){
        //1.创建一个栈用来存储左括号
        Stack<String> chars = new Stack<>();
        //2.从左往右遍历字符串，拿到每一个字符
        for (int i=0;i<str.length();i++){
            String currChar = str.charAt(i) + "";
            //3.判断该字符是不是左括号，如果是，放入栈中存储
            if ("(".equals(currChar)){
                chars.push(currChar);
            }else if (")".equals(currChar)){//4.判断该字符是不是右括号，如果不是，继续下一次循环
                //5.如果该字符是右括号，则从栈中弹出一个元素t；
                String pop = chars.pop();
                //6.判断元素t是否为null，如果不是，则证明有对应的左括号，如果不是，则证明没有对应的左括号
                if (pop == null){
                    return false;
                }
            }
        }
        //7.循环结束后，判断栈中还有没有剩余的左括号，如果有，则不匹配，如果没有，则匹配
        if (chars.size() == 0){
            return true;
        }else {
            return false;
        }
    }
}

//使用栈求逆波兰表达式的结果
class ReversePolishNotation {
    public static void main(String[] args) {
        //中缀表达式3*（17-15）+18/6的逆波兰表达式如下
        String[] notation = {"3", "17", "15", "-", "*","18", "6","/","+"};
        int result = caculate(notation);
        System.out.println("逆波兰表达式的结果为："+result);
    }
    /**
     * @param notaion 逆波兰表达式的数组表示方式
     * @return 逆波兰表达式的计算结果
     */
    public static int caculate(String[] notaion){

        //1.创建一个栈对象oprands存储操作数
        Stack<Integer> oprands = new Stack<>();
        //2.从左往右遍历逆波兰表达式，得到每一个字符串
        for (int i = 0; i < notaion.length; i++) {
            String curr = notaion[i];
            //3.判断该字符串是不是运算符，如果不是，把该该操作数压入oprands栈中
            Integer o1;
            Integer o2;
            Integer result;
            switch (curr){
                case "+":
                    //4.如果是运算符，则从oprands栈中弹出两个操作数o1,o2
                    o1 = oprands.pop();
                    o2 = oprands.pop();
                    //5.使用该运算符计算o1和o2，得到结果result
                    result = o2 + o1;
                    //6.把该结果压入oprands栈中
                    oprands.push(result);
                    break;
                case "-":
                    o1 = oprands.pop();
                    o2 = oprands.pop();
                    //注意，栈是先进后出，第一个弹出的数01，在02的后面，因此要用02-01
                    result = o2 - o1;
                    oprands.push(result);
                    break;
                case "*":
                    o1 = oprands.pop();
                    o2 = oprands.pop();
                    result = o2 * o1;
                    oprands.push(result);
                    break;
                case "/":
                    o1 = oprands.pop();
                    o2 = oprands.pop();
                    //注意，栈是先进后出，第一个弹出的数01，在02的后面，因此要用02/01
                    result = o2 / o1;
                    oprands.push(result);
                    break;
                default:
                    //如果不符合上述四个条件，则该字符串为数字，要入栈
                    oprands.push(Integer.parseInt(curr));
                    break;
            }
        }

        //7.遍历结束后，拿出栈中最终的结果返回
        Integer result = oprands.pop();
        return result;
    }
}