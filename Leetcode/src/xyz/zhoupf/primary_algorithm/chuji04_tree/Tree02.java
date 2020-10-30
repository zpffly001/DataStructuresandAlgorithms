package xyz.zhoupf.primary_algorithm.chuji04_tree;

import java.util.Stack;


public class Tree02 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode left1 = new TreeNode(1);
        TreeNode right1 = new TreeNode(4);
        TreeNode rleft2 = new TreeNode(3);
        TreeNode rright2 = new TreeNode(6);

        root.left = left1;
        left1.left = null;
        left1.right = null;
        root.right = right1;
        right1.left = rleft2;
        right1.right = rright2;
        rleft2.left = null;
        rleft2.right = null;
        rright2.left = null;
        rright2.right = null;

        System.out.println(isValidBST(root));
        System.out.println(isValidBST01(root));
    }

    public static boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        double inorder = - Double.MAX_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            // 如果中序遍历得到的节点的值小于等于前一个 inorder，说明不是二叉搜索树
            if (root.val <= inorder) return false;
            inorder = root.val;
            root = root.right;
        }
        return true;
    }

    public static boolean isValidBST01(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        //初始取Double类型的最小值，保证inorder能保存第一个相比较的结点的值
        double inorder = -Double.MAX_VALUE;

        while (!stack.isEmpty() || root != null){
            while (root != null){
                stack.push(root);
                //一直取左值压入栈中，直到取到当前节点的最左叶子子节点，然后root为空，跳出循环
                root = root.left;
            }
            //每次从栈中取出一个值，赋值给root
            root = stack.pop();

            // 如果中序遍历得到的节点的值小于等于前一个 inorder，说明不是二叉搜索树
            if (root.val <= inorder) return false;
            inorder = root.val;
            //第一次循环，因为root本来就是最左边的那个节点了，且是叶子节点，因此root.right为空，又赋值给了root，说明root为空
            //然后在此进入循环条件判断，虽然root为空，但是栈stack不为空，因此仍然可以进入循环。
            //此时root为空不会进入子循环进行压栈操作(此处栈stack用的巧妙，如果当前root没有右节点，进入下次循环，root的值，是栈stack出栈的值赋予的，因次，即当前root就是上次root的父节点了，在判断有没有右节点，然后与inorder比较，如果小于inorder则返回false，如果不是，则继续循环下去)
            root = root.right;

        }

        return true;
    }

    private static class TreeNode{
        public Integer val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(Integer val){
            this.val = val;
        }
    }

}
