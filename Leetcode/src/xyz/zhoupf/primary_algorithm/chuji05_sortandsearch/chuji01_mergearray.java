package xyz.zhoupf.primary_algorithm.chuji05_sortandsearch;

import java.util.Arrays;

public class chuji01_mergearray {

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] nums1_copy = new int[m];
        //从0索引开始，拷贝m个元素
        System.arraycopy(nums1, 0, nums1_copy, 0, m);

        int p1 = 0;
        int p2 = 0;

        int p = 0;

        while ((p1 < m) && (p2 < n)){
            nums1[p++] = (nums1_copy[p1] < nums2[p2]) ? nums1_copy[p1++] : nums2[p2++];
        }

        if (p1 < m)
            System.arraycopy(nums1_copy, p1, nums1, p1 + p2, m + n - p1 - p2);
        if (p2 < n)
            System.arraycopy(nums2, p2, nums1, p1 + p2, m + n - p1 - p2);
        System.out.println(Arrays.toString(nums1));
    }

    public static void merge01(int[] nums1, int m, int[] nums2, int n) {
        //m表示nums1中有效的值个数，也简化了我们不用对无用值进行判断，因为数组前m个就是有效的
        //因为需要把两个数组合并到nums1，覆盖nums1中无用的值
        int[] nums1_copy = new int[m];
        //把nums1数组从0索引开始的值全部赋值到nums1_copy数组从0索引开始，复制m个元素
        System.arraycopy(nums1, 0, nums1_copy, 0, m);

        int p = 0;
        int q = 0;
        int index = 0;

        while ((p < m) && (q < n)){
            //nums1[index++] = (nums1_copy[p] < nums2[q]) ? nums1_copy[p++] : nums2[q++];
            if (nums1_copy[p] < nums2[q]){
                nums1[index] = nums1_copy[p];
                p++;
            }
            else {
                nums1[index] = nums2[q];
                q++;
            }
            index++;
        }
        if (p < m) System.arraycopy(nums1_copy, p, nums1, p+q, m+n-p-q);
        else System.arraycopy(nums2, q, nums1, p+q, m+n-p-q);

    }

    public static void main(String[] args) {
        //合并到一个数组中，数组不可变，因此用0站位，然后在那数组2中的元素进行填充
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int[] nums2 = {2, 5, 6};
        merge(nums1, 3, nums2, 3);
        merge01(nums1, 3, nums2, 3);
    }

}
