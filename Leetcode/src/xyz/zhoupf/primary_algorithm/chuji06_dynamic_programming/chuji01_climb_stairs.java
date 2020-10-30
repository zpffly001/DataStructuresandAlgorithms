package xyz.zhoupf.primary_algorithm.chuji06_dynamic_programming;

/**
 * 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个正整数。
 * 示例 1：
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 思路：
 * 最后一步可能走一个台阶，即之前走了f(n-1)步
 * 最后一步也可能走两个台阶，即之前走了f(n-2)步
 * 因此n个台阶需要走的步数为：f(n-1)+f(n-2)
 */
public class chuji01_climb_stairs {

    //方法一：动态规划
    public static int climbStairs(int n) {
        int p = 0, q = 0, r = 1;
        for(int i = 1; i <= n; ++i){
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }

    //方法二动态规划
    public static int climbStairs01(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    //方法三：递归
    public static int climbStairs02(int n) {
        //if (n <= 1) return 1;//或者
        if (n <= 2) return n;//这两种都会超出时间限制
        return climbStairs02(n - 1) + climbStairs02(n - 2);
    }

    public static void main(String[] args) {
        System.out.println(climbStairs(45));
        System.out.println(climbStairs01(45));
        System.out.println(climbStairs02(45));
    }

}
