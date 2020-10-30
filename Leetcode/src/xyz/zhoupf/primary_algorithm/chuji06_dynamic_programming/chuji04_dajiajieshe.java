package xyz.zhoupf.primary_algorithm.chuji06_dynamic_programming;

/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * 示例 2：
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 */
public class chuji04_dajiajieshe {

    //方法一：动态规划，时间复杂度O(n)，空间复杂度O(n)
    public static int rob(int[] nums) {
        int n = nums.length;
        // 处理边界条件。
        if (n == 0) return 0;
        if (n == 1) return nums[0];

        // 定义dp数组，按照状态转移方程递推。
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++){
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[n - 1];
    }

    //方法二，动态规划，时间复杂度O(n)，空间复杂度O(1)
    public static int rob01(int[] nums) {
        int a = 0, b = 0;
        for (int i = 0; i < nums.length; i++) {
            int c = Math.max(b, a + nums[i]);
            a = b;
            b = c;
        }
        return b;
    }
    public static void main(String[] args) {
        int[] arr = {2,7,9,3,1};
        System.out.println(rob(arr));
        System.out.println(rob01(arr));
    }

}
