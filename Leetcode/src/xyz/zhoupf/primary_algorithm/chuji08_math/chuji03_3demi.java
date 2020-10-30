package xyz.zhoupf.primary_algorithm.chuji08_math;

/**
 * 3的幂
 * 给定一个整数，写一个函数来判断它是否是 3 的幂次方。
 *
 * 示例 1:
 * 输入: 27
 * 输出: true
 * 示例 2:
 * 输入: 0
 * 输出: false
 * 示例 3:
 * 输入: 9
 * 输出: true
 * 示例 4:
 * 输入: 45
 * 输出: false
 */
public class chuji03_3demi {

    //暴力，时间超时
    public static boolean isPowerOfThree(int n) {
        if (n == 1) return true;
        else if (n < 3) return false;
        if (n == 3) return true;
        int ji = 1;
        while (ji < n/3){
            ji *= 3;
        }
        if (ji * 3 == n) return true;
        return false;
    }

    //第二种：
    public static boolean isPowerOfThree02(int n) {
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isPowerOfThree(27));
    }

}
