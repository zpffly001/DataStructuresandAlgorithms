package xyz.zhoupf.algorithm_classification.double_pointer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个只包含整数的有序数组，每个元素都会出现两次，唯有一个数只会出现一次，找出这个数。
 * 示例 1:
 * 输入: [1,1,2,3,3,4,4,8,8]  输出: 2
 */
public class _541ASingleElement {

    //自己的笨方法(看错了，原来只要出现两次的数字都是连续的，我以为是不连续的)
    public static int singleNonDuplicate(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) == null){
                map.put(nums[i], 1);
            }else {
                map.put(nums[i], 2);
            }
        }
        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        for (Map.Entry<Integer, Integer> entry : entries) {
            if (entry.getValue() == 1){
                return entry.getKey();
            }
        }
        return 0;
    }

    //大佬的方法
    public static int singleNonDuplicate02(int[] nums) {
        for (int i = 0; i < nums.length - 1; i+=2) {
            if (nums[i] != nums[i + 1]){
                return nums[i];
            }
        }
        return nums[nums.length - 1];
    }

    public static void main(String[] args) {

    }

}
