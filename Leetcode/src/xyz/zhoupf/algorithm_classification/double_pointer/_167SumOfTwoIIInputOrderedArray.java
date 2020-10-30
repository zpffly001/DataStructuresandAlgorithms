package xyz.zhoupf.algorithm_classification.double_pointer;

import java.util.Arrays;

/**
 * 167. 两数之和 II - 输入有序数组
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 * 说明:
 * 返回的下标值（index1 和 index2）不是从零开始的。
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 * 示例:输入: numbers = [2, 7, 11, 15], target = 9
 * 输出: [1,2]    解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 */
public class _167SumOfTwoIIInputOrderedArray {

    public static int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        int sum = 0;
        while (left < right){
            sum = numbers[left] + numbers[right];
            if (sum > target) right--;
            else if (sum < target) left++;
            //因为题目要求我们需要返回两个符合要求的数的下标
            //但是为什么要left+1,right+1呢，因为他要的是下标，而不是索引，即第一个数返回下标1
            else return new int[]{left+1, right+1};
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        int[] numbers = {2, 7, 11, 15};
        System.out.println(Arrays.toString(twoSum(numbers, 9)));
    }

}
