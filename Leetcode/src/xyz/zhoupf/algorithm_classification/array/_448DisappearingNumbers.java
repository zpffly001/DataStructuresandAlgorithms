package xyz.zhoupf.algorithm_classification.array;

import java.util.ArrayList;
import java.util.List;

public class _448DisappearingNumbers {

    public static void main(String[] args) {
        int[] arr = {4,3,2,7,8,2,3,1};
        System.out.println(findDisappearedNumbers1(arr));
    }

    // 超出了时间限制
    public static List<Integer> findDisappearedNumbers1(int[] nums) {
        int n = nums.length;
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        System.out.println(list);
        for (int i = 0; i < n; i++) {
            list.remove(new Integer(nums[i]));
        }

        return list;
    }

    public static List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        for (int num : nums) {
            // 因为数组长度为0 ~ n-1，因此我们需要-1
            int x = (num - 1) % n;
            nums[x] += n;
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n) {
                // 因为之前我们顾及数组长度小于n，而数值进行了-1操作
                list.add(i + 1);
            }
        }

        return list;
    }



}
