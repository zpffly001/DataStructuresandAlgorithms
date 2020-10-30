package xyz.zhoupf.top100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * 示例：给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 */
public class _15SumOfThreeNumbers {

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList();
        int len = nums.length;
        if (nums == null || len < 3) return ans;
        //给数组排序
        Arrays.sort(nums);
        for (int i = 0; i < len; i++) {
            //因为nums[i]总是最左边的数，如果最左边的数都大于0，那么和也肯定大于0
            if (nums[i] > 0) break;
            //这里如果nums[i] == nums[i - 1]，则跳过i，去重
            //注意这里我们要求的是i层循环不能重复，并不是说nums[left]或nums[right]不能等于nums[i](我想通了，但是下次再看时，我如果看不懂，但是我要想起，我之前看懂了)
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int left = i+1;
            int right = len-1;
            //进入这里就表明nums[i]!=nums[i - 1],因此我们只关心左右指针
            while (left < right){
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0){
                    //如果三者和为0，则符合条件，加入ans
                    ans.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    //如果当前左指针和下一个左指针相同，那么重复跳过(因为这时i和右指针并没动)
                    while (left < right && nums[left] == nums[left + 1]) left++;// 去重
                    while (left < right && nums[right] == nums[right - 1]) right--;// 去重
                    //这里必须变两个数，因为你只变一个数，下次为0, 其实数和上次一样
                    left++;
                    right++;
                }
                //如果和大于0，则把右指针往左移动，即变小
                else if (sum > 0) right--;
                else if (sum < 0) left++;
            }
        }
        return ans;
    }

}
/*
* 算法流程：
特判，对于数组长度 nn，如果数组为 nullnull 或者数组长度小于 33，返回 [][]。
对数组进行排序。
遍历排序后数组：
若 nums[i]>0nums[i]>0：因为已经排序好，所以后面不可能有三个数加和等于 00，直接返回结果。
对于重复元素：跳过，避免出现重复解
令左指针 L=i+1L=i+1，右指针 R=n-1R=n−1，当 L<RL<R 时，执行循环：
当 nums[i]+nums[L]+nums[R]==0nums[i]+nums[L]+nums[R]==0，执行循环，判断左界和右界是否和下一位置重复，去除重复解。并同时将 L,RL,R 移到下一位置，寻找新的解
若和大于 00，说明 nums[R]nums[R] 太大，RR 左移
若和小于 00，说明 nums[L]nums[L] 太小，LL 右移
*/
class Solution {
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList();
        int len = nums.length;
        if(nums == null || len < 3) return ans;
        Arrays.sort(nums); // 排序
        for (int i = 0; i < len ; i++) {
            if(nums[i] > 0) break; // 如果当前数字大于0，则三数之和一定大于0，所以结束循环
            if(i > 0 && nums[i] == nums[i-1]) continue; // 去重
            int L = i+1;
            int R = len-1;
            while(L < R){
                int sum = nums[i] + nums[L] + nums[R];
                if(sum == 0){
                    ans.add(Arrays.asList(nums[i],nums[L],nums[R]));
                    while (L<R && nums[L] == nums[L+1]) L++; // 去重
                    while (L<R && nums[R] == nums[R-1]) R--; // 去重
                    L++;
                    R--;
                }
                else if (sum < 0) L++;
                else if (sum > 0) R--;
            }
        }
        return ans;
    }
}