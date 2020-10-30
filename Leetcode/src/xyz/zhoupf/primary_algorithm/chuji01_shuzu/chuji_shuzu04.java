package xyz.zhoupf.primary_algorithm.chuji01_shuzu;

import java.util.HashMap;

/**
 * 给定一个整数数组，判断是否存在重复元素。
 *
 * 如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。
 */
public class chuji_shuzu04 {
    public static boolean containsDuplicate(int[] nums) {
//        for (int i = 0; i < nums.length-1; i ++){
//            for (int j = i+1; j < nums.length; j ++){
//                if (nums[i] == nums[j]){
//                    return true;
//                }
//            }
//        }
//        return false; 这种方法计算效率太低，运行超时

//        List<Integer> list = new ArrayList<>();
//        for (int i = 0; i < nums.length; i ++){
//            if(!list.contains(nums[i])){
//                list.add(nums[i]);
//            }else {
//                return true;
//            }
//        }
//        return false; 这种方法计算效率太低，运行超时

        HashMap<Integer,Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i ++){
            if (!hashMap.containsKey(nums[i])){
                hashMap.put(nums[i], 1);
            }else {
                return true;
            }
        }
        return false; //HashMap的效率比 循环 和 List 两者的效率高
    }

    public static void main(String[] args) {
        int [] nums = {1,2,3,1};
        System.out.println(containsDuplicate(nums));
//        for(int i=0;i<nums.length;i++){
//            System.out.println(nums[i]);
//        }
    }

}
