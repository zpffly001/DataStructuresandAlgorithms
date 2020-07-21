package tree;

import linear.Queue;

public class BinaryTree<Key extends Comparable<Key>, Value> {

    private Node root;
    private int N;

    private class Node{
        //存储键
        public Key key;
        //存储值
        public Value value;
        //记录左子结点
        public Node left;
        //记录右子结点
        public Node right;
        public Node(Key key, Value value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

        //获取树中元素的个数
        public int size() {
            return N;
        }
        //向树中添加元素key-value
        public void put(Key key, Value value) {
            root = put(root, key, value);//插入的时候先从根节点root入手，插入后会将插入后的树返还
        }
        //向指定的树x中添加key-value,并返回添加元素后新的树
        public Node put(Node x, Key key, Value value){
            //如果x子树为空
            if (x == null){
                //个数+1
                N++;
                //如果刚开始整个书都为空，那么就创建一个根节点，让root引用
                return new Node(key, value, null, null);
            }

            //如果树不为空
            //比较传入的key和x树的大小，因为传入的key,value会组成新节点，插入在x树的某个地方（可能直接插入左边或右边，也可能在x书的子树上）
            //因此我们把传入的变量key看作新节点的key，而x.key看作当前节点的key(因为新节点的插入要以当前x树为参考)
            int cmp = key.compareTo(x.key);
            if (cmp > 0){
                //新结点的key大于当前结点的key，继续找当前结点的右子结点
                //这里是递归调用插入，即如果key大于x树的key，则新节点建立在x树的右边，因此递归调用put(x.right, key, value)
                //可以看出如果x.right为空，则新节点直接成为x.right从而补全空缺，如果x.right不为空，还会自动递归调用put()直到找到合适的地方
                //新节点插入后，层层递归返回插入新节点后改变了的树，从而最后递归的更新整个树
                x.right = put(x.right, key, value);
            }else if (cmp < 0){
                x.left = put(x.left, key, value);
            }else {
                //新结点的key等于当前结点的key，把当前结点的value进行替换
                x.value = value;
            }

            //此时返回的就是插入新节点后更新过的树
            return x;
        }

        //查询树中指定key对应的value
        public Value get(Key key) {
            //调用重载的方法重整个树中查找
            return get(root, key);
        }
        //从指定的树x中，查找key对应的值
        /**
         * 本次递归有两种情况，一种是递归到叶子节点还没有找到，则返回null
         * 第二种是，递归的找到了对应key的节点。然后返回节点的value，就是我们要查询的值
         * @param x
         * @param key
         * @return
         */
        public Value get(Node x, Key key) {
            /*
            这里的x可不一定指的是root节点，虽然第一个传入的是root节点，但是我们又在笨方法中递归的调用了自己
            因此，这里的x只是递归的出口节点，即没有找到，这只是没有找到情况下的递归的出口
             */
            if(x == null){
                return null;
            }
            int cmp = key.compareTo(x.key);
            if (cmp > 0) {
                //如果要查询的key大于当前结点的key，则继续找当前结点的右子结点；
                return get(x.right, key);
            } else if (cmp < 0) {
                //如果要查询的key小于当前结点的key，则继续找当前结点的左子结点；
                return get(x.left, key);
            } else {
                //如果要查询的key等于当前结点的key，则树中返回当前结点的value。
                //找到了，就返回，这是递归情况下找到值的出口
                return x.value;
            }
        }
        //删除树中key对应的value
        public void delete(Key key) {
            root = delete(root, key);
        }
        //删除指定树x中的key对应的value，并返回删除后的新树
        public Node delete(Node x, Key key) {

            if (x == null) {
                return null;
            }

            int cmp = key.compareTo(x.key);
            if (cmp > 0) {
                //新结点的key大于当前结点的key，继续找当前结点的右子结点
                //如果找到了，进行了删除操作，右子树就发生了变化，因此，把变化后的右子树返回来，我们把新树成为x树的右节点
                x.right = delete(x.right, key);
            } else if (cmp < 0) {
                //新结点的key小于当前结点的key，继续找当前结点的左子结点
                //如果找到了，进行了删除操作，左子树就发生了变化，因此，把变化后的左子树返回来，我们把新树成为x树的左节点
                x.left = delete(x.left, key);
            } else {
                //个数-1
                N--;

                //新结点的key等于当前结点的key,当前x节点就是要删除的结点
                //1.如果当前要删除的结点x的右子树不存在，则直接返回当前结点的左子结点
                if (x.right == null){
                    return x.left;
                }
                //2.如果当前要删除的结点x的左子树不存在，则直接返回当前结点的右子结点
                if (x.left == null){
                    return  x.right;
                }
                //3.当前结点的左右子树都存在
                //3.1找到右子树中最小的结点
                Node minNode = x.right;//记录右子树中最小的节点，初始先赋值为x的右子树，然后一直找子树的右节点，直到找到右子树的最小节点
                //循环遍历，直到找到x的右子树中的最小节点
                while (minNode.left!=null){
                    minNode = minNode.left;
                }
                //因为最小节点，要去代替被删除的x节点，因此把最小节点在右子树中除名
                Node n = x.right;
                while (n.left != null){
                    //如果当前节点的左节点的左节点为空，则表明当前节点的左节点为最左节点，即是我们要重右子树中除名的节点，因此把当前节点的左节点赋值为空
                    if (n.left.left == null){
                        n.left = null;
                    }else {
                        n = n.left;//变换节点即可
                    }
                }

                //3.3让被删除结点的左子树称为最小结点minNode的左子树，让被删除结点的右子树成为最小结点minNode的右子树
                minNode.left = x.left;
                minNode.right = x.right;
                //3.4让被删除结点的父节点指向最小结点minNode
                x = minNode;
            }
            return x;
        }
    //找出整个树中最小的键
    public Key min(){
        return min(root).key;
    }
    //找出指定树x中最小的键所在的结点
    private Node min(Node x){
        if (x.left!=null){
            return min(x.left);
        }else {
            return x;
        }
//        //方法二
//        while (x.left!=null){
//            x = x.left;
//        }
//        return x;
    }

    //找出整个树中最大的键
    public Key max(){
        return max(root).key;
    }
    //找出指定树x中最大键所在的结点
    public Node max(Node x){
        if (x.right!=null){
            return max(x.right);
        }else{
            return x;
        }
    }

    //使用前序遍历，获取整个树中的所有键
    public Queue<Key> preErgodic(){
        Queue<Key> keys = new Queue<>();
        preErgodic(root, keys);
        return keys;
    }
    //使用前序遍历，把指定树x中的所有键放入到keys队列中
    private void preErgodic(Node x,Queue<Key> keys){
        if (x==null){
            return;
        }
        //1.把当前结点的key放入到队列中;
        keys.enqueue(x.key);

        //2.找到当前结点的左子树，如果不为空，递归遍历左子树
        if (x.left!=null){
            preErgodic(x.left, keys);
        }

        //3.找到当前结点的右子树，如果不为空，递归遍历右子树
        if (x.right!=null){
            preErgodic(x.right,keys);
        }
    }

    //使用中序遍历，获取整个树中的所有键
    public Queue<Key> midErgodic() {
        Queue<Key> keys = new Queue<>();
        midErgodic(root, keys);
        return keys;
    }
    //使用中序遍历，把指定树x中的所有键放入到keys队列中
    private void midErgodic(Node x,Queue<Key> keys){
        if (x==null){
            return;
        }
        //1.找到当前结点的左子树，如果不为空，递归遍历左子树
        if (x.left!=null){
            midErgodic(x.left, keys);
        }
        //2.把当前结点的key放入到队列中;
        keys.enqueue(x.key);
        //3.找到当前结点的右子树，如果不为空，递归遍历右子树
        if (x.right!=null){
            midErgodic(x.right, keys);
        }
    }

    //使用后序遍历，获取整个树中的所有键
    public Queue<Key> afterErgodic(){
        Queue<Key> keys = new Queue<>();
        afterErgodic(root,keys);
        return keys;
    }
    //使用后序遍历，把指定树x中的所有键放入到keys队列中
    private void afterErgodic(Node x,Queue<Key> keys){
        if (x==null){
            return;
        }
        //1.通过递归，找到当前结点的左子树，如果不为空，递归遍历左子树
        if (x.left!=null){
            afterErgodic(x.left, keys);
        }
        //2.通过递归，找到当前结点的右子树，如果不为空，递归遍历右子树
        if (x.right!=null){
            afterErgodic(x.right, keys);
        }
        //3.通过递归，把当前结点的key放入到队列中;
        keys.enqueue(x.key);
    }

    //使用层序遍历得到树中所有的键
    public Queue<Key> layerErgodic(){
        //定义两个队列，分别存储树中的键和节点
        Queue<Key> keys = new Queue<>();
        Queue<Node> nodes = new Queue<>();

        //默认，在队列中放入根节点
        nodes.enqueue(root);
        //如果存储节点的队列不为空，则一直循环
        while (!nodes.isEmpty()){
            //从队列中弹出一个节点，把该节点的键key放入队列中
            Node n = nodes.dequeue();
            keys.enqueue(n.key);
            //判断当前节点还有没有左子节点，如果有则放入Nodes中
            if (n.left!=null){
                nodes.enqueue(n.left);
            }
            //判断当前节点还有没有右子节点，如果有放入nodes中
            if (n.right!=null){
                nodes.enqueue(n.right);
            }
        }
        return keys;
    }

    //计算整个树的最大深度
    public int maxDepth(){
            return maxDepth(root);
    }
    //计算指定树x的最大深度
    private int maxDepth(Node x){
        //1.如果根结点为空，则最大深度为0；
        if (x == null){
            return 0;
        }
        int max = 0;
        int maxL = 0;
        int maxR = 0;
        //2.计算左子树的最大深度；
        if (x.left!=null){
            maxL = maxDepth(x.left);
        }
        //3.计算右子树的最大深度；
        if (x.right!=null){
            maxR = maxDepth(x.right);
        }

        //4，当前递归到的树的最大深度=左子树的最大深度和右子树的最大深度的较大者+1
        max = maxL > maxR ? maxL+1 :maxR+1;
        return max;
    }

}

//测试二叉查找树
class TestBinaryTree {
    public static void main(String[] args) throws Exception {
        BinaryTree<Integer, String> binaryTree = new BinaryTree<>();
        binaryTree.put(1, "张三");
        binaryTree.put(2, "李四");
        binaryTree.put(3, "王五");
        System.out.println("插入完毕后元素的个数：" + binaryTree.size());

        System.out.println("键2对应的元素是" + binaryTree.get(2));

        binaryTree.put(2, "哈士奇");
        System.out.println("修改后键2对应的元素是" + binaryTree.get(2));

        binaryTree.delete(3);
        System.out.println("删除后元素的个数是" + binaryTree.size());
        System.out.println("删除后键3的值为" + binaryTree.get(3));

        System.out.println("最小键所在的节点" + binaryTree.min());

        System.out.println("最大键所在的节点" + binaryTree.max());
    }
}

//测试二叉查找树的前序遍历方法
class TestBinaryTreeErgodic {

//    //前序遍历
//    public static void main(String[] args) throws Exception {
//        BinaryTree<String, String> bt = new BinaryTree<>();
//        bt.put("E", "5");
//        bt.put("B", "2");
//        bt.put("G", "7");
//        bt.put("A", "1");
//        bt.put("D", "4");
//        bt.put("F", "6");
//        bt.put("H", "8");
//        bt.put("C", "3");
//        Queue<String> queue = bt.preErgodic();
//        for (String key : queue) {
//            System.out.println(key+"="+bt.get(key));
//        }
//    }

//    //中序遍历
//    public static void main(String[] args) throws Exception {
//        BinaryTree<String, String> bt = new BinaryTree<>();
//        bt.put("E", "5");
//        bt.put("B", "2");
//        bt.put("G", "7");
//        bt.put("A", "1");
//        bt.put("D", "4");
//        bt.put("F", "6");
//        bt.put("H", "8");
//        bt.put("C", "3");
//        Queue<String> queue = bt.midErgodic();
//        for (String key : queue) {
//            System.out.println(key+"="+bt.get(key));
//        }
//    }

//    //后序遍历
//    public static void main(String[] args) throws Exception {
//        BinaryTree<String, String> bt = new BinaryTree<>();
//        bt.put("E", "5");
//        bt.put("B", "2");
//        bt.put("G", "7");
//        bt.put("A", "1");
//        bt.put("D", "4");
//        bt.put("F", "6");
//        bt.put("H", "8");
//        bt.put("C", "3");
//        Queue<String> queue = bt.afterErgodic();
//        for (String key : queue) {
//            System.out.println(key+"="+bt.get(key));
//        }
//    }

//    //层序遍历
//    public static void main(String[] args) throws Exception {
//        BinaryTree<String, String> bt = new BinaryTree<>();
//        bt.put("E", "5");
//        bt.put("B", "2");
//        bt.put("G", "7");
//        bt.put("A", "1");
//        bt.put("D", "4");
//        bt.put("F", "6");
//        bt.put("H", "8");
//        bt.put("C", "3");
//        Queue<String> queue = bt.layerErgodic();
//        for (String key : queue) {
//            System.out.println(key+"="+bt.get(key));
//        }
//    }

    //树的最大深度
    public static void main(String[] args) throws Exception {
        BinaryTree<String, String> bt = new BinaryTree<>();
        bt.put("E", "5");
        bt.put("B", "2");
        bt.put("G", "7");
        bt.put("A", "1");
        bt.put("D", "4");
        bt.put("F", "6");
        bt.put("H", "8");
        bt.put("C", "3");
        System.out.println(bt.maxDepth());
    }
}