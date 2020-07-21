package chuji_shuzu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class chuji_shuzu08 {

    public static void moveZeroes(int[] nums) {
        int count = 0; //用来计算0的个数
        for (int i = 0; i < nums.length; i++){
            if (nums[i] == 0){//如果为零则计数器加一
                count++;
            }else if (count != 0){//如果nums[i]不为0，将nums[i]跟前count个交换，并且将nums[i]赋值为0
                nums[i-count] = nums[i];
                nums[i] = 0;
            }
        }

    }//有点像堆排序

    public static void main(String[] args) {
        int [] nums = {0,1,0,3,12};
        moveZeroes(nums);
    }

}
