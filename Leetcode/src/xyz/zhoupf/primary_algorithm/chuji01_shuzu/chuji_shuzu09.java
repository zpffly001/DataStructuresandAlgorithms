package xyz.zhoupf.primary_algorithm.chuji01_shuzu;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 两数之和
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 */
public class chuji_shuzu09 {

    //①暴力解法，遍历数组的所有元素，找到答案
    public static int[] twoSum(int[] nums, int target) {
        int[] index = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++){
                if (nums[j] == target - nums[i]){
                    index[0] = i;
                    index[1] = j;
                }
            }
        }
        return index;
    }

    //②.使用一遍HashMap
    public static int[] twoSum1(int[] nums, int target) {
        int[] index = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target-nums[i])){
                index[0] = map.get(target-nums[i]);
                index[1] = i;
            }
            map.put(nums[i], i);
        }
        return index;
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int[] ints = twoSum(nums, 9);
        System.out.println(Arrays.toString(ints));

        int[] ints1 = twoSum(nums, 9);
        System.out.println(Arrays.toString(ints1));
    }

}
