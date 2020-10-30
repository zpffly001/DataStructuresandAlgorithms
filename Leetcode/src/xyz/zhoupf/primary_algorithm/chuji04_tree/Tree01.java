package xyz.zhoupf.primary_algorithm.chuji04_tree;

import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树的最大深度
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 */
public class Tree01 {

    public static void main(String[] args) {
        TreeNode<String, String> root = new TreeNode<String, String>("3", "1");
        TreeNode<String, String> left9 = new TreeNode<String, String>("9", "1");
        TreeNode<String, String> right2 = new TreeNode<String, String>("2", "1");
        TreeNode<String, String> right1 = new TreeNode<String, String>("1", "1");
        TreeNode<String, String> right7 = new TreeNode<String, String>("7", "1");
        root.left = left9;
        left9.right = null;
        left9.left = null;
        root.right = right2;
        right2.left = right1;
        right1.right = null;
        right1.left = null;
        right2.right = right7;
        right7.right = null;
        right7.left = null;
        System.out.println(maxDepth(root));
        System.out.println(maxDepth01(root));
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        //初始queue，先把根节点root放入
        List<TreeNode> queue = new LinkedList() {{ add(root); }}, tmp;
        int res = 0;
        while (!queue.isEmpty()){
            tmp = new LinkedList<>();
            for (TreeNode node : queue) {
                if (node.left != null) tmp.add(node.left);
                if(node.right != null) tmp.add(node.right);
            }
            queue = tmp;
            res++;
        }
        return res;
    }

    public static int maxDepth01(TreeNode root) {
        if (root == null) return 0;
        LinkedList<TreeNode> queue = new LinkedList() {{ add(root); }}, tmp;
        int res = 0;
        while (!queue.isEmpty()){
            tmp = new LinkedList<>();
            for (TreeNode node : queue) {
                if (node.left != null) tmp.add(node.left);
                if (node.right != null) tmp.add(node.right);
            }
            queue = tmp;
            res++;
        }
        return res;
    }


    private static class TreeNode<Key, Value>{
        //存储键
        private Key key;
        //存储值
        private Value value;
        //记录左子结点
        private TreeNode left;
        //记录右子结点
        private TreeNode right;

        public TreeNode(Key key, Value value) {
            this.key = key;
            this.value = value;
//            this.left = left;
//            this.right = right;
        }
    }

}
