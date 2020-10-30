package xyz.zhoupf.primary_algorithm.chuji06_dynamic_programming;

/**
 * 最大子序和
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 * 输入: [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 */
public class chuji03_maxzixuhe {

    //暴力解题，时间复杂度O(n^2)
    public static int maxSubArray(int[] nums) {
        int len = nums.length;
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            int sum = 0;
            for(int j = i; j < len; j++){
                sum += nums[j];
                res = Math.max(res, sum);
            }
        }
        return res;
    }

    //动态规划+递归，时间复杂度O(n)
    public static int maxSubArray01(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        //定义dp数组，dp数组中的每个值dp[i]代表着以nums[i]为结尾的最大子序和
        int[] dp = new int[n];
        //以nums[0]结尾的最大子序和就是nums[0]
        dp[0] = nums[0];
        //遍历，通过状态转移方程求得dp[i]的最大子序和
        for (int i = 1; i < n; i++) {
            //dp[i]的最大子序和要么是自成一派最大，要么就是当前值加上前面i - 1个数的最大子序和
            dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);//这里使用了递归
        }

        ////遍历dp数组，求得dp数组中的最大值，就是该题的答案
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    //官方，动态规划，时间复杂度O(n)
    public static int maxSubArray02(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int i = 0; i < nums.length; i++) {
            //pre为前i-1个值的最大值
            pre = Math.max(pre + nums[i], nums[i]);
            //在把i-1个最大值，和i个最大值作比较，谁大选谁，遍历到最后就是那个最大的
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }

    public static void main(String[] args) {
        int[] arr = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(arr));
        System.out.println(maxSubArray01(arr));
        System.out.println(maxSubArray02(arr));
    }

}


/**
 * 解法二、动态规划
 * 按照排列组合的数学算法，9 个数字，以第 i 个数字结尾的串，有 i 种组合，一共有45个组合(\sum_{i=1} ^9 i )(∑
 * i=1
 * 9
 * ​
 *  i)，如果有n个数字，时间复杂度是$O(n^2) $，这样的时间复杂度是明显不能接受的。
 *
 * 我们把目光落到动态规划上面来，首先需要把这个问题分解成最优子问题来解。最主要的思路就是将上面的45个组合进行
 * ，分解成数量较少的几个子问题。在这里我们一共有9个数字，顺理成章的我们把组合分解成9个小组的组合。
 *
 * 第一个子组合是以第一个数字结尾的连续序列，也就是 [-2]，最大值-2
 *
 * 第二个子组合是以第二个数字结尾的连续序列，也就是 [-2,1], [1]，最大值1
 *
 * 第三个子组合是以第三个数字结尾的连续序列，也就是 [-2,1,3], [1,3], [3]，最大值4
 *
 * 以此类推。。。
 *
 * 如果我们能够得到每一个子组合的最优解，也就是子序列的最大值，整体的最大值就可以通过比较这9个子组合的最大值来得到了。现在我们找到了最优子问题，重叠子问题在哪呢？那就得细心比较一下每个子问题。
 *
 * 从第二个子组合和第三个子组合可以看到，组合 3 只是在组合 2 的基础上每一个数组后面添加第 3 个数字，也就是数字 3，然后增加一个只有第三个数字的数组 [3] 。这样两个组合之间的关系就出现了，可是我们不关心这个序列是怎么生成的，只是关心最大值之间的关系。
 *
 * 下面我们看组合 3 的组成，我们将子组合 3 分成两种情况：
 * 继承子组合二得到的序列，也就是[-2,1,3], [1,3] （最大值 1 = 第二个组合的最大值 + 第三个数字）
 * 单独第三个数字的序列，也就是[3] （最大值 2 = 第三个数字）
 * 如果 第二个序列的最大值 大于0，那么最大值 1 就比最大值 2 要大，反之最大值 2 较大。这样，我们就通过第二个组合的最大值和第三个数字，就得到了第三个组合的最大值。因为第二个组合的结果被重复用到了，所以符合这个重叠子问题的定义。通俗来讲这个问题就变成了，第 i 个子组合的最大值可以通过第i-1个子组合的最大值和第 i 个数字获得，如果第 i-1 个子组合的最大值没法给第 i 个数字带来正增益，我们就抛弃掉前面的子组合，自己就是最大的了。
 *
 * 步骤一、定义状态 -> 定义数组元素的含义
 * 定义 dp[i] 为以 i 结尾子串的最大值
 * 步骤二、状态转移方程 -> 找出数组元素间的关系式
 * dp[i]= \begin{cases} dp[i-1]+nums[i],\quad if\quad dp[i-1]\ge0\\ nums[i],\quad\quad\quad\quad\quad\quad if\quad dp[i-1]<0\\ \end{cases}
 * dp[i]={
 * dp[i−1]+nums[i],ifdp[i−1]≥0
 * nums[i],ifdp[i−1]<0
 * ​
 *
 *
 * 步骤三、初始化 -> 找出初始条件
 * dp[0] = nums[0];
 * 步骤四、状态压缩 -> 优化数组空间
 * 每次状态的更新只依赖于前一个状态，就是说 dp[i] 的更新只取决于 dp[i-1] , 我们只用一个存储空间保存上一次的状态即可。
 * 步骤五、选出结果
 * 有的题目结果是 dp[i] 。
 * 本题结果是 dp[0]...dp[i] 中最大值。
 *
 * 作者：lizhiqiang-3
 * 链接：https://leetcode-cn.com/problems/maximum-subarray/solution/zheng-li-yi-xia-kan-de-dong-de-da-an-by-lizhiqiang/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
