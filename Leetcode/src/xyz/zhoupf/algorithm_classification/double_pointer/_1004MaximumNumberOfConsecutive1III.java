package xyz.zhoupf.algorithm_classification.double_pointer;

/**
 * 最大连续1的个数 III
 * 给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。
 *
 * 返回仅包含 1 的最长（连续）子数组的长度。
 * 示例 1：输入：A = [1,1,1,0,0,0,1,1,1,1,0], K = 2   输出：6
 * 解释：
 * [1,1,1,0,0,1,1,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 6。
 */
public class _1004MaximumNumberOfConsecutive1III {

    //我们可以理解为，滑动窗口内最多有K个0，求滑动窗口最大的长度
    public int longestOnes(int[] A, int K) {
        //统计0的个数
        int count = 0;
        //滑动窗口区间左端点
        int left = 0;
        //滑动窗口区间右端点
        int right = 0;
        //最终结果，即最大窗口
        int res = 0;
        //滑动窗口表示的区间为[left,right)，左闭右开
        while (right < A.length){
            if (A[right++] == 0) ++count;
            //当窗口内0的个数超过k时候，开始收缩窗口
            while (count  > K){
                //如果刚滑出窗口的元素是0，则count--;
                if (A[left++] == 0)
                    --count;
            }
            //此时count<=K,保存窗口的最大宽度
            res = Math.max(res, right - left);
        }
        return res;
    }

}
