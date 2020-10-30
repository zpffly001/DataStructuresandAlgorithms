package xyz.zhoupf.primary_algorithm.chuji09_other;

import java.util.Arrays;

public class chuji_06queshideshuzi {

    //方法一：排序
    //首先我们对数组进行排序，随后我们可以在常数时间内判断两种特殊情况：0 没有出现在数组的首位，以及 nn 没有出现在数组的末位。如果这两种特殊情况都不满足，那么缺失的数字一定在 0 和 nn 之间（不包括两者）。此时我们可以在线性时间内扫描这个数组，如果某一个数比它前面的那个数大了超过 1，那么这两个数之间的那个数即为缺失的数字。
    public static int missingNumber(int[] nums) {
        Arrays.sort(nums);

        //判断n是否出现在末位
        if (nums[nums.length - 1] != nums.length) return nums.length;
        //判断0 是否出现在首位
        else if (nums[0] != 0) return 0;

        // 此时缺失的数字一定在 (0, n) 中
        for (int i = 1; i < nums.length; i++) {
            int expectedNum = nums[i - 1] + 1;
            if (nums[i] != expectedNum) return expectedNum;
        }
        // 未缺失任何数字（保证函数有返回值）
        return -1;
    }

    public static void main(String[] args) {
//        List<Integer> list = new ArrayList<>();
//        list.add(3);
//        list.add(6);
//        list.add(9);
//        list.add(1);
//        System.out.println(list);
//        list.remove(new Integer(1));
//        System.out.println(list);
        int[] nums = {9,6,4,2,3,5,7,0,1};
        //int[] nums = {0};
        System.out.println(missingNumber(nums));
    }

}
