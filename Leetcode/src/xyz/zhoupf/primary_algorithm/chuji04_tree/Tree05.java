package xyz.zhoupf.primary_algorithm.chuji04_tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 将有序数组转换为二叉搜索树
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 给定有序数组: [-10,-3,0,5,9],
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 */
public class Tree05 {

    public static void main(String[] args) {

    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    //方法一：递归实现
    public TreeNode helper(int[] nums, int left, int right) {
        //如果左边索引大于右边索引，表明
        if (left > right) return null;

        // 总是选择中间位置左边的数字作为根节点
        int mid = (left + right) / 2;
        //第一次循环：mid=(0+4)/2=2, left=0, right=4;--->root.left: left=0, right=1; root.right: left=3, right=4;
        //第二次循环：root.left: mid=(0+1)/2=0; root.left.left: left=0, right=0-1=-1; root.left.right: left:1 right=1
        //第三次循环：root.left.right: mid=(1+1)/2=1 root.left.right.left=1 root.letf.right.right=0小于左值返回null，root.left.right

        TreeNode root = new TreeNode(mid);
        root.left = helper(nums, left, mid - 1);
        //1，0。此时左边索引大于右边，即根节点没有右节点了，即右节点为空。
        root.right = helper(nums, mid + 1, right);
        return root;
    }

    //实现二：队列实现
    public TreeNode sortedArrayToBST01(int[] num) {
        if (num.length == 0)
            return null;
        Queue<int[]> rangeQueue = new LinkedList<>();
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        int lo = 0;
        int hi = num.length - 1;
        int mid = (lo + hi) >> 1;
        TreeNode node = new TreeNode(num[mid]);
        rangeQueue.add(new int[]{lo, mid - 1});
        rangeQueue.add(new int[]{mid + 1, hi});
        nodeQueue.add(node);
        nodeQueue.add(node);
        while (!rangeQueue.isEmpty()) {
            int[] range = rangeQueue.poll();
            TreeNode currentNode = nodeQueue.poll();
            lo = range[0];
            hi = range[1];
            if (lo > hi) {
                continue;
            }
            mid = (lo + hi) >> 1;
            int midValue = num[mid];
            TreeNode newNode = new TreeNode(midValue);
            if (midValue > currentNode.val)
                currentNode.right = newNode;
            else
                currentNode.left = newNode;
            if (lo < hi) {
                rangeQueue.add(new int[]{lo, mid - 1});
                rangeQueue.add(new int[]{mid + 1, hi});
                nodeQueue.add(newNode);
                nodeQueue.add(newNode);
            }
        }
        return node;
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
