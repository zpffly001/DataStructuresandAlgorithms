package xyz.zhoupf.primary_algorithm.chuji08_math;

import java.util.ArrayList;

/**
 * 计数质数
 * 统计所有小于非负整数 n 的质数的数量。
 *
 * 示例:
 * 输入: 10
 * 输出: 4
 * 解释: 小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 */
public class chuji02_CountSuShu {

    //方法一：暴力法（计算超时）
    public static int countPrimes(int n) {
        int count = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 2; i < n; i++) {
            boolean flag = false;
            for (int j = 2; j < i; j++) {
                if (i % j == 0){
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                count++;
                list.add(i);
            }

        }
        System.out.println(list);
        return count;
    }

    //方法二：优化暴力算法，我们可以发现，假如一个数为 9 ，那么其二分之一（4.5）后的数都可以不用进行计算，因为肯定是有余的 。
    // 事实上情况会比这更好一些：对正整数 n ，如果用 2 到 √n 之间(包含边界)的所有整数去除，均无法整除，则 n 为质数。
    //并且，我们可以发现，一切非 2 偶数一定不可能为质数。所以，我们可以在此处进行另一步的优化。
    public static int countPrimes01(int n) {
        if (n < 3) return 0;
        //从3开始验算，所以初始值为1（2为质数）
        int count = 1;
        for (int i = 3; i < n; i++) {
            //表明这个数转化为二进制后最后一位一定为0，前面几位全为1(即2，4，8他们和肯定为偶数)
            //且i>2，即说明如果符合条件，则表明i是一个大于2的整数，即i肯定能被2整除，一定不是质数，跳过。按照官方话就是（//当某个数为 2 的 n 次方时（n为自然数），其 & (n - 1) 所得值将等价于取余运算所得值，如果 x = 2^n ，则 x & (n - 1) == x % n）
            if ((i & 1) == 0)
                continue;
            boolean flag = true;
            //用 j * j <= i 代替 j <= √i 会更好。即j * j <= i;
            //因为我们已经排除了所有偶数，所以每次循环加二将规避偶数会减少循环次数
            for (int j = 3; j * j <= i; j+=2) {
                if (i % j == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) count++;
        }
        return count;
    }

    //方法三：厄拉多塞筛法(    如果我们在进行顺序遍历时，每取得一个数（排除0、1），如果将它所有的倍数（排除0、1、本身）都清除，那么，剩下的数是不是必为素数？,没错，这个有趣且实用的方法便是著名的厄拉多塞筛法！)
    //对此，我们可以声明一个长度为最大限制数的布尔数组。用布尔值来区别筛选出的数和质数。
    public static int countPrimes02(int n) {
        int count = 0;
        boolean[] flags = new boolean[n];//boolean默认为false
        for (int i = 2; i < n; i++) {
            //刚开始判断一定可以通过，因为像1，3，5就是初始倍数，没有倍数
            if (!flags[i]){
                count++;
                for (int j = i + i; j < n; j += i) {
                    //在i的倍数中，排除不是质数的数
                    flags[j] = true;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(countPrimes(2000));
        System.out.println(countPrimes01(2000));
        System.out.println(countPrimes02(2000));

    }

}
