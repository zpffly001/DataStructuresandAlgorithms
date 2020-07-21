package linear;

public class JosephTest {
    public static void main(String[] args) throws Exception {
        //1，构建循环链表
        Node<Integer> first = null;
        //记录前一个节点
        Node<Integer> pre = null;
        for (int i=1;i<=41;i++){
            //第一个元素
            if (i==1){
                first = new Node<>(i,null);
                pre = first;
                continue;//定义了第一个元素，并记录了第一个元素，因此可以跳出循环
            }

            //定义除了第一个元素外的其他元素
            Node<Integer> newNode = new Node<>(i, null);
            //下面两步，通过指针pre把41个节点连接起来，先形成一个链表
            pre.next = newNode;
            pre = newNode;

            //构建循环链表
            if (i==41){
                pre.next=first;
            }
        }


        //2，.使用count，记录当前的报数值
        int count = 0;

        //3,遍历循环链表
        //记录当前节点，第一次遍历默认从首节点开始
        Node<Integer> curr = first;
        //记录当前节点的上一个节点，这样我们有了当前节点和当前节点的上一个节点，那么单向链表就可以进行删除了
        Node<Integer> curr_pre = null;//默认为空，因为首节点没有上一个
        while (curr!=curr.next){
            //4.判断count的值，如果是3，则从链表中删除这个结点并打印结点的值，把count重置为0；
            count++;
            if (count == 3){
                //如果报数为3，则删除当前节点
                curr_pre.next = curr.next;//把当前节点的上一个节点的.next指向当前节点的.next(当前节点的下一个节点)
                //打印被删除的节点
                System.out.print(curr.item+",");
                count=0;
                curr = curr.next;//因为此时报数为3，当前节点要被删除了，因此只需要把当前节点后移就行，指向前一个节点的指针不变，只需要把next域指向被删除节点的下一个节点就行
            }else {
                curr_pre = curr;
                curr = curr.next;
            }
        }

        /*打印剩余的最后那个人*/
        System.out.println("最后剩余的：" + curr.item);
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
