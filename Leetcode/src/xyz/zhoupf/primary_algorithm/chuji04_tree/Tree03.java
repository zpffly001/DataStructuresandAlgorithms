package xyz.zhoupf.primary_algorithm.chuji04_tree;

import java.util.LinkedList;

/**
 * 对称二叉树
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 */
public class Tree03 {

    public static void main(String[] args) {
    }

    //方法一：递归实现
    public static boolean isSymmetric(TreeNode root) {
        if (root == null){
            return true;
        }
        //调用递归函数，比较左节点，右节点
        return dfs(root.left, root.right);
    }
    public static boolean dfs(TreeNode left, TreeNode right){
        //递归的终止条件是两个节点都为空
        //或者两个节点中有一个为空
        //或者两个节点的值不相等
        if (left == null && right == null){
            return true;
        }
        if (left == null || right == null){
            return false;
        }
        if (left.val != right.val){
            return false;
        }
        //再递归的比较左节点的左孩子和右节点的右孩子,以及比较左节点的右孩子和右节点的左孩子.
        return dfs(left.left, right.right) && dfs(left.right, right.left);
    }

    //方法二：队列实现
    public boolean isSymmetric01(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }

        //用队列保存节点
        LinkedList<TreeNode> queue = new LinkedList<>();
        //将根节点的左右孩子放到队列中
        queue.add(root.left);
        queue.add(root.right);
        while (queue.size() > 0){
            //从队列中取出两个节点，再比较这两个节点(可定是两个左右节点)
            TreeNode left = queue.removeFirst();
            TreeNode right = queue.removeFirst();
            if (left == null && right == null) continue;
            if (left==null || right==null) return false;
            if (left.val != right.val) return false;
            //将左节点的左孩子， 右节点的右孩子放入队列
            queue.add(left.left);
            queue.add(right.right);
            //将左节点的右孩子，右节点的左孩子放入队列
            queue.add(left.right);
            queue.add(right.left);
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
