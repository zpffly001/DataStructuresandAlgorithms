package tree;

/**
 * 红黑树的实现
 * @param <Key>
 */
public class RedBlackTree<Key extends Comparable<Key>, Value> {

    //根节点
    private Node root;
    //记录树中元素的个数
    private int N;
    //红色链接
    private static final boolean RED = true;
    //黑色链接
    private static final boolean BLACK = false;

    //获取树中元素的个数
    public int size() {
        return N;
    }
    /**
     * 判断当前节点的父指向链接是否为红色
     *
     * @param x
     * @return
     */
    private boolean isRed(Node x) {
        //节点x为空，则表示空连接，则设置指向空节点的颜色为黑色
        if (x == null){
            return false;
        }
        return x.color == RED;
    }
    /**
     * 左旋转
     *旋转前：h节点的右子节点为x，即h节点在上方
     * 旋转后：x节点的左子节点为h，即x节点在上方
     * 原本x的左子节点成为了现在的左子节点h的右子节点
     * @param h
     * @return
     */
    private Node rotateLeft(Node h) {
        //找出当前结点h的右子结点，标识为x
        Node x = h.right;
        //让x的左子节点成为h的右子节点
        h.right = x.left;
        //让h节点成为x节点的左子节点
        x.left = h;
        //让x节点的color属性等于h节点的color属性
        x.color = h.color;
        //让h结点的color属性变为红色
        h.color = RED;
        return x;
    }
    /**
     * 右旋
     *旋转前：h节点的左子节点为x，即h节点在上方
     * 旋转后：x节点的右子节点为h，即x节点在上方，又因为右子节点不能为空色，因此x的两个红链接都要编程黑色
     * @param h
     * @return
     */
    private Node rotateRight(Node h) {
        //找出当前结点h的左子结点，标识为x
        Node x = h.left;
        //让x的右子节点成为h的左子节点
        h.left = x.right;
        //让h节点成为x节点的右子节点
        x.right = h;
        //让x节点的color属性等于h节点的color属性
        x.color = h.color;
        //让h结点的color属性变为红色
        h.color = RED;
        return x;
    }
    /**
     * 颜色反转,相当于完成拆分4-节点
     *
     * @param h
     */
    private void flipColors(Node h) {
        //当前结点的color属性值变为RED；
        h.color = RED;
        //当前结点的左右子结点的color属性值都变为黑色
        h.left.color = BLACK;
        h.right.color = BLACK;
    }
    /**
     * 在整个树上完成插入操作
     *
     * @param key
     * @param val
     */
    public void put(Key key, Value val) {
        //在root整个树上插入key-val
        root = put(root, key, val);
        //让根结点的颜色变为BLACK
        root.color = BLACK;
    }
    /**
     * 在指定树中，完成插入操作,并返回添加元素后新的树
     *
     * @param h
     * @param key
     * @param val
     */
    private Node put(Node h, Key key, Value val) {
        if (h == null){
            //标准的插入操作，和父结点用红链接相连
            N++;
            return new Node(key, val,null, null, RED);
        }
        //比较h节点的键和传入的key的大小，key大往右走，key小往左走
        int cmp = key.compareTo(h.key);
        if (cmp < 0){
            //继续寻找左子树插入
            h.left = put(h.left, key, val);
        }else if (cmp > 0) {
            //继续寻找右子树插入
            h.right = put(h.right, key, val);
        }else {
            //已经有相同的结点存在，修改节点的值；
            h.value = val;
        }

        //如果当前结点的右链接是红色，左链接是黑色，需要左旋
        if (isRed(h.right) && !isRed(h.left)){
            h = rotateLeft(h);
        }

        //如果当前结点的左子结点和左子结点的左子结点都是红色链接，则需要右旋
        if (isRed(h.left) && isRed(h.left.left)){
            h = rotateRight(h);
        }

        //如果当前结点的左链接和右链接都是红色，需要颜色变换
        if (isRed(h.left) && isRed(h.right)){
            flipColors(h);
        }

        //返回当前结点
        return h;
    }
    //根据key，从树中找出对应的值
    public Value get(Key key) {
        return get(root, key);
    }
    //从指定的树x中，查找key对应的值
    public Value get(Node x, Key key) {
        //递归，如果当前结点为空，则没有找到,返回null
        if (x == null){
            return null;
        }

        //比较当前结点的键和key
        int cmp = key.compareTo(x.key);
        if (cmp < 0){
            //继续往左子树中查找
            return get(x.left, key);
        }else if (cmp > 0){
            //继续往右子树中查找
            return get(x.right, key);
        }else{
            //找到了，并返回结点的值
            return x.value;
        }
    }

    //结点类
    private class Node{
        //存储键
        public Key key;
        //存储值
        public Value value;
        //记录左子结点
        public Node left;
        //记录右子结点
        public Node right;
        //由其父结点指向它的链接的颜色
        public boolean color;

        public Node(Key key, Value value, Node left, Node right, boolean color) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.color = color;
        }
    }
}


//测试代码
class TestRedBlackTree {
    public static void main(String[] args) throws Exception {
        RedBlackTree<Integer, String> bt = new RedBlackTree<>();
//        bt.put(4, "二哈");
//        bt.put(1, "张三");
//        bt.put(3, "李四");
//        bt.put(5, "王五");
//        System.out.println(bt.size());
//        bt.put(1,"老三");
//        System.out.println(bt.get(1));
//        System.out.println(bt.size());
//        System.out.println(bt.get(4));
//        System.out.println(bt.get(5));
//        bt.put(8,"老八");
//        System.out.println(bt.size());

        bt.put(1, "张三");
        bt.put(2, "李四");
        bt.put(3, "王五");
        System.out.println(bt.get(1));
        System.out.println(bt.get(2));
        System.out.println(bt.get(3));
    }
}