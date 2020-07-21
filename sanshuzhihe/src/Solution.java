import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public static List<List<Integer>> threeSum(int[] nums){
        List<List<Integer>> lists = new ArrayList<>();
        if (nums == null && nums.length < 3) return lists;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length-1; i ++){
            if(i > 0 && nums[i] == nums[i-1]) continue;// 去重，如果if条件成立则忽略掉条件中成立的情况，这里之所以拿i和i-1相比是因为可以执行一次而不多次执行重复的若改为i和i+1相比则在重复的几种情况下一个不取，则导致少取
            int L = i + 1;
            int R = nums.length - 1;
            while (L < R){
                int sum = nums[i] + nums[L] + nums[R];
                if (sum == 0){
                    lists.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    while (L<R && nums[L] == nums[L+1]) L++; // 去重,(L<R 防止越界，进来的时候是一定符合的，但是++后就不一定符合了
                    while (L<R && nums[R] == nums[R-1]) R--; // 去重
                    L++;
                    R--;
                }
                else if (sum < 0) L++;
                else if (sum > 0) R--;
            }
        }
        return lists;
    }

    public static void main(String[] args) {
        int[] nums = new int[6];
        nums[0] = -1;
        nums[1] = 0;
        nums[2] = 1;
        nums[3] = 2;
        nums[4] = -1;
        nums[5] = -4;

        System.out.println(threeSum(nums));
    }

}
