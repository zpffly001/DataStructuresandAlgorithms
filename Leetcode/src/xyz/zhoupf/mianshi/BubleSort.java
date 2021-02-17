package xyz.zhoupf.mianshi;

public class BubleSort {

    public static void main(String[] args) {
        int[] nums = {23, 12, 55, 34, 9};
        int temp;
        //冒泡排序只需要排元素个数-1次，而且我们从0开始，即从0--->arrs.length - 1
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]){
                    temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }

        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }


    }

}
