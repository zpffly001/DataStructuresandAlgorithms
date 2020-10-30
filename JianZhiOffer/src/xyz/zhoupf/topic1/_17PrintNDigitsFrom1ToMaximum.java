package xyz.zhoupf.topic1;

import java.util.Arrays;

/**
 * 打印从1到最大的n位数
 * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
 *
 * 示例 1:    输入: n = 1    输出: [1,2,3,4,5,6,7,8,9]
 * 说明：用返回一个整数列表来代替打印   n 为正整数
 */
public class _17PrintNDigitsFrom1ToMaximum {

    //方法一：暴力解法
    public static int[] printNumbers(int n) {
        int[] arr = new int[(int) (Math.pow(10, n) - 1)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }

    //方法二：大数打印解法（正确解法）数字的取值范围都是有限的。因此，大数的表示应用字符串 String 类型。
    //这道题面试的时候要考虑大数的
    public static void main(String[] args) {
        System.out.println(Arrays.toString(printNumbers(2)));
    }
}
