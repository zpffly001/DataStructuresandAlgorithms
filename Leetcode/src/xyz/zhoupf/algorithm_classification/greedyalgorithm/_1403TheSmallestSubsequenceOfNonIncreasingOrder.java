package xyz.zhoupf.algorithm_classification.greedyalgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1403. 非递增顺序的最小子序列
 * 给你一个数组 nums，请你从中抽取一个子序列，满足该子序列的元素之和 严格 大于未包含在该子序列中的各元素之和。
 *
 * 如果存在多个解决方案，只需返回 长度最小 的子序列。如果仍然有多个解决方案，则返回 元素之和最大 的子序列。
 *
 * 与子数组不同的地方在于，「数组的子序列」不强调元素在原数组中的连续性，也就是说，它可以通过从数组中分离一些（也可能不分离）元素得到。
 *
 * 注意，题目数据保证满足所有约束条件的解决方案是 唯一 的。同时，返回的答案应当按 非递增顺序 排列。
 示例 1：

 输入：nums = [4,3,10,9,8]
 输出：[10,9]
 解释：子序列 [10,9] 和 [10,8] 是最小的、满足元素之和大于其他各元素之和的子序列。但是 [10,9] 的元素之和最大。 
 */
public class _1403TheSmallestSubsequenceOfNonIncreasingOrder {

    //解法，先从大到小排序，再从后往前便利，直到后面数之和大于前面的。返回
    public List<Integer> minSubsequence01(int[] nums) {
        List<Integer> res = new ArrayList<>();
        Arrays.sort(nums);
        int left = 0, sum = 0;
        for (int num : nums) {
            left += num;
        }
        //因为排过序的数组是从小到大排序的。因此要从后往前遍历
        for (int i = nums.length - 1; i >= 0; i--){
            res.add(nums[i]);
            sum += nums[i];
            left -= nums[i];
            if (sum > left){
                break;
            }
        }
        return res;
    }

}
