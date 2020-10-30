package xyz.zhoupf.algorithm_classification.double_pointer;

import java.util.Arrays;

/**
 * 合并排列的数组
 * 给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。
 * 初始化 A 和 B 的元素数量分别为 m 和 n。
 * 输入:
 * A = [1,2,3,0,0,0], m = 3
 * B = [2,5,6],       n = 3
 * 输出: [1,2,2,3,5,6]
 */
public class _10_01MergeSortedArrays {

    public void merge(int[] A, int m, int[] B, int n) {
        //确保将其中的一个数组中的数字遍历完
        while (m > 0 && n > 0){
            //需要比较 m + n 个数的大小,对比选出较大的数字，放在m + n - 1的位置，并将选出此数字的指针向前移动
            A[m + n - 1] = A[m - 1] > B[n - 1] ? A[--m] : B[--n];
        }

        //剩下的数都比已经遍历过的数字小，m和n有且只有一个为0；
        //1，如果m部位0，A中数字普遍偏大，且A，B是合并在A中的，因此没遍历完的数，都已在A中，不用再管
        while (n > 0){
            A[n - 1] = B[n - 1];
            n--;
        }
    }

    // 测试一下
    public static void main(String[] args) {
        int[] A = new int[] { 1, 2, 3, 0, 0, 0 };
        int[] B = new int[] { 2, 5, 6 };
        new _10_01MergeSortedArrays().merge(A, 3, B, 3);
        System.out.println(Arrays.toString(A));
    }

}
