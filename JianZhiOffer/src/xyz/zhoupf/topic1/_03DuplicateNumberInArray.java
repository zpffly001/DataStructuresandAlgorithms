package xyz.zhoupf.topic1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 剑指 Offer 03. 数组中重复的数字
 * 找出数组中重复的数字。
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 * 示例 1：
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 */
public class _03DuplicateNumberInArray {

    //方案一：暴力求解
    public static int findRepeatNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]){
                    return nums[i];
                }
            }
        }
        return -1;
    }

    //方案二：哈希查找
    public static int findRepeatNumber01(int[] nums) {
        Set<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashSet.contains(nums[i])) return nums[i];
            hashSet.add(nums[i]);
        }
        return -1;
    }

    //在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。这句话是前提，才能bucket[nums[i]] = i;
    //方案三：数组代替哈希表
    //Arrays.fill（ a1, value ）a1是一个数组变量，value是一个a1中元素数据类型的值，作用：填充a1数组中的每个元素都是value
    public static int findRepeatNumber02(int[] nums) {
        int[] bucket = new int[nums.length];
        //把数组中所有的数字都初始化为-1
        Arrays.fill(bucket, -1);

        for (int i = 0; i < nums.length; i++) {
            // 2. 判断当前元素是否已经存在(因为数组每个元素初始化都是-1，如果不是-1，说明数组的该元素之前被填充过)
            if (bucket[nums[i]] != -1) return nums[i];

            //否则的话，将当前元素的值作为索引，当前元素的下标作为值，填入数组中，方便后续的查找判重
            bucket[nums[i]] = i;
        }
        return -1;
    }

    //在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。这句话是前提，才能nums[nums[i]] = nums[i];
    //方案四：最优解法
    //如果没有重复数字，那么正常排序后，数字i应该在下标为i的位置，所以思路是重头扫描数组，遇到下标为i的数字如果不是i的话，（假设为m),
    // 那么我们就拿与下标m的数字交换。在交换过程中，如果有重复的数字发生，那么终止返回ture
    public static int findRepeatNumber03(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (i != nums[i]){
                if (nums[i] == nums[nums[i]]) return nums[i];

                // 交换,使下标为i的数组的值，作为索引放入nums[nums[i]]，并和其中原来的元素进行交换
                int tmp = nums[nums[i]];
                nums[nums[i]] = nums[i];
                nums[i] = tmp;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 2};
        System.out.println(findRepeatNumber03(arr));
    }

}
