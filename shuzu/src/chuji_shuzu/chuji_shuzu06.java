package chuji_shuzu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 */
public class chuji_shuzu06 {
    public static int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for (int num1 : nums1){
            list1.add(num1);
        }
        for (int num2 : nums2){
            if (list1.contains(num2)){
                list2.add(num2);
                System.out.println(num2);
                //list1.remove(num2);//我写成直接删除数组元素Num2就出错了，数组里面的东西必须开箱，ArrayList这种情况注意
                list1.remove(Integer.valueOf(num2));//Integer.parseInt(String s)将会返回int常量。Integer.valueOf(String s)将会返回Integer类型，如果存在缓存将会返回缓存中已有的对象。ArrayList对象不能存储基本类型，只能存储引用类型的数据。类似 <int> 不能写，但是存储基本数据类型对应的包装类型是可以的
            }
        }
        System.out.println(list2.toString());
        int[] nums = new int[list2.size()];//很奇怪的是数组从零开始，定义数组大小应该比list集合的size小1，但是如我定义成size-1长度就会发生数组越界
        int i = 0;
        for (int num : list2){
            nums[i++] = num;
        }
        return nums;
    }

    public static void main(String[] args) {
        int [] nums1 = {4,9,5};
        int [] nums2 = {9,4,9,8,4};
        int[] arr = intersect(nums1,nums2);
        //System.out.println(intersect(nums1,nums2));//此方法返回过来的数组就算用.toString()方法也是地址值
        System.out.println(Arrays.toString(arr));
    }

}
