package xyz.zhoupf.algorithm_classification.double_pointer;

import java.util.Arrays;

/**
 * 移除元素
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 * 示例 1:    给定 nums = [3,2,2,3], val = 3,
 * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。你不需要考虑数组中超出新长度后面的元素。
 */
public class _27RemovingElements {

    public static int[] removeElement(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return nums;
        /*int left = 0;
        int right = 0;
        for (int i = 0; i < nums.length; i++, right++) {
            if (nums[i] != val) left = i;
            if (nums[right] != val){
                int tmp = nums[right];
                nums[right] = nums[left];
                nums[left] = tmp;
            }
            if (right == nums.length-1)
                return left + 1;
        }
        return -1;*/
    }

    public static void main(String[] args) {
        int[] arr = {3,2,2,3};
        System.out.println(Arrays.toString(removeElement(arr, 3)));
    }

}
