package xyz.zhoupf.primary_algorithm.chuji01_shuzu;

/**
 * 从排序数组中删除重复项
 */

public class chuji_shuzu01 {
    public static int removeDuplicates(int[] nums) {
        if(nums.length == 0) return 0;
        int i = 0;
        for(int j = 1; j < nums.length; j++) {
            if(nums[j] != nums[i]) { //
                i++;
                nums[i] = nums[j];
            }
        }
        return i+1;
    }

    public static void main(String[] args) {
        int[] nums = {0,0,1,1,1,2,2,3,3,};
        System.out.println(removeDuplicates(nums));
    }
    
}
