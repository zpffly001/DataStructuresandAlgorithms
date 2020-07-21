package tree;

import linear.Queue;

public class PageFolding {
    public static void main(String[] args) {
        //构建折痕树
        Node tree = createTree(2);
        //遍历折痕树，并打印
        printTree(tree);
    }

    //3.使用中序遍历，打印出树中所有结点的内容；
    private static void printTree(Node tree) {
        if (tree==null){
            return;
        }
        if (tree.left!=null){
            printTree(tree.left);
        }
        System.out.print(tree.item+",");
        if (tree.right!=null){
            printTree(tree.right);
        }
    }
    //2.构建深度为N的折痕树；
    private static Node createTree(int N) {
        //定义根节点的引用
        Node root = null;
        for (int i = 0; i < N; i++) {
            //i==0表明是第一次对折
            if (i==0){
                //初始化根节点的引用，因为第一次对折朝下，因此为down，而左右节点均为空
                root = new Node("down", null, null);
            }else {
                //2.如果不是第一次对折，就相当于给原有的节点添加左右子节点，其实形成了满二叉树，则使用队列保存根结点；
                //在形成满二叉树的过程中，都是自上到下，自左到右，依次给叶子节点添加他的左右子节点，因此使用层序遍历更合适
                //使用层序遍历，就需要一个辅助队列存储节点，而且默认要把根节点放入队列
                Queue<Node> queue = new Queue<>();
                queue.enqueue(root);

                //3,循环遍历队列
                while (!queue.isEmpty()){
                    //3.1从队列中拿出一个结点；
                    Node tmp = queue.dequeue();
                    //3.2如果这个结点的左子结点不为空，则把这个左子结点添加到队列中；
                    if (tmp.left!=null){
                        queue.enqueue(tmp.left);
                    }
                    //3.3如果这个结点的右子结点不为空，则把这个右子结点添加到队列中；
                    if (tmp.right!=null){
                        queue.enqueue(tmp.right);
                    }
                    //3.4判断当前结点的左子结点和右子结点都不为空，如果是，则需要为当前结点创建一个值为down的左子结点，一个值为up的右子结点。
                    if (tmp.left == null && tmp.right == null){
                        tmp.left = new Node("down", null, null);
                        tmp.right = new Node("up", null, null);
                    }
                }
            }
        }
        return root;
    }

    //1.定义结点类
    private static class Node{
        //存储结点元素
        String item;
        //左子结点
        Node left;
        //右子结点
        Node right;
        public Node(String item, Node left, Node right){
        this.item = item;
        this.left = left;
        this.right =right;
        }
    }
}
